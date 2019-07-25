package ru.matorinun.upravbotprof.ui.login;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.matorinun.upravbotprof.R;
import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.networking.login.LoginBody;
import ru.matorinun.upravbotprof.networking.login.LoginParent;
import ru.matorinun.upravbotprof.networking.login.LoginResultData;
import ru.matorinun.upravbotprof.ui.main.MainActivity;


// MVP architecture presenter class
class LoginActivityPresenter {

    private LoginActivity activity;
    private Repository repository;
    private SharedPrefsHelper prefsHelper;

     LoginActivityPresenter(LoginActivity activity, Repository repository,
                            SharedPrefsHelper prefsHelper) {
        this.activity = activity;
        this.repository = repository;
        this.prefsHelper = prefsHelper;
    }



    // Sends POST request to server and, if successful, gets the result (LoginParent object)
    Disposable getDisposable(String login, String password){

         activity.showLoading(true);

         String GUID = prefsHelper.get(SharedPrefsHelper.APP_GUID, "");

        //TODO 2. LoginBody создается с параметрами, которые можно получить только в Активити.
        // Можно ли это как-то сделать лучше, чтобы не создавать new LoginBody???
        // И еще тут создается new DisposableSingleObserver ???? Хотя в примерах в инете в таких ситуациях
        // тоже создают новый Observer...
        return repository.loginParent(new LoginBody(login, password, GUID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginParent>() {

                    @Override
                    public void onSuccess(LoginParent parent) {

                        if(parent != null){

                            LoginResultData resultData = parent.getResultData();
                            String result = resultData.getResult();

                            if(result.equalsIgnoreCase("Success")){

                                prefsHelper.put(SharedPrefsHelper.LOGIN, login);
                                prefsHelper.put(SharedPrefsHelper.APP_GUID, GUID);
                                prefsHelper.put(SharedPrefsHelper.FIO, resultData.getFIO());
                                prefsHelper.put(SharedPrefsHelper.ID, resultData.getID());
                                prefsHelper.put(SharedPrefsHelper.PHONE_NUMBER, resultData.getPHONE_NUMBER());
                                prefsHelper.put(SharedPrefsHelper.TOKEN_ID, resultData.getTOKENID());

                                launchMainActivity();
                            }

                        }
                        activity.showLoading(false);
                        activity.setSnackbar(activity.getString(R.string.wrong_login_or_password_input));

                    }

                    @Override
                    public void onError(Throwable e) {
                        activity.showLoading(false);
                        activity.setSnackbar(activity.getString(R.string.connection_error));
                    }
                });
    }



    void profileValidationCheck(){
        if(prefsHelper.contains(SharedPrefsHelper.TOKEN_ID)){
            String tokenID = prefsHelper.get(SharedPrefsHelper.TOKEN_ID, "");

            Log.e("LoginActivity", "tokenID = " + tokenID);

            if(!TextUtils.isEmpty(tokenID) && !tokenID.equalsIgnoreCase("0")
                    && !tokenID.equalsIgnoreCase("null")){
                launchMainActivity();
            }
        }

    }


    private void launchMainActivity(){
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();

    }


}
