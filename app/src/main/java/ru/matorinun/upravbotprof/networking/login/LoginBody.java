package ru.matorinun.upravbotprof.networking.login;


public class LoginBody {

    private String login;
    private String pass;
    private String device_id; //В связи с тем, что Google запретил использовать DEVICE_ID шлем внутренний GUID,
    // который генерим при установке или первом запуске приложения и храним в настройках телефона

    public LoginBody(String login, String pass, String device_id) {
        this.login = login;
        this.pass = pass;
        this.device_id = device_id;
    }


}
