package com.nrlm.cbo.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nrlm.cbo.R;

public class MaterialDialogFactory extends Activity {
   // public static MaterialDialogFactory materialDialogFactory = null;
    Context mContext;
    AppUtils appUtility;
    AppSharedPreferences appSharedPreferences;

    public MaterialDialogFactory(Context context) {
        this.mContext = context;
        appUtility =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
    }

    public static MaterialDialogFactory newInstance(Context context) {
        MaterialDialogFactory  materialDialogFactory = new MaterialDialogFactory(context);
        return materialDialogFactory;
    }
   /* public static MaterialDialogFactory getInstance(Context context) {
        if (materialDialogFactory == null) {
            materialDialogFactory = new MaterialDialogFactory(context);
        }
        return materialDialogFactory;
    }*/


    public MaterialAlertDialogBuilder showExceptionDialog(String exception, String positiveText, DialogInterface.OnClickListener dialogPositiveClickListener,boolean status){
        /*MaterialAlertDialogBuilder materialAlertDialogBuilder =new MaterialAlertDialogBuilder(mContext);
        View customLayout = getLayoutInflater().inflate(R.layout.shg_saving_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog d =materialAlertDialogBuilder.show();*/

        MaterialAlertDialogBuilder materialAlertDialogBuilder =new MaterialAlertDialogBuilder(mContext);
        materialAlertDialogBuilder.setTitle("Application Error");
        materialAlertDialogBuilder.setMessage(exception);
        materialAlertDialogBuilder.setPositiveButton(positiveText,dialogPositiveClickListener);
        materialAlertDialogBuilder.setCancelable(status);
        materialAlertDialogBuilder.show();
        return materialAlertDialogBuilder;

    }

    /**********Newly added*******************/
    public MaterialAlertDialogBuilder alertDialog(String exception, String positiveText, DialogInterface.OnClickListener dialogPositiveClickListener,boolean status){
        /*MaterialAlertDialogBuilder materialAlertDialogBuilder =new MaterialAlertDialogBuilder(mContext);
        View customLayout = getLayoutInflater().inflate(R.layout.shg_saving_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog d =materialAlertDialogBuilder.show();*/

        MaterialAlertDialogBuilder materialAlertDialogBuilder =new MaterialAlertDialogBuilder(mContext);
        materialAlertDialogBuilder.setTitle("Alert!");
        materialAlertDialogBuilder.setMessage(exception);
        materialAlertDialogBuilder.setPositiveButton(positiveText,dialogPositiveClickListener);
        materialAlertDialogBuilder.setCancelable(status);
        materialAlertDialogBuilder.show();
        return materialAlertDialogBuilder;

    }
}
