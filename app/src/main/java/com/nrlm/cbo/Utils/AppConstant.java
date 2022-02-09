package com.nrlm.cbo.Utils;

import com.rengwuxian.materialedittext.BuildConfig;

public class AppConstant {
    public static String APP_VERSION = BuildConfig.VERSION_NAME;
    public static final String DIALOG_MSG = "loading...";
    public static final String DELETE_TAG = "delete";
    public static final String EDIT_TAG = "edit";

    /*==========================================================================*/
    public static final int API_TIME_OUT =6*1000;
    public static final String SELECT_RECEIPT_TYPE = "--Select Receipt Type--";
    public static final String SELECT_RECEIPT_SUB_TYPE = "--Select Receipt Sub Type--";
    public static final String SELECT_AGENCY_TYPE = "--Select Agency Type--";
    public static final String SELECT_PAYMENT_TYPE = "--Select Payment Type--";
    public static final String SELECT_PAYMENT_SUB_TYPE = "--Select Payment Sub Type--";
    public static final String SELECT_PAYMENT_AGENCY_TYPE = "--Select Payment Agency Type--";
    public static final String CUT_OFF_RUNNING_LOAN_TAG = "cutOffLoan";

    /*****for local*******/
   /* public static final String HTTP_TYPE = "http";
    public static final String IP_ADDRESS = "10.197.183.198:8080";
    public static final String NRLM_STATUS = "";*/

    /******for live demo******/
    public static final String HTTP_TYPE = "https";
    public static final String IP_ADDRESS = "nrlm.gov.in";
    public static final String NRLM_STATUS = "cboServices";

    /*****for live****/
  /* public static final String HTTP_TYPE="https";
     public static final String IP_ADDRESS="nrlm.gov.in"
     public static final String NRLM_STATUS = "nrlmwebservice";*/


   /**for micro service*/
   /* public static final String HTTP_TYPE = "http";
    public static final String IP_ADDRESS = "10.197.183.198:8080";*/


    public static final String LOGIN_URL = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/login";
    public static final String ASSIGN_SHG_URL = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/shgassign";
    public static final String BANK_BRANCH_DATA = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/bankbranchdata";
    public static final String LITTLE_MASTER_DATA = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/masterdata";
    public static final String SHG_DATA_URL = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/shgdata";
    public static final String MEMBER_DATA_URL = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/shgmemberdata";
    public static final String MASTER_DATA_URL = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/cboTrans/masterdata";
   // public static final String MICRO_LOGIN = HTTP_TYPE + "://" + IP_ADDRESS + "/" + "cbo/login";

    public static final String MICRO_LOGIN =HTTP_TYPE+"://"+ IP_ADDRESS +"/"+NRLM_STATUS +"/cbo/login";
    public static final String MICRO_LOCATION =HTTP_TYPE+"://"+ IP_ADDRESS +"/"+NRLM_STATUS +"/cbo/blockGpVill";
    public static final String MICRO_BANK_BRANCH =HTTP_TYPE+"://"+ IP_ADDRESS +"/"+NRLM_STATUS +"/cbo/bankbranch";
    public static final String MICRO_SHG_DATA_URL =HTTP_TYPE+"://"+ IP_ADDRESS +"/"+NRLM_STATUS +"/cbo/all-Shg";
    public static final String MICRO_MEMBER_DATA_URL =HTTP_TYPE+"://"+ IP_ADDRESS +"/"+NRLM_STATUS +"/cbo/shg-member";
    public static final String MICRO_MASTER_DATA_URL =HTTP_TYPE+"://"+ IP_ADDRESS +"/"+NRLM_STATUS +"/cbo/masters-data";

    /****vpn micro service url*****/
    //http://10.197.183.198:8080/cbo/login

    /*******live micro service url******/
    //https://nrlm.gov.in/cboServices/cbo/login
    //https://nrlm.gov.in/cboServices/cbo/blockGpVill
    //https://nrlm.gov.in/cboServices/cbo/bankbranch
    //https://nrlm.gov.in/cboServices/cbo/all-Shg
    //https://nrlm.gov.in/cboServices/cbo/masters-data
    //https://nrlm.gov.in/cboServices/cbo/shg-member

}
