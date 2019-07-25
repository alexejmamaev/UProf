package ru.matorinun.upravbotprof.di.modules;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.matorinun.upravbotprof.di.scopes.ApplicationContext;
import ru.matorinun.upravbotprof.networking.ApiService;

@Module
public class RetrofitApiModule {


    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Cache cache = new Cache(context.getCacheDir(), 5 * 1024 * 1024); // 5 MB

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS);

        Log.e("RetrofitApiModule", "providing OkHttpClient singleton");
        return clientBuilder.build();

    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client){
        Log.e("RetrofitApiModule", "providing Retrofit instance singleton");
        return new retrofit2.Retrofit.Builder()
                .baseUrl(ApiService.TEST_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    @Singleton
    @Provides
    ApiService providesApiService(Retrofit retrofit){
        Log.e("RetrofitApiModule", "providing ApiService singleton");
        return retrofit.create(ApiService.class);
    }
}
