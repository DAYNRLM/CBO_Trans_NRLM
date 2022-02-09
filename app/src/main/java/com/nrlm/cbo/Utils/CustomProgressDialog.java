package com.nrlm.cbo.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.nrlm.cbo.R;
import com.nrlm.cbo.view.fragments.cutOffFragment.SHGCutOffFragment;

public class CustomProgressDialog  {

    //public static CustomProgressDialog customProgress = null;
    private ProgressDialog mDialog;
    Context mContext;
    AppUtils appUtility;
    AppSharedPreferences appSharedPreferences;

    public CustomProgressDialog(Context context) {
        this.mContext = context;
        appUtility =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
    }

    public static CustomProgressDialog newInstance(Context context) {
        CustomProgressDialog  customProgress = new CustomProgressDialog(context);
        return customProgress;
    }

   /* public static CustomProgressDialog getInstance(Context context) {
        if (customProgress == null) {
            customProgress = new CustomProgressDialog(context);
        }*//*else {
            customProgress = new CustomProgressDialog(context);
        }*//*
       
        return customProgress;
    }*/


    public void showProgress( String message, boolean cancelable) {
        try {
            mDialog = new ProgressDialog(mContext);
            // no tile for the dialog
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setMessage(message);
            //mDialog.setContentView(R.layout.prograss_bar_dialog);
            // mProgressBar = (ProgressBar) mDialog.findViewById(R.id.progress_bar);
            //  mProgressBar.getIndeterminateDrawable().setColorFilter(context.getResources()
            // .getColor(R.color.material_blue_gray_500), PorterDuff.Mode.SRC_IN);
            // TextView progressText = (TextView) mDialog.findViewById(R.id.progress_text);
            // progressText.setText("" + message);
            // progressText.setVisibility(View.VISIBLE);
            // mProgressBar.setVisibility(View.VISIBLE);
            // you can change or add this line according to your need
            //   mProgressBar.setIndeterminate(true);
            mDialog.setCancelable(cancelable);
            mDialog.setCanceledOnTouchOutside(cancelable);
            mDialog.show();
        }catch (Exception e){
            appUtility.showLog("progress bar expection"+e,CustomProgressDialog.class);
        }


    }

    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;

        }
    }

}
