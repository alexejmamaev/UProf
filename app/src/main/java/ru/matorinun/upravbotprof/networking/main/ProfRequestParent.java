package ru.matorinun.upravbotprof.networking.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProfRequestParent {

    @SerializedName("ResultData")
    @Expose
    private RequestsResultData resultData;


    public RequestsResultData getResultData() {
        return resultData;
    }

    public void setResultData(RequestsResultData resultData) {
        this.resultData = resultData;
    }
}
