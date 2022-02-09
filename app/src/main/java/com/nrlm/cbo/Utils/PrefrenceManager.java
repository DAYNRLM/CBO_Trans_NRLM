package com.nrlm.cbo.Utils;

public class PrefrenceManager {
    /*****added by lincon******/
    private static final String PREF_KEY_SHG_CODE = "shgCode";
    private static final String PREF_KEY_VERIFY_SHG_CODE = "shgCodeVerify";
    private static final String PREF_KEY_VILLAGE_CODE_FOR_SEETING = "shgVillageCodeSeeting";
    private static final String PREF_KEY_MPIN_STATUS="mpinStatus";
    private static final String PREF_KEY_FOR_LOGIN="loginStatus";
    private static final String PREF_KEY_SELECTED_SHG_CUTOFF="selectedShgCode";
    private static final String PREF_KEY_FOR_COMPANY_ID="localCompanyId";
    private static final String PREF_KEY_FOR_COMPANY_BRANCH_ID="localCompanyBranchId";
    private static final String PREF_KEY_FOR_CUTOFF_SCREEN_STATUS="cutOffScreenStatus";
    private static final String PREF_KEY_FOR_SETTING_SCREEN_STATUS="settingScreenStatus";
    private static final String PREF_KEY_FOR_RUNNING_LOAN_ID="runningLoanId";
    private static final String PREF_KEY_FOR_RUNNING_INSURANCE_ID="runningInsuranceId";

    public static final String PREF_SHG_CODE="shgcode";
    public static final String PREF_ENTITY_CODE="entitycode";




    /***********manish*******************/
public static final String PREF_MEMBERCUTOFF_SELECTEDMEMBER_CODE="membersSelectedCode";

    public static String getPrefMembercutoffSelectedmemberCode() {
        return PREF_MEMBERCUTOFF_SELECTEDMEMBER_CODE;
    }

    /**********abduss*********************/





    /******************lincon************************************/
    public static String getPrefKeyForSettingScreenStatus() {
        return PREF_KEY_FOR_SETTING_SCREEN_STATUS;
    }

    public static String getPrefKeySelectedShgCutoff() {
        return PREF_KEY_SELECTED_SHG_CUTOFF;
    }

    public static String getPrefKeyForRunningInsuranceId() {
        return PREF_KEY_FOR_RUNNING_INSURANCE_ID;
    }

    public static String getPrefKeyForRunningLoanId() {
        return PREF_KEY_FOR_RUNNING_LOAN_ID;
    }

    public static String getPrefKeyForCutoffScreenStatus() {
        return PREF_KEY_FOR_CUTOFF_SCREEN_STATUS;
    }

    public static String getPrefKeyForCompanyId() {
        return PREF_KEY_FOR_COMPANY_ID;
    }

    public static String getPrefKeyForCompanyBranchId() {
        return PREF_KEY_FOR_COMPANY_BRANCH_ID;
    }

    public static String getPrefKeyForLogin() {
        return PREF_KEY_FOR_LOGIN;
    }

    public static String getPrefKeyMpinStatus() {
        return PREF_KEY_MPIN_STATUS;
    }

    public static String getPrefKeyVillageCodeForSeeting() {
        return PREF_KEY_VILLAGE_CODE_FOR_SEETING;
    }

    public static String getPrefKeyVerifyShgCode() {
        return PREF_KEY_VERIFY_SHG_CODE;
    }

    public static String getPrefKeyShgCode() {
        return PREF_KEY_SHG_CODE;
    }

    /*=======================================================================================================*/

    public static String getPrefEntityCode() {
        return PREF_ENTITY_CODE;
    }

    public static String getPrefShgCode() {
        return PREF_SHG_CODE;
    }

    /*********************manish*********************/

    /*****************abduss***********************/
}
