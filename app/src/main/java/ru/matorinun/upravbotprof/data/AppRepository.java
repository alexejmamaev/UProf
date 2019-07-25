package ru.matorinun.upravbotprof.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.matorinun.upravbotprof.data.room.RequestEntity;
import ru.matorinun.upravbotprof.data.room.RequestDao;
import ru.matorinun.upravbotprof.networking.ApiService;
import ru.matorinun.upravbotprof.networking.login.LoginBody;
import ru.matorinun.upravbotprof.networking.login.LoginParent;
import ru.matorinun.upravbotprof.networking.main.RequestsBody;
import ru.matorinun.upravbotprof.networking.main.ProfRequestParent;

public class AppRepository implements Repository {

    private static final String LOG = AppRepository.class.getSimpleName();
    private RequestDao mDao;
    private ApiService apiService;

    @Inject
    public AppRepository(RequestDao dao, ApiService api){
        mDao = dao;
        apiService = api;
    }



    @Override
    public LiveData<List<RequestEntity>> getAllRequests(){
        Log.e(LOG, "AppRepository => getAllRequests");
        return mDao.getAll();
    }

    @Override
    public LiveData<List<RequestEntity>> getAllByStatus(String status){
        Log.e(LOG, "AppRepository => getAllByStatus (" + status + ")");
        return mDao.getAllByStatus(status);
    }

    @Override
    public LiveData<RequestEntity> findById(int id) {
        Log.e(LOG, "AppRepository => findById (" + id + ")");
        return mDao.getById(id);
    }

    // Вставить в таблицу ОДНУ заявку
    @Override
    public Single<Long> insertOne(RequestEntity requestEntity){
        return Single.fromCallable(() -> mDao.insert(requestEntity));
    }


    // Вставить в таблицу ВСЕ заявки
    @Override
    public Single<List<Long>> insertAll(List<RequestEntity> requestEntityList){
        return Single.fromCallable(() -> mDao.insertAll(requestEntityList));
    }


    @Override
    public LiveData<Integer> getRequestsCount(){
        Log.e(LOG, "AppRepository => getRequestsCount " + mDao.count());
        return mDao.count();
    }


    // полностью очищаем таблицу
    @Override
    public Single<Integer> nukeTable() {
        return Single.fromCallable(() -> mDao.nukeTable());
    }


    // Регистрация на сервере
    @Override
    public Single<LoginParent> loginParent(LoginBody body) {
        return apiService.logIn(body);

    }

    // Получение общего списка заявок с сервера
    @Override
    public Single<ProfRequestParent> getAllRequestsParent(RequestsBody body) {
        return apiService.getAllRequestsList(body);

    }
}
