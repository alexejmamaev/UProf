package ru.matorinun.upravbotprof;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.di.AppComponent;


import ru.matorinun.upravbotprof.di.DaggerAppComponent;
import ru.matorinun.upravbotprof.di.modules.ContextModule;
import ru.matorinun.upravbotprof.di.modules.RetrofitApiModule;
import ru.matorinun.upravbotprof.di.modules.RoomModule;
import ru.matorinun.upravbotprof.di.modules.SharedPrefsModule;

public class UpravbotApplication extends Application {

    private static final String LOG = UpravbotApplication.class.getSimpleName();

    private static AppComponent appComponent;

    @Inject
    SharedPrefsHelper prefsHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .roomModule(new RoomModule())
                .retrofitApiModule(new RetrofitApiModule())
                .sharedPrefsModule(new SharedPrefsModule())
                .build();
        appComponent.inject(this);

        generateAppGUID();

        setRXJavaExceptionsHandler();

    }

    private void generateAppGUID(){
        String appGUID;

        if(prefsHelper.contains(SharedPrefsHelper.APP_GUID)){
            appGUID = prefsHelper.get(SharedPrefsHelper.APP_GUID, "");

            if(!TextUtils.isEmpty(appGUID) && !appGUID.equalsIgnoreCase("null")
                    && !appGUID.equals("0")){
                return;
            }

        }

        appGUID = UUID.randomUUID().toString();
        prefsHelper.put(SharedPrefsHelper.APP_GUID, appGUID);
        Log.e(LOG, "Generating appGUID " + appGUID);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    private void setRXJavaExceptionsHandler(){
        RxJavaPlugins.setErrorHandler(throwable -> {

            if (throwable instanceof UndeliverableException) {
                // Merely log undeliverable exceptions
                Log.e(LOG,  "RxJava UndeliverableException -> " + throwable);
            } else {
                Log.e(LOG, "RxJava Exception -> " + throwable);

            }


        });
    }

}
