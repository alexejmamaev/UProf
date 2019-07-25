package ru.matorinun.upravbotprof.ui.main;

import dagger.Component;
import ru.matorinun.upravbotprof.di.AppComponent;
import ru.matorinun.upravbotprof.di.scopes.ActivityScope;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainComponent {

    MainActivity inject(MainActivity mainActivity);

    MainActivityViewModel viewModel();

}
