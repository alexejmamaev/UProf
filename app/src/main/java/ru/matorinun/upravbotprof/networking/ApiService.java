package ru.matorinun.upravbotprof.networking;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.matorinun.upravbotprof.networking.login.LoginBody;
import ru.matorinun.upravbotprof.networking.login.LoginParent;
import ru.matorinun.upravbotprof.networking.main.RequestsBody;
import ru.matorinun.upravbotprof.networking.main.ProfRequestParent;

public interface ApiService {

    String TEST_URL = "http://31.3.22.233/WCFServices/MATORIN.QUICK_API.svc/";

    @POST("Auth_prof")
    Single<LoginParent> logIn(@Body LoginBody body);

    @POST("Get_profrequest_list")
    Single<ProfRequestParent> getAllRequestsList(@Body RequestsBody body);

}
