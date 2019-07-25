package ru.matorinun.upravbotprof.networking.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestsResultData {

    @SerializedName("Result")
    @Expose
    private String Result;

    @SerializedName("ErrNo")
    @Expose
    private String ErrNo;

    @SerializedName("ErrMsg")
    @Expose
    private String ErrMsg;

    @SerializedName("REQUESTS")
    @Expose
    private List<ProfRequest> REQUESTS; // массив заявок для техника


    public RequestsResultData(String result, String errNo, String errMsg, List<ProfRequest> REQUESTS) {
        Result = result;
        ErrNo = errNo;
        ErrMsg = errMsg;
        this.REQUESTS = REQUESTS;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getErrNo() {
        return ErrNo;
    }

    public void setErrNo(String errNo) {
        ErrNo = errNo;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public List<ProfRequest> getREQUESTS() {
        return REQUESTS;
    }

    public void setREQUESTS(List<ProfRequest> REQUESTS) {
        this.REQUESTS = REQUESTS;
    }
}
