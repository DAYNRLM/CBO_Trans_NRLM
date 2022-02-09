package com.nrlm.cbo.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginErrorResponse implements Serializable {

    public static LoginErrorResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, LoginErrorResponse.class);
    }

    public String javaToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private LoginErrorDataResponse loginErrorDataResponse;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LoginErrorDataResponse getLoginErrorDataResponse() {
        return loginErrorDataResponse;
    }

    public void setLoginErrorDataResponse(LoginErrorDataResponse loginErrorDataResponse) {
        this.loginErrorDataResponse = loginErrorDataResponse;
    }

    public  class LoginErrorDataResponse implements Serializable{

     /*   @SerializedName("login_attempt")
        private String loginAttempts;*/
        @SerializedName("status")
        private String status;

      /*  public String getLoginAttempts() {
            return loginAttempts;
        }

        public void setLoginAttempts(String loginAttempts) {
            this.loginAttempts = loginAttempts;
        }*/

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
