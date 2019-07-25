package ru.matorinun.upravbotprof.networking.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginParent {

    @SerializedName("ResultData")
    @Expose
    private LoginResultData resultData;

    public LoginResultData getResultData() {
        return resultData;
    }

    public void setResultData(LoginResultData resultData) {
        this.resultData = resultData;
    }
}
