package ru.matorinun.upravbotprof.data;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

public class SharedPrefsHelper {

    public static String TOKEN_ID = "*token-id";
    public static String ID = "*id";
    public static String FIO = "*fio";
    public static String EMAIL = "*e-mail";
    public static String PHONE_NUMBER = "*phone-number";
    public static String PHOTO = "*photo";
    public static String LOGIN = "*login";
    public static String APP_GUID = "*device-id"; // Это ключ приложения, но сервер его рассматривает, как ключ устройства


    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public boolean contains(String key){
        return mSharedPreferences.contains(key);

    }

//    ResultData: {
//        string Result
//        string ErrNo
//        string ErrMsg
//        string ID
//        string FIO
//        string EMAIL
//        string PHONE_NUMBER
//        string PHOTO
//        string LOGIN
//        string TOKENID
//        List<Object> OBJECTS } // Список объектов(адресов домов) для данного техника

}
