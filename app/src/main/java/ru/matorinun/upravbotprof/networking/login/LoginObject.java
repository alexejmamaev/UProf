package ru.matorinun.upravbotprof.networking.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginObject {

    @SerializedName("OBJECT_ID")
    @Expose
    private String OBJECT_ID;

    @SerializedName("ADDRESS")
    @Expose
    private String ADDRESS;

    @SerializedName("OBJECT_GUID")
    @Expose
    private String OBJECT_GUID;

    public LoginObject(String OBJECT_ID, String ADDRESS, String OBJECT_GUID) {
        this.OBJECT_ID = OBJECT_ID;
        this.ADDRESS = ADDRESS;
        this.OBJECT_GUID = OBJECT_GUID;
    }

    public String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(String OBJECT_ID) {
        this.OBJECT_ID = OBJECT_ID;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getOBJECT_GUID() {
        return OBJECT_GUID;
    }

    public void setOBJECT_GUID(String OBJECT_GUID) {
        this.OBJECT_GUID = OBJECT_GUID;
    }
}
