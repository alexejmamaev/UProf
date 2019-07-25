package ru.matorinun.upravbotprof.networking.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResultData {

    @SerializedName("Result")
    @Expose
    private String Result;

    @SerializedName("ErrNo")
    @Expose
    private String ErrNo;

    @SerializedName("ErrMsg")
    @Expose
    private String ErrMsg;

    @SerializedName("ID")
    @Expose
    private String ID;

    @SerializedName("FIO")
    @Expose
    private String FIO;

    @SerializedName("EMAIL")
    @Expose
    private String EMAIL;

    @SerializedName("PHONE_NUMBER")
    @Expose
    private String PHONE_NUMBER;

    @SerializedName("PHOTO")
    @Expose
    private String PHOTO;

    @SerializedName("LOGIN")
    @Expose
    private String LOGIN;

    @SerializedName("TOKENID")
    @Expose
    private String TOKENID;

    @SerializedName("OBJECTS")
    @Expose
    private List<LoginObject> OBJECTS; // Список объектов(адресов домов) для данного техника


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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getTOKENID() {
        return TOKENID;
    }

    public void setTOKENID(String TOKENID) {
        this.TOKENID = TOKENID;
    }

    public List<LoginObject> getOBJECTS() {
        return OBJECTS;
    }

    public void setOBJECTS(List<LoginObject> OBJECTS) {
        this.OBJECTS = OBJECTS;
    }
}
