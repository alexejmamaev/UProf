package ru.matorinun.upravbotprof.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import io.reactivex.disposables.Disposable;
import ru.matorinun.upravbotprof.R;
import ru.matorinun.upravbotprof.UpravbotApplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;


public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getSimpleName();

    private Disposable disposable;

    //This reference will be satisfied by 'LoginComponent.inject(this)' method
    @Inject
    LoginActivityPresenter presenter;


    private ConstraintLayout rootView;
    private TextView hint;
    private EditText password_input, login_input;
    private Vibrator vibrator;
    private ProgressBar loadingIndicator;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // This thing is needed for field vars to be injected, cos activity has no constructor
        DaggerLoginComponent.builder()
                .appComponent(UpravbotApplication.getAppComponent())
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);

        presenter.profileValidationCheck();

        setupViews();

    }



    private void setupViews(){
        rootView = findViewById(R.id.login_rootView);
        login_input = findViewById(R.id.edit_login);
        login_input.setOnFocusChangeListener((view, focused) -> {
            if(focused){
                hint.setVisibility(View.VISIBLE);
            } else {
                hint.setVisibility(View.GONE);
            }

        });

        password_input = findViewById(R.id.edit_password);

        //TODO 1. надо ли вибратор тоже внедрять через DI?
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button login_bttn = findViewById(R.id.button_login);
        login_bttn.setOnClickListener(v -> {
            vibrator.vibrate(20);
            login_input.clearFocus();
            v.requestFocus();
            onButtonClick();
        });

        hint = findViewById(R.id.hint_bubble_login);

        loadingIndicator = findViewById(R.id.login_loadingIndicator);

    }

    private void onButtonClick(){
        String login = login_input.getText().toString().trim();
        String password = password_input.getText().toString().trim();

        if(TextUtils.isEmpty(login) || login.contains(" ")){
            setSnackbar(getString(R.string.empty_logint_input));
            login_input.requestFocus();
        } else if(TextUtils.isEmpty(password) || password.contains(" ")){
            setSnackbar(getString(R.string.empty_password_input));
            password_input.requestFocus();
        } else {

            disposable = presenter.getDisposable(login, password);

        }

    }

    void showLoading(boolean loading) {
        loadingIndicator.setVisibility(loading ? View.VISIBLE : View.GONE);
    }


    void setSnackbar(String errmsg){

        if(!TextUtils.isEmpty(errmsg)) {

          snackbar = Snackbar.make(rootView, errmsg, Snackbar.LENGTH_LONG);
          snackbar.show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(disposable != null && !disposable.isDisposed())
            disposable.dispose();

        if(snackbar != null) snackbar.dismiss();
    }
}
