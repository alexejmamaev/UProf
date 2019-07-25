package ru.matorinun.upravbotprof.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.matorinun.upravbotprof.R;
import ru.matorinun.upravbotprof.UpravbotApplication;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.data.room.RequestEntity;
import ru.matorinun.upravbotprof.networking.ObserverListener;


public class MainActivity extends AppCompatActivity implements ObserverListener, ItemClickListener {

    private final static String LOG = MainActivity.class.getSimpleName();

    //These references will be satisfied by 'MainComponent.inject(this)' method
    @Inject
    SharedPrefsHelper prefsHelper;

    @Inject
    MainActivityViewModel model;

    @Inject
    RequestsHistoryAdapter adapter;

    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    DividerItemDecoration dividerItemDecoration;


    private ConstraintLayout rootView;
    private ProgressBar loadingIndicator;
    private Vibrator vibrator;

    private Snackbar snackbar;

    private RecyclerView rv;
    private TextView empty_data_label;
    private Button bttn1, bttn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This thing is needed for fields vars to be injected, cos activities has no constructors
        DaggerMainComponent.builder()
                .appComponent(UpravbotApplication.getAppComponent())
                //TODO 3. нормально ли передавать в модуль интерфейс @param listener, не образуется ли тут еще одна зависимость???
                .mainActivityModule(new MainActivityModule(
                        this, this, this))
                .build()
                .inject(this);

        setViews();

        setUpViewModel();


    }

    private void setViews(){

        rootView = findViewById(R.id.main_rootView);
        loadingIndicator = findViewById(R.id.main_loadingIndicator);

        //TODO 4. надо ли вибратор тоже внедрять через DI?
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        empty_data_label = findViewById(R.id.empty_data_main_tv);

        bttn1 = findViewById(R.id.button1);
        bttn1.setOnClickListener(view -> updateData());

        bttn2 = findViewById(R.id.button2);
        bttn2.setOnClickListener(view -> compositeDisposable.add(model.nukeData()));

        rv = findViewById(R.id.main_recycler);
        rv.addItemDecoration(dividerItemDecoration);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }


    private void setUpViewModel(){
        model.syncWithDB().observe(this, new Observer<List<RequestEntity>>() {
            @Override
            public void onChanged(List<RequestEntity> entities) {
                if(entities.size() == 0) {
                    empty_data_label.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                } else {
                    showLoading(false);
                    empty_data_label.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                    adapter.setData(entities);
                }

            }
        });
    }

    @Override
    public void OnItemClickListener(RequestEntity entity) {
        setSnackbar(entity.getFIO());
    }

    private void updateData(){
        vibrator.vibrate(20);
        showLoading(true);

        String STATUS_ID = null;
        compositeDisposable.add(model.syncWithRemoteData(STATUS_ID));

    }

    // Загрузка данных с сервера прошла успешно (Result == Success)
    @Override
    public void onSuccess(List<RequestEntity> entities) {

        showLoading(false);

        if(entities.size() == 0){
            empty_data_label.setText("НЕТ ДАННЫХ ДЛЯ ОТОБРАЖЕНИЯ");
            return;
        }

                setUpViewModel();

    }

    // Данные загружаются, но Result != Success(скорее всего ошибка в запросе или ошибка на сервере)
    // @param entitiesSize -> размер передаваемого списка
    @Override
    public void onSyncError() {
        showLoading(false);
        setSnackbar(getString(R.string.fail_receive_data));
    }

    // Ошибка загрузки данных с сервера (скорее всего просто нет связи)
    @Override
    public void onError(Throwable e) {
        showLoading(false);
        setSnackbar(getString(R.string.connection_error));

    }

    void showLoading(boolean loading) {
        loadingIndicator.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    void setSnackbar(String errmsg){

        if(!TextUtils.isEmpty(errmsg)) {

            if(errmsg.equals(getString(R.string.fail_receive_data))){
                snackbar = Snackbar.make(rootView, errmsg, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.retry, v -> updateData());
                snackbar.setActionTextColor(getResources().getColor(R.color.retry_green));
            } else {
                snackbar = Snackbar.make(rootView, errmsg, Snackbar.LENGTH_SHORT);
            }

            snackbar.show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Called if the app is going to be killed
        if (isFinishing()) {
            Log.e(LOG, "App is killed >>>>>>>>>>>");

            if(compositeDisposable != null && !compositeDisposable.isDisposed()){

                if(model.nukeDisposable != null && !model.nukeDisposable.isDisposed())
                    compositeDisposable.add(model.nukeDisposable);

                if(model.storeDataDisposable != null && !model.storeDataDisposable.isDisposed())
                    compositeDisposable.add(model.storeDataDisposable);

                compositeDisposable.dispose();
            }


            if(snackbar != null) snackbar.dismiss();

        }

        // Called when activity is recreated due to orientation change (app is not killed)
        else {
            Log.e(LOG, "Orientation change >>>>>>>>>>>>");
        }
    }


}
