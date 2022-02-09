package com.nrlm.cbo.webService.volley;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyResult {
    void notifySuccess(String requestType, JSONObject response);

    // public void notifySuccess(String requestType, String response);
    void notifyError(String requestType, VolleyError error);
}
