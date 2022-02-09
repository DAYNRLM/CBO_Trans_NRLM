package com.nrlm.cbo.model.response;

import com.google.gson.Gson;

import java.io.Serializable;

public class LoginResponse  implements Serializable {

     /*{"status":1,"error":{"code":"E200","message":"Success"},"login_id":null,
    "server_date_time":null,"mobile_number":null,"state_code":null,"state_short_name":null,
    "logout_days":null,"language_id":0,"month":0,"clientId":"n{j5Y[<!Ps*HWCWg ","endPointUrl":"login",
    "loginAttempt":0,"hours":0,"minuts":0,"days":0}*/

    private String login_id;
    private String server_date_time;
    private String mobile_number;
    private String state_code;
    private String state_short_name;
    private String logout_days;
    private int language_id;
    private String clientId;
    private String endPointUrl;
    private String loginAttempt;

    public static LoginResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, LoginResponse.class);
    }

    public String javaToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getServer_date_time() {
        return server_date_time;
    }

    public void setServer_date_time(String server_date_time) {
        this.server_date_time = server_date_time;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_short_name() {
        return state_short_name;
    }

    public void setState_short_name(String state_short_name) {
        this.state_short_name = state_short_name;
    }

    public String getLogout_days() {
        return logout_days;
    }

    public void setLogout_days(String logout_days) {
        this.logout_days = logout_days;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    public String getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(String loginAttempt) {
        this.loginAttempt = loginAttempt;
    }


}
