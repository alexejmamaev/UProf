package ru.matorinun.upravbotprof.di.modules;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.room.AppDatabase;
import ru.matorinun.upravbotprof.data.AppRepository;
import ru.matorinun.upravbotprof.data.room.RequestDao;
import ru.matorinun.upravbotprof.di.scopes.ApplicationContext;
import ru.matorinun.upravbotprof.networking.ApiService;

@Module
public class RoomModule {

    private static final String APP_DATABASE_NAME = "requests_database";

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase(@ApplicationContext Context context) {
        Log.e("RoomModule", "providing RoomDatabase singleton");
        return Room.databaseBuilder(context, AppDatabase.class, APP_DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    RequestDao providesRequestDao(AppDatabase appDatabase) {
        Log.e("RoomModule", "providing Dao singleton");
        return appDatabase.requestDao();
    }

    @Singleton
    @Provides
    Repository providesRepository(RequestDao requestDao, ApiService apiService) {
        Log.e("RoomModule", "providing Repository singleton");
        return new AppRepository(requestDao, apiService);
    }


}
