package com.nrlm.cbo.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import com.nrlm.cbo.BuildConfig;

public class DeviceInfoUtils {
    Context context;
    private TelephonyManager telephonyManager;
    AppUtils appUtility;
    AppSharedPreferences appSharedPreferences;

    public static DeviceInfoUtils deviceInfoutils = null;
    public static DeviceInfoUtils getInstance(Context context) {
        if (deviceInfoutils == null)
            deviceInfoutils = new DeviceInfoUtils(context);
        return deviceInfoutils;
    }
    public DeviceInfoUtils(Context context) {
        this.context = context;
        appUtility =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
    }
    public String getIMEINo1() {
        String imeiNo1 = "";
        try {
            if (getSIMSlotCount() > 0) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                }
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    imeiNo1 = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

                } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imeiNo1 = telephonyManager.getDeviceId(0);

                }else if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                    imeiNo1 ="dummy_123456789";
                }

            } else imeiNo1 = telephonyManager.getDeviceId();
        }catch (Exception e){
            appUtility.showLog("IMEI Exception:- "+e,DeviceInfoUtils.class);
        }
        return imeiNo1;
    }
    private int getSIMSlotCount() {
        int getPhoneCount = 0;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getPhoneCount = telephonyManager.getPhoneCount();
            }
        }catch (Exception e){
            appUtility.showLog("IMEI Sim Slot Exception:- "+e,DeviceInfoUtils.class);
        }
        return getPhoneCount;
    }
    public String getDeviceInfo() {
        String deviceInfo = "";
        try{
            deviceInfo = Build.MANUFACTURER + "-" + Build.DEVICE + "-" + Build.MODEL;
        }catch (Exception e){
            appUtility.showLog("Device info Exception:- "+e,DeviceInfoUtils.class);
        }
        if (deviceInfo.equalsIgnoreCase("")|| deviceInfo==null)
            return "";
        return deviceInfo;
    }
    public String getAppVersionFromApp() {
        String appVersion = "";
        try {
            appVersion =  BuildConfig.VERSION_NAME;;
        }catch (Exception e){
            appUtility.showLog("App Version Exception:- "+e,DeviceInfoUtils.class);
        }
        return appVersion;
    }
    public String getApiVersion(){
       int version = 0;
        try {
             version =  Build.VERSION.SDK_INT;
        }catch (Exception e){
            appUtility.showLog("App Version Exception:- "+e,DeviceInfoUtils.class);
        }
        return String.valueOf(version);
    }

}
