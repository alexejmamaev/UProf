package ru.matorinun.upravbotprof.di.modules;

import android.content.Context;
import android.util.Log;

import dagger.Module;
import dagger.Provides;
import ru.matorinun.upravbotprof.di.scopes.ApplicationContext;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        Log.e("ContextModule", "providing ApplicationContext");
        return context;
    }


}
