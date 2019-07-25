package ru.matorinun.upravbotprof.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Single;
import ru.matorinun.upravbotprof.data.room.RequestEntity;
import ru.matorinun.upravbotprof.networking.login.LoginBody;
import ru.matorinun.upravbotprof.networking.login.LoginParent;
import ru.matorinun.upravbotprof.networking.main.RequestsBody;
import ru.matorinun.upravbotprof.networking.main.ProfRequestParent;


public interface Repository {

    /*--- Room DB methods ---*/
    LiveData<List<RequestEntity>> getAllRequests();

    LiveData<List<RequestEntity>> getAllByStatus(String status);

    LiveData<RequestEntity> findById(int id);

    Single<Long> insertOne(RequestEntity requestEntity);

    Single<List<Long>> insertAll(List<RequestEntity> requestEntityList);

    LiveData<Integer> getRequestsCount();

    Single<Integer> nukeTable();


    /*--- Retrofit methods---*/
    Single<LoginParent> loginParent(LoginBody body);

    Single<ProfRequestParent> getAllRequestsParent(RequestsBody body);


}
