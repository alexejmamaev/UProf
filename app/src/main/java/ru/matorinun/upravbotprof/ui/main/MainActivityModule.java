package ru.matorinun.upravbotprof.ui.main;

import android.util.Log;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.di.scopes.ActivityScope;
import ru.matorinun.upravbotprof.networking.ObserverListener;

@Module
class MainActivityModule {

    private MainActivity mainActivity;
    private ObserverListener observerListener;
    private ItemClickListener clickListener;

    MainActivityModule(MainActivity mainActivity, ObserverListener observerListener,
                       ItemClickListener clickListener){
        this.mainActivity = mainActivity;
        this.observerListener = observerListener;
        this.clickListener = clickListener;
    }

    @Provides
    @ActivityScope
    MainActivityViewModelFactory providesMainActivityViewModelFactory
            (Repository repository, SharedPrefsHelper prefsHelper){
        Log.e("MainActivityModule", "providing MainActivityViewModelFactory ActivityScope");
        return new MainActivityViewModelFactory(repository, prefsHelper, observerListener);
    }


    @Provides
    @ActivityScope
    MainActivityViewModel providesMainActivityViewModel(MainActivityViewModelFactory factory){
        Log.e("MainActivityModule", "providing LoginActivity ActivityScope");
        return ViewModelProviders.of(mainActivity, factory).get(MainActivityViewModel.class);
    }


    @Provides
    @ActivityScope
    CompositeDisposable providesCompositeDisposable(){
        return new CompositeDisposable();
    }



    @Provides
    @ActivityScope
    RequestsHistoryAdapter providesRequestsHistoryAdapter(){
        return new RequestsHistoryAdapter(mainActivity, clickListener);
    }


    @Provides
    @ActivityScope
    LinearLayoutManager providesLinearLayoutManager(){
        return new LinearLayoutManager(mainActivity);
    }

    @Provides
    @ActivityScope
    DividerItemDecoration providesDividerItemDecoration(){
        return new DividerItemDecoration(mainActivity, providesLinearLayoutManager().getOrientation());
    }


}
