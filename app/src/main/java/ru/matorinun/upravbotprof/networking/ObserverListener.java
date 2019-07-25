package ru.matorinun.upravbotprof.networking;

import java.util.List;

import ru.matorinun.upravbotprof.data.room.RequestEntity;
import ru.matorinun.upravbotprof.di.scopes.ActivityScope;

@ActivityScope
public interface ObserverListener {

    void onSuccess(List<RequestEntity> entities);
    void onSyncError();
    void onError(Throwable e);
}
