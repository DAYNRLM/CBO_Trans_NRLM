package com.nrlm.cbo.model.request;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("password")
    private String password;
    @SerializedName("imei_no")
    private String imeiNo;
    @SerializedName("device_name")
    private String deviceName;
    @SerializedName("app_versions")
    private String appVersion;
    @SerializedName("date")
    private String todayDate;
    @SerializedName("android_version")
    private String androidVersion;
    @SerializedName("location_coordinate")
    private String locCoordinate;
    @SerializedName("android_api_version")
    private String androidApiVersion;
    @SerializedName("logout_time")
    private String logoutTime;
    @SerializedName("app_login_time")
    private String appLoginTime;

    /*{
    "user_id": "MCBORJJPLINCON",
    "password": "c6024fd19953c32dc6e2b8fe91684a16a889cc8482157f1ec652616517537239",
    "imei_no": "111",
    "device_name": "111",
    "app_versions": "1.0.0",
    "date": "23-02-2021",
    "android_version": "111",
    "location_coordinate": "111",
    "android_api_version": "111",
    "logout_time": "2019-10-17 11:34:40.00419",
    "app_login_time": "111474"

}*/

    public static LoginRequest jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, LoginRequest.class);
    }

    public String javaToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getLocCoordinate() {
        return locCoordinate;
    }

    public void setLocCoordinate(String locCoordinate) {
        this.locCoordinate = locCoordinate;
    }

    public String getAndroidApiVersion() {
        return androidApiVersion;
    }

    public void setAndroidApiVersion(String androidApiVersion) {
        this.androidApiVersion = androidApiVersion;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getAppLoginTime() {
        return appLoginTime;
    }

    public void setAppLoginTime(String appLoginTime) {
        this.appLoginTime = appLoginTime;
    }
}
