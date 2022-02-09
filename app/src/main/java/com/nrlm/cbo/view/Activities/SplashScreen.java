package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.Utils.DateFactory;
import com.nrlm.cbo.Utils.DeviceInfoUtils;
import com.nrlm.cbo.Utils.LocationMasterUtils;
import com.nrlm.cbo.Utils.MaterialDialogFactory;
import com.nrlm.cbo.webService.volley.VolleyResult;
import com.nrlm.cbo.webService.volley.VolleyService;

public class SplashScreen extends AppCompatActivity {

    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    AppUtils appUtils;
    VolleyResult mResultCallBack = null;
    VolleyService volleyService;
    CustomProgressDialog customProgressDialog;
    MaterialDialogFactory materialDialogFactory;
    DateFactory dateFactory;
    DeviceInfoUtils deviceInfoUtils;
    LocationMasterUtils locationMasterUtils;

    String loginStatus ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getAllInstance();
        loginStatus =appSharedPreferences.getLoginStatus();

        loadNextScreenWithDelay();
    }

    private void getAllInstance() {
        allDataList = AllDataList.getInstance(SplashScreen.this);
        appUtils = AppUtils.getInstance();
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(SplashScreen.this);
        volleyService = VolleyService.getInstance(SplashScreen.this);
        deviceInfoUtils = DeviceInfoUtils.getInstance(SplashScreen.this);
        customProgressDialog = CustomProgressDialog.newInstance(SplashScreen.this);
        materialDialogFactory = MaterialDialogFactory.newInstance(SplashScreen.this);
        dateFactory = DateFactory.getInstance(SplashScreen.this);
        locationMasterUtils = LocationMasterUtils.getInstance(SplashScreen.this);
    }


    private void loadNextScreenWithDelay() {
        android.os.Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(loginStatus.equalsIgnoreCase("done")){
                    appUtils.makeIntent(SplashScreen.this, HomeActivity.class, false);
                }else {
                    Intent intent = new Intent(SplashScreen.this, ShgLoginActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
}