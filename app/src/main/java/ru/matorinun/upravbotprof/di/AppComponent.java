package ru.matorinun.upravbotprof.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.matorinun.upravbotprof.UpravbotApplication;
import ru.matorinun.upravbotprof.data.Repository;
import ru.matorinun.upravbotprof.data.SharedPrefsHelper;
import ru.matorinun.upravbotprof.data.room.RequestDao;
import ru.matorinun.upravbotprof.di.modules.RetrofitApiModule;
import ru.matorinun.upravbotprof.di.modules.RoomModule;
import ru.matorinun.upravbotprof.di.modules.ContextModule;
import ru.matorinun.upravbotprof.di.modules.SharedPrefsModule;
import ru.matorinun.upravbotprof.networking.ApiService;


//This is an application scope component. Has no dependencies cos it's the lowest scope component.
@Singleton
@Component(modules = {ContextModule.class, RoomModule.class,
        RetrofitApiModule.class, SharedPrefsModule.class})
public interface AppComponent {

    // Defines the place where dependencies are injected
    void inject(UpravbotApplication application);

    /* --- Dependencies below should be visible out of the component ---*/
    Repository requestRepository();

    SharedPrefsHelper sharedPrefsHelper();

    RequestDao requestDao();

    //    @ApplicationContext Context context();
    //
    //    AppDatabase appDatabase();

    ApiService apiService(); //Retrofit end-point

}
