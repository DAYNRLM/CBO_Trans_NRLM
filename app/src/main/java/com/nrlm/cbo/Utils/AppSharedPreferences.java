package com.nrlm.cbo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {
    private static AppSharedPreferences appSharedPrefrences;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private Context context;


    public AppSharedPreferences(Context context) {
        this.appSharedPrefs = context.getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public static AppSharedPreferences getsharedprefInstances(Context con) {
        if (appSharedPrefrences == null)
            appSharedPrefrences = new AppSharedPreferences(con);
        return appSharedPrefrences;
    }

    public void clearallSharedPrefernce() {
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void removeSharedPrefKey(String key) {
        prefsEditor.remove(key);
        prefsEditor.commit();
    }

    public String getApi() {
        return appSharedPrefs.getString("Api", "");
    }

    public void setApi(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Api", path);
        prefsEditor.apply();
    }

    public String getShgCode() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyShgCode(), "");
    }
    public void setMpinStatus(String path)
    {
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyMpinStatus(),path);
        prefsEditor.apply();
    }
    public void setSelectedShgCutOffSc(String path)
    {
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeySelectedShgCutoff(),path);
        prefsEditor.apply();
    }
    public String getSelectedShgCutOffSc()
    {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeySelectedShgCutoff(),"");
    }

    public String getMpinStatus()
    {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyMpinStatus(),"");
    }

    public void setShgCode(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyShgCode(), path);
        prefsEditor.apply();
    }
    public String getShgCodeForVerification() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyVerifyShgCode(), "");
    }

    public void setCodeForVerification(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyVerifyShgCode(), path);
        prefsEditor.apply();
    }

    public String getVillageCodeSetting() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyVillageCodeForSeeting(), "");
    }

    public void setVillageCodeSetting(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyVillageCodeForSeeting(), path);
        prefsEditor.apply();
    }

    public String getLoginStatus() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForLogin(), "");
    }

    public void setLoginStatus(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForLogin(), path);
        prefsEditor.apply();
    }

    /**************added by abduss****************************/


    /*************added by lincon*****************************/

    /***************set and get for local id ******************/
    public String getCompanyId() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForCompanyId(), "");
    }

    public void setCompanyId(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForCompanyId(), path);
        prefsEditor.apply();
    }

    public String getCompanyBranchId() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForCompanyBranchId(), "");
    }

    public void setCompanyBranchId(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForCompanyBranchId(), path);
        prefsEditor.apply();
    }

    public String getRunningInsuranceId() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForRunningInsuranceId(), "");
    }

    public void setRunningInsuranceId(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForRunningInsuranceId(), path);
        prefsEditor.apply();
    }

    public String getRunningLoanId() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForRunningLoanId(), "");
    }

    public void setRunningLoanId(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForRunningLoanId(), path);
        prefsEditor.apply();
    }


    /**************cutoff screen status**************/

    public String getCutoffScreenStatus() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForCutoffScreenStatus(), "");
    }

    public void setCutoffScreenStatus(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForCutoffScreenStatus(), path);
        prefsEditor.apply();
    }

    /**************setting screen status**************/

    public String getSettingScreenStatus() {
        return appSharedPrefs.getString(PrefrenceManager.getPrefKeyForSettingScreenStatus(), "");
    }

    public void setSettingScreenStatus(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.getPrefKeyForSettingScreenStatus(), path);
        prefsEditor.apply();
    }


    /************added by manish****************/
public void setMemberCutOffSelectMemberCode(String path)
{
    this.prefsEditor=appSharedPrefs.edit();
    prefsEditor.putString(PrefrenceManager.getPrefMembercutoffSelectedmemberCode(),path);
    prefsEditor.apply();
}
public String getMemberCutOffSelectedMemberCode()
{
    return appSharedPrefs.getString(PrefrenceManager.getPrefMembercutoffSelectedmemberCode(),"");
}


    public void removeSelectedPrefKey(){
        removeSharedPrefKey(PrefrenceManager.getPrefKeyForLogin());// remove login key
        removeSharedPrefKey(PrefrenceManager.getPrefKeyVillageCodeForSeeting());// remove villagecode
    }

}
