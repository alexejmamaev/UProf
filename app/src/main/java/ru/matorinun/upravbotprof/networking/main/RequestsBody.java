package ru.matorinun.upravbotprof.networking.main;

public class RequestsBody {

    private String tokenID;
    private String STATUS_ID; //если NULL, то выберет все заявки

    public RequestsBody(String tokenID, String STATUS_ID) {
        this.tokenID = tokenID;
        this.STATUS_ID = STATUS_ID;
    }
}
