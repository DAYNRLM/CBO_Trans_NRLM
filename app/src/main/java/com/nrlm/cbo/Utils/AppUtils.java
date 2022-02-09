package com.nrlm.cbo.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class AppUtils {
    public static AppUtils utilsInstance;
    private static boolean wantToShow = true;

    public synchronized static AppUtils getInstance() {
        if (utilsInstance == null) {
            utilsInstance = new AppUtils();
        }
        return utilsInstance;
    }


    public void showLog(String logMsg, Class application) {
        if (wantToShow) {
            Log.d(application.getName(), logMsg);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus()) {
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        }
    }
/*
@param String
 */
    public void makeIntent(Context context, Class activityToGo, boolean clearBackActitvity) {
        if (context != null) {
            Intent intent = new Intent(context, activityToGo);

            if (clearBackActitvity) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

            context.startActivity(intent);
        }
    }

    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, String tag, boolean addTobackStack, int container) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (addTobackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(container, fragment, tag);
        ft.commit();
    }

    public void removeFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    public String loadAssetData(Context context, String filName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public String getRandomOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return "" + otp;
    }

    @NonNull
    public String getSha256(@NonNull String plain_text) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = null;
        hash = digest.digest(plain_text.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    @NonNull
    private String bytesToHex(@NonNull byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
