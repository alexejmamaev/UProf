package ru.matorinun.upravbotprof.ui.login;

import dagger.Component;
import ru.matorinun.upravbotprof.di.AppComponent;
import ru.matorinun.upravbotprof.di.scopes.ActivityScope;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginActivityModule.class)
public interface LoginComponent {

    LoginActivity inject(LoginActivity loginActivity);

    LoginActivityPresenter presenter();


}
