package ru.matorinun.upravbotprof.data.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// Room DB Entity

@Entity
public class RequestEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

//    @ColumnInfo(name = "REQUEST_ID")
    private String REQUEST_ID; // идентификатор заявки в УПРАВБОТЕ

    private String MOBILE_NUMBER; // идентификатор заявки(номер) в мобильном приложении

    private String LOG_IN_ID; // идентификатор создателя заявки в управбот

    private String FIO; // ФИО заявителя

    private String PHONE_NUMBER; // телефон заявителя

    private String ADDRESS; // адрес заявителя

    private String ROOM; // помещение заявителя

    private String OBJECT_ID; // идентификатор дома в управботе

    private String CR_DATE; // дата и время создания

    private String REQUEST_TEXT; // текст заявки

    private String EMERGENCY_TREATMENT; // признак аварийности 1 - аварийная, остальное - нет

    private String ROOM_TYPE; // Тип помещения в управботе

    private String E_MAIL; // почта техника

    private String TECH_NAME; // ФИО техника

    private String TECH_LOGIN; // Логин техника

    private String M_STATUS; // статус заявки из мобильного приложения (GUID)

    private String M_STATUS_ID; // идентификатор статуса из МП

    private String S_NAME; // статус заявки из МП (текстом)

    private String STATUS; // статус заявки в управботе

    private String STATUS_ID; // идентификатор статуса из управбот

    public RequestEntity(int id, String REQUEST_ID, String MOBILE_NUMBER, String LOG_IN_ID, String FIO,
                         String PHONE_NUMBER, String ADDRESS, String ROOM, String OBJECT_ID, String CR_DATE,
                         String REQUEST_TEXT, String EMERGENCY_TREATMENT, String ROOM_TYPE, String E_MAIL,
                         String TECH_NAME, String TECH_LOGIN, String M_STATUS, String M_STATUS_ID,
                         String S_NAME, String STATUS, String STATUS_ID) {
        this.id = id;
        this.REQUEST_ID = REQUEST_ID;
        this.MOBILE_NUMBER = MOBILE_NUMBER;
        this.LOG_IN_ID = LOG_IN_ID;
        this.FIO = FIO;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.ADDRESS = ADDRESS;
        this.ROOM = ROOM;
        this.OBJECT_ID = OBJECT_ID;
        this.CR_DATE = CR_DATE;
        this.REQUEST_TEXT = REQUEST_TEXT;
        this.EMERGENCY_TREATMENT = EMERGENCY_TREATMENT;
        this.ROOM_TYPE = ROOM_TYPE;
        this.E_MAIL = E_MAIL;
        this.TECH_NAME = TECH_NAME;
        this.TECH_LOGIN = TECH_LOGIN;
        this.M_STATUS = M_STATUS;
        this.M_STATUS_ID = M_STATUS_ID;
        this.S_NAME = S_NAME;
        this.STATUS = STATUS;
        this.STATUS_ID = STATUS_ID;
    }

    /* Игнорирует данный конструктор из логики обработки Room, т.к. при создании элемента мы не
         знаем какой будет индекс элемента */
    @Ignore
    public RequestEntity(String REQUEST_ID, String MOBILE_NUMBER, String LOG_IN_ID, String FIO,
                         String PHONE_NUMBER, String ADDRESS, String ROOM, String OBJECT_ID, String CR_DATE,
                         String REQUEST_TEXT, String EMERGENCY_TREATMENT, String ROOM_TYPE, String E_MAIL,
                         String TECH_NAME, String TECH_LOGIN, String M_STATUS, String M_STATUS_ID,
                         String S_NAME, String STATUS, String STATUS_ID) {
        this.REQUEST_ID = REQUEST_ID;
        this.MOBILE_NUMBER = MOBILE_NUMBER;
        this.LOG_IN_ID = LOG_IN_ID;
        this.FIO = FIO;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.ADDRESS = ADDRESS;
        this.ROOM = ROOM;
        this.OBJECT_ID = OBJECT_ID;
        this.CR_DATE = CR_DATE;
        this.REQUEST_TEXT = REQUEST_TEXT;
        this.EMERGENCY_TREATMENT = EMERGENCY_TREATMENT;
        this.ROOM_TYPE = ROOM_TYPE;
        this.E_MAIL = E_MAIL;
        this.TECH_NAME = TECH_NAME;
        this.TECH_LOGIN = TECH_LOGIN;
        this.M_STATUS = M_STATUS;
        this.M_STATUS_ID = M_STATUS_ID;
        this.S_NAME = S_NAME;
        this.STATUS = STATUS;
        this.STATUS_ID = STATUS_ID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

    public String getMOBILE_NUMBER() {
        return MOBILE_NUMBER;
    }

    public void setMOBILE_NUMBER(String MOBILE_NUMBER) {
        this.MOBILE_NUMBER = MOBILE_NUMBER;
    }

    public String getLOG_IN_ID() {
        return LOG_IN_ID;
    }

    public void setLOG_IN_ID(String LOG_IN_ID) {
        this.LOG_IN_ID = LOG_IN_ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getROOM() {
        return ROOM;
    }

    public void setROOM(String ROOM) {
        this.ROOM = ROOM;
    }

    public String getOBJECT_ID() {
        return OBJECT_ID;
    }

    public void setOBJECT_ID(String OBJECT_ID) {
        this.OBJECT_ID = OBJECT_ID;
    }

    public String getCR_DATE() {
        return CR_DATE;
    }

    public void setCR_DATE(String CR_DATE) {
        this.CR_DATE = CR_DATE;
    }

    public String getREQUEST_TEXT() {
        return REQUEST_TEXT;
    }

    public void setREQUEST_TEXT(String REQUEST_TEXT) {
        this.REQUEST_TEXT = REQUEST_TEXT;
    }

    public String getEMERGENCY_TREATMENT() {
        return EMERGENCY_TREATMENT;
    }

    public void setEMERGENCY_TREATMENT(String EMERGENCY_TREATMENT) {
        this.EMERGENCY_TREATMENT = EMERGENCY_TREATMENT;
    }

    public String getROOM_TYPE() {
        return ROOM_TYPE;
    }

    public void setROOM_TYPE(String ROOM_TYPE) {
        this.ROOM_TYPE = ROOM_TYPE;
    }

    public String getE_MAIL() {
        return E_MAIL;
    }

    public void setE_MAIL(String e_MAIL) {
        E_MAIL = e_MAIL;
    }

    public String getTECH_NAME() {
        return TECH_NAME;
    }

    public void setTECH_NAME(String TECH_NAME) {
        this.TECH_NAME = TECH_NAME;
    }

    public String getTECH_LOGIN() {
        return TECH_LOGIN;
    }

    public void setTECH_LOGIN(String TECH_LOGIN) {
        this.TECH_LOGIN = TECH_LOGIN;
    }

    public String getM_STATUS() {
        return M_STATUS;
    }

    public void setM_STATUS(String m_STATUS) {
        M_STATUS = m_STATUS;
    }

    public String getM_STATUS_ID() {
        return M_STATUS_ID;
    }

    public void setM_STATUS_ID(String m_STATUS_ID) {
        M_STATUS_ID = m_STATUS_ID;
    }

    public String getS_NAME() {
        return S_NAME;
    }

    public void setS_NAME(String s_NAME) {
        S_NAME = s_NAME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS_ID() {
        return STATUS_ID;
    }

    public void setSTATUS_ID(String STATUS_ID) {
        this.STATUS_ID = STATUS_ID;
    }

    @Override
    public String toString(){
        return "RequestEntity{" +
                "id=" + id +
                ", REQUEST_ID= " + REQUEST_ID + '\'' +
                ", MOBILE_NUMBER= " + MOBILE_NUMBER + '\'' +
                ", LOG_IN_ID= " + LOG_IN_ID + '\'' +
                ", FIO= " + FIO + '\'' +
                ", PHONE_NUMBER= " + PHONE_NUMBER + '\'' +
                ", ADDRESS= " + ADDRESS + '\'' +
                ", ROOM= " + ROOM + '\'' +
                ", OBJECT_ID= " + OBJECT_ID + '\'' +
                ", CR_DATE= " + CR_DATE + '\'' +
                ", REQUEST_TEXT= " + REQUEST_TEXT + '\'' +
                ", EMERGENCY_TREATMENT= " + EMERGENCY_TREATMENT + '\'' +
                ", ROOM_TYPE= " + ROOM_TYPE + '\'' +
                ", E_MAIL= " + E_MAIL + '\'' +
                ", TECH_NAME= " + TECH_NAME + '\'' +
                ", TECH_LOGIN= " + TECH_LOGIN + '\'' +
                ", M_STATUS_ID= " + M_STATUS_ID + '\'' +
                ", S_NAME= " + S_NAME + '\'' +
                ", STATUS= " + STATUS + '\'' +
                ", STATUS_ID= " + STATUS_ID + '\'' +
                '}';
    }



}
