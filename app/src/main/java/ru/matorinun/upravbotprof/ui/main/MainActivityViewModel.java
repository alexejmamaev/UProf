package ru.matorinun.upravbotprof.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.room.RequestEntity;
import ru.matorinun.upravbotprof.networking.ObserverListener;
import ru.matorinun.upravbotprof.networking.main.ProfRequest;
import ru.matorinun.upravbotprof.networking.main.ProfRequestParent;
import ru.matorinun.upravbotprof.networking.main.RequestsBody;


// MVVM architecture mediator class (view - mediator - model)
class MainActivityViewModel extends ViewModel {

    private static final String LOG = MainActivityViewModel.class.getSimpleName();

    private Repository repository;
    private SharedPrefsHelper prefsHelper;
    private ObserverListener listener;
    Disposable nukeDisposable;
    Disposable storeDataDisposable;

    MainActivityViewModel(Repository repository, SharedPrefsHelper prefsHelper,
                          ObserverListener listener) {
        this.repository = repository;
        this.prefsHelper = prefsHelper;
        this.listener = listener;
    }



    LiveData<List<RequestEntity>> syncWithDB(){
        return repository.getAllRequests();
    }



    /*
    * Асинхронный запрос для получения списка заявок
    * @param STATUS_ID (null - все заявки)
    */
    Disposable syncWithRemoteData(String STATUS_ID){

        String tokenID = prefsHelper.get(SharedPrefsHelper.TOKEN_ID, "");

    //TODO 5. Можно ли внедрить new DisposableSingleObserver через DI и надо ли это делать?
        return repository.getAllRequestsParent(new RequestsBody(tokenID, STATUS_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //TODO 6. надо ли внедрять new Function через DI?
                .map(new Function<ProfRequestParent, List<RequestEntity>>() {
                    /**
                     * Apply some calculation to the input value and return some other value.
                     * @param profRequestParent the input value
                     * @return the output value
                     */
                    @Override
                    public List<RequestEntity> apply(ProfRequestParent profRequestParent) throws Exception {
                        return getConvertedList(profRequestParent);
                    }
                })
//                .doOnSuccess(entities -> {
//                    Log.e(LOG, "-----doOnSuccess -> " + entities.size());
//                    storeData(entities);
//                })
                .subscribeWith(new DisposableSingleObserver<List<RequestEntity>>(){

                    @Override
                    public void onSuccess(List<RequestEntity> entities) {
                        Log.e(LOG, "1-- entities received");
                        storeData(entities);
                        }


                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }
                });

    }




    // Mapper (retrofit POJO --> ROOM Entities)
    private List<RequestEntity> getConvertedList(ProfRequestParent parent){

        //TODO 7. Если в данной функции мы создаем new ArrayList<>(), его надо внедрять через DI?
        List<RequestEntity> out = new ArrayList<>();

        if(resultSuccess(parent)){

            List<ProfRequest> in = parent.getResultData().getREQUESTS();

            if(in.size() > 0){

                for (ProfRequest request : in){
                    RequestEntity entity = new RequestEntity(
                            request.getREQUEST_ID(),
                            request.getMOBILE_NUMBER(),
                            request.getLOG_IN_ID(),
                            request.getFIO(),
                            request.getPHONE_NUMBER(),
                            request.getADDRESS(),
                            request.getROOM(),
                            request.getOBJECT_ID(),
                            request.getCR_DATE(),
                            request.getREQUEST_TEXT(),
                            request.getEMERGENCY_TREATMENT(),
                            request.getROOM_TYPE(),
                            request.getE_MAIL(),
                            request.getTECH_NAME(),
                            request.getTECH_LOGIN(),
                            request.getM_STATUS(),
                            request.getM_STATUS_ID(),
                            request.getS_NAME(),
                            request.getSTATUS(),
                            request.getSTATUS_ID());
                    out.add(entity);
                }
            }


        } else {
            listener.onSyncError();
        }

        return out;
    }



    // Result: success - запрос успешен; другое - запрос провалился.
    private boolean resultSuccess(ProfRequestParent profRequestParent){
        return profRequestParent != null &&
                profRequestParent.getResultData().getResult().equalsIgnoreCase("Success");
    }


    // Заново заполняет базу данных полным списком заявок
    private void storeData(List<RequestEntity> entities){

        // Очищает таблицу перед обновлением данных
        nukeDisposable = repository.nukeTable()
                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(LOG, "2-- table nuked");
                        // Запускает загрузку листа заявок в DB
                        saveDataIntoDB(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG, e.toString());
                        listener.onSyncError();

                    }
                });

    }


    private void saveDataIntoDB(List<RequestEntity> entities){

        if(entities.size() > 0){
            storeDataDisposable = repository.insertAll(entities)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<Long>>() {
                        @Override
                        public void onSuccess(List<Long> ids) {
                            Log.e(LOG, "3-- entities stored = " + ids.size());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(LOG, e.toString());
                            listener.onSyncError();
                        }
                    });


            repository.insertAll(entities);

        } else {
            listener.onSuccess(entities);
        }
    }


    // Deletes table contents from the db
    Disposable nukeData(){
        return repository.nukeTable()
                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(LOG, "DELETED --- " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG, e.toString());
                    }
                });
    }



    void setFakeData(){
        repository.insertOne(new RequestEntity("1", "2", "3",
                "4", "5", "6", "7", "8", "9",
                "10", "11", "12", "13",
                "14", "15", "16", "17", "18",
                "19", "20"));

    }

}
