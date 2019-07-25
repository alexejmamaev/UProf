package ru.matorinun.upravbotprof.ui.login;

import android.util.Log;

import dagger.Module;
import dagger.Provides;
import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.di.scopes.ActivityScope;


@Module
class LoginActivityModule {

    private LoginActivity loginActivity;


    LoginActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        Log.e("LoginActivityModule", "providing LoginActivity ActivityScope");
        return loginActivity;
    }


    @Provides
    @ActivityScope
    LoginActivityPresenter provideLoginActivityViewModel(Repository repository, SharedPrefsHelper prefsHelper) {
        Log.e("LoginActivityModule", "providing LoginActivityPresenter ActivityScope");
        return new LoginActivityPresenter(loginActivity, repository, prefsHelper);
    }


}
