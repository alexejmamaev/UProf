package ru.matorinun.upravbotprof.ui.main;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.networking.ObserverListener;

// Этот класс нужен для того, чтобы можно было создать MainActivityViewModel с аргументами
// (по-умолчанию ViewModel создается БЕЗ аргументов)
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Repository repository;
    private SharedPrefsHelper prefsHelper;
    private ObserverListener listener;

    MainActivityViewModelFactory(Repository repository, SharedPrefsHelper prefsHelper,
                                 ObserverListener listener) {
        this.repository = repository;
        this.prefsHelper = prefsHelper;
        this.listener = listener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(repository, prefsHelper, listener);
    }



}
