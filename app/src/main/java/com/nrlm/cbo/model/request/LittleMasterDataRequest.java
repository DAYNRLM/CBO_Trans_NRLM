package com.nrlm.cbo.model.request;

import com.google.gson.Gson;

public class LittleMasterDataRequest {
    private String user_id;
    private String imei_no;
    private String device_name;
    private String location_coordinate;

    public static LittleMasterDataRequest jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, LittleMasterDataRequest.class);
    }

    public String javaToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImei_no() {
        return imei_no;
    }

    public void setImei_no(String imei_no) {
        this.imei_no = imei_no;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getLocation_coordinate() {
        return location_coordinate;
    }

    public void setLocation_coordinate(String location_coordinate) {
        this.location_coordinate = location_coordinate;
    }
}
