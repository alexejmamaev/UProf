package ru.matorinun.upravbotprof.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.matorinun.upravbotprof.di.scopes.ApplicationContext;

@Module
public class SharedPrefsModule {

    @Singleton
    @Provides
    SharedPreferences providesSharedPrefs(@ApplicationContext Context context){
        Log.e("SharedPrefsModule", "providing SharedPrefs singleton");
       return context.getSharedPreferences("upravbot-prof-prefs", Context.MODE_PRIVATE);
    }

}
