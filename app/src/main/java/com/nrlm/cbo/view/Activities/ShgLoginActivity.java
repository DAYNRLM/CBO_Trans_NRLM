package com.nrlm.cbo.view.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.Utils.DateFactory;
import com.nrlm.cbo.Utils.DeviceInfoUtils;
import com.nrlm.cbo.Utils.DialogFactory;
import com.nrlm.cbo.Utils.LocationMasterUtils;
import com.nrlm.cbo.Utils.MaterialDialogFactory;
import com.nrlm.cbo.Utils.ServiceMessage;
import com.nrlm.cbo.Utils.SingletonVolley;
import com.nrlm.cbo.database.room.datamodels.BankBranchModel;
import com.nrlm.cbo.database.room.datamodels.BankModel;
import com.nrlm.cbo.database.room.datamodels.BankTypeModel;
import com.nrlm.cbo.database.room.datamodels.BlockModel;
import com.nrlm.cbo.database.room.datamodels.GpsModel;
import com.nrlm.cbo.database.room.datamodels.LoginModel;
import com.nrlm.cbo.database.room.datamodels.VillageModel;
import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.entities.BankTypeEntity;
import com.nrlm.cbo.database.room.entities.BlockEntity;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.model.request.AssignShgRequest;
import com.nrlm.cbo.model.request.BankAndBranchRequest;
import com.nrlm.cbo.model.request.LoginRequest;
import com.nrlm.cbo.model.response.AssignedLocation;
import com.nrlm.cbo.model.response.BankAndBranchResponse;
import com.nrlm.cbo.model.response.LoginErrorResponse;
import com.nrlm.cbo.model.response.LoginResponse;
import com.nrlm.cbo.webService.volley.VolleyResult;
import com.nrlm.cbo.webService.volley.VolleyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShgLoginActivity extends AppCompatActivity {

    @BindView(R.id.userIdInputLayout)
    TextInputLayout userIdInputLayout;

    @BindView(R.id.passwordInputLayout)
    TextInputLayout passwordInputLayout;

    @BindView(R.id.passwordEt)
    TextInputEditText passwordEt;

    @BindView(R.id.userIdEt)
    TextInputEditText userIdEt;

    @BindView(R.id.loginBtn)
    MaterialButton loginBtn;

    @BindView(R.id.forgetPasswordTv)
    TextView forgetPasswordTv;


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


    String todayDate = "";
    String deviceInfo = "";
    String imeiNumber = "";
    String userId = "";
    String encryptedPassword = "";
    String appVersion = "";
    String locationCorrdinate = "00.00";
    String otpMobileNumber = "";
    String generatedOTP = "";


    private LoginModel loginModel;
    BlockModel blockModel;
    GpsModel gpsModel;
    VillageModel villageModel;
    LoginEntity loginEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_login);
        ButterKnife.bind(this);

        /*String districtCode="MCBOSTDSLINCON".substring(6,8);
        AppUtils.getInstance().showLog("districtCode="+districtCode,ShgLoginActivity.class);*/
        handleSSLHandshake();

        getAllInstance();

        getAllInfo();

        userIdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userIdInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       /* materialDialogFactory.showExceptionDialog("errrrrr", "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        },false);*/

    /*    List<BlockAssign> blocList = AppDataBase.getDatabase(this).bloackCodeDao().loadAllBlockList();
        if (blocList.isEmpty()) {
            testInserationLocation();
        }*/

    }

    /*****get all instance ********/
    private void getAllInstance() {
        blockModel = new BlockModel(getApplication());
        gpsModel = new GpsModel(getApplication());
        villageModel = new VillageModel(getApplication());
        loginModel = new LoginModel(getApplication());


        allDataList = AllDataList.getInstance(ShgLoginActivity.this);
        appUtils = AppUtils.getInstance();
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ShgLoginActivity.this);
        volleyService = VolleyService.getInstance(ShgLoginActivity.this);
        deviceInfoUtils = DeviceInfoUtils.getInstance(ShgLoginActivity.this);
        // customProgressDialog = CustomProgressDialog.getInstance(ShgLoginActivity.this);
        customProgressDialog = CustomProgressDialog.newInstance(ShgLoginActivity.this);
        // materialDialogFactory = MaterialDialogFactory.getInstance(ShgLoginActivity.this);
        materialDialogFactory = MaterialDialogFactory.newInstance(ShgLoginActivity.this);
        dateFactory = DateFactory.getInstance(ShgLoginActivity.this);
        locationMasterUtils = LocationMasterUtils.getInstance(ShgLoginActivity.this);
    }

    /*****get all device info  ********/
    private void getAllInfo() {
        todayDate = dateFactory.getTodayDate();
        deviceInfo = deviceInfoUtils.getDeviceInfo();
        imeiNumber = deviceInfoUtils.getIMEINo1();
        appVersion = deviceInfoUtils.getAppVersionFromApp();
        locationCorrdinate = locationMasterUtils.getLocation();
    }


    @OnClick(R.id.forgetPasswordTv)
    public void forgetPasswordDialog() {

        MaterialButton cancelBtn, sendOTPBtn;
        TextInputEditText otpMobileEt;
        TextInputLayout mobInputLayout;

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ShgLoginActivity.this);
        View customLayout = getLayoutInflater().inflate(R.layout.forget_password_custom_dialog_layout, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog cusDialog = materialAlertDialogBuilder.show();

        cancelBtn = customLayout.findViewById(R.id.cancelBtn);
        sendOTPBtn = customLayout.findViewById(R.id.sendOTPBtn);
        mobInputLayout = customLayout.findViewById(R.id.mobInputLayout);
        otpMobileEt = customLayout.findViewById(R.id.otpMobileEt);

        otpMobileEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mobInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusDialog.dismiss();
            }
        });
        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpMobileNumber = otpMobileEt.getText().toString().trim();
                if (otpMobileNumber.isEmpty() || otpMobileNumber.length() < 10) {
                    mobInputLayout.setError("Please enter valid mobile number");
                } else {
                    generatedOTP = appUtils.getRandomOtp();
                    // callOTPApi();
                    cusDialog.dismiss();
                }
            }
        });

    }

    @OnClick(R.id.loginBtn)
    public void login() {
        userId = userIdEt.getText().toString().trim();
        encryptedPassword = passwordEt.getText().toString().trim();
        if (userId.equalsIgnoreCase("")) {
            userIdInputLayout.setError("Enter user id first");

        } else if (encryptedPassword.equalsIgnoreCase("")) {
            passwordInputLayout.setError("Enter password first");

        } else {
            try {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUserId("MCBOMPAPANDROID");//userId
                loginRequest.setPassword("c6024fd19953c32dc6e2b8fe91684a16a889cc8482157f1ec652616517537239");//encryptedPassword
                loginRequest.setImeiNo("111");
                loginRequest.setDeviceName("111");
                loginRequest.setAndroidVersion("111");
                loginRequest.setLocCoordinate(locationCorrdinate);
                loginRequest.setAndroidApiVersion("111");
                loginRequest.setAppVersion(appVersion);
                loginRequest.setTodayDate(todayDate);
                loginRequest.setLogoutTime("2019-10-17 11:34:40.00419");
                loginRequest.setAppLoginTime(String.valueOf(new Random().nextInt(1000000)));

                /*{
    "user_id": "MCBORJJPLINCON",
    "password": "c6024fd19953c32dc6e2b8fe91684a16a889cc8482157f1ec652616517537239",
    "imei_no": "111",
    "device_name": "111",
    "app_versions": "1.0.0",
    "date": "19-03-2021",
    "android_version": "111",
    "location_coordinate": "111",
    "android_api_version": "111",
    "logout_time": "2019-10-17 11:34:40.00419",
    "app_login_time": "11122"
}*/

                appUtils.showLog("loginRequest=" + loginRequest.toString(), ShgLoginActivity.class);

                callLoginMicroService(new JSONObject(loginRequest.javaToJson()));
                // callLoginApi(new JSONObject(loginRequest.javaToJson()));

            } catch (Exception e) {
                materialDialogFactory.showExceptionDialog(e.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        }
        // appUtils.makeIntent(ShgLoginActivity.this, ModuleSelectionActivity.class, true);
    }

    private void callLoginMicroService(JSONObject jsonObject) {
        customProgressDialog.showProgress(getString(R.string.loading), false);

        mResultCallBack = new VolleyResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                //  customProgressDialog.hideProgress();
                try {
                    if (response != null) {
                        AppUtils.getInstance().showLog("LoginResponse" + response.toString(), ShgLoginActivity.class);
                        if (response.getInt("status") == 1) {
                            LoginResponse loginResponse = LoginResponse.jsonToJava(String.valueOf(response));
                            /*if server side database will not response then api can return null values in every string field of response */
                            if (loginResponse.getState_code() != null && loginResponse.getState_short_name() != null) {
                                AppUtils.getInstance().showLog("LoginResponsefromBean" + loginResponse.getMobile_number(), ShgLoginActivity.class);
                                insertLoginResInDB(loginResponse);
                            }
                        } else {
                            customProgressDialog.hideProgress();
                            String errorCode = response.getJSONObject("error").getString("code");
                            showServerErrorDialog(errorCode);
                        }

                    }
                } catch (Exception e) {
                    appUtils.showLog("Exception:" + e.toString(), ModuleSelectionActivity.class);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                customProgressDialog.hideProgress();
                materialDialogFactory.showExceptionDialog(error.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        };
        volleyService.postDataVolley("micro_service_request", AppConstant.MICRO_LOGIN, jsonObject, mResultCallBack);

    }

    private void callLoginApi(JSONObject requestObject) {
        customProgressDialog.showProgress("loading", false);
        appUtils.showLog("LoginApi" + AppConstant.LOGIN_URL.toString(), ShgLoginActivity.class);
        JsonObjectRequest loginObjectRequest = new JsonObjectRequest(Request.Method.POST, AppConstant.LOGIN_URL, requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                customProgressDialog.hideProgress();
                if (response != null) {
                    AppUtils.getInstance().showLog("LoginResponse" + response.toString(), ShgLoginActivity.class);
                    try {
                        if (response.has("data")) {
                            JSONObject responseData = response.getJSONObject("data");
                            if (responseData.has("status")) {
                                LoginErrorResponse loginErrorResponse = LoginErrorResponse.jsonToJava(String.valueOf(response));
                                Toast.makeText(getApplicationContext(), "Status: "
                                                + loginErrorResponse.getLoginErrorDataResponse().getStatus()
                                        , Toast.LENGTH_LONG).show();
                            } else if (responseData.has("login_id")) {
                                LoginResponse loginResponse = LoginResponse.jsonToJava(String.valueOf(response));
                                AppUtils.getInstance().showLog("LoginResponsefromBean" + loginResponse.getMobile_number(), ShgLoginActivity.class);
                                insertLoginResInDB(loginResponse);
                            }
                        } else if (response.has("message")) {
                            /*{"message":"failure","status":0}*/
                            if (response.getString("message").equalsIgnoreCase("failure")) {
                                DialogFactory.getInstance().showAlertDialog(ShgLoginActivity.this, 0, getString(R.string.info)
                                        , getString(R.string.server_error_dialog), getString(R.string.ok)
                                        , new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }, null, null, false
                                );
                            }

                        }
                    } catch (JSONException e) {
                        customProgressDialog.hideProgress();
                        appUtils.showLog("LoginResponseExp" + e.toString(), ShgLoginActivity.class);
                        materialDialogFactory.showExceptionDialog(e.toString(), "ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }, false);

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customProgressDialog.hideProgress();
                appUtils.showLog("LoginvolleyErrorExp" + error.toString(), ShgLoginActivity.class);
                materialDialogFactory.showExceptionDialog("LoginMicro: "+error.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        });
        loginObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, 0));

        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(loginObjectRequest);
    }

    private void insertLoginResInDB(LoginResponse loginResponse) {

        LoginEntity loginEntity = new LoginEntity();
        LoginModel loginModel = new LoginModel(getApplication());
        if (loginResponse != null) {
            AppUtils.getInstance().showLog("LoginResponseData -->" + loginResponse.getLogin_id() + "," +
                    loginResponse.getLogin_id() + "," +
                    loginResponse.getLogout_days() + "," +
                    loginResponse.getMobile_number() + "," +
                    loginResponse.getServer_date_time() + "," +
                    loginResponse.getState_code() + "," +
                    loginResponse.getState_short_name() + "," +
                    loginResponse.getLanguage_id(), ShgLoginActivity.class);


            if (loginResponse.getLogin_id() != null && !loginResponse.getLogin_id().isEmpty()) {
                loginEntity.login_id = loginResponse.getLogin_id().trim();
            }
            if (loginResponse.getLogout_days() != null && !loginResponse.getLogout_days().isEmpty()) {
                loginEntity.logout_days = loginResponse.getLogout_days().trim();
            }
            if (loginResponse.getMobile_number() != null && !loginResponse.getMobile_number().isEmpty()) {
                loginEntity.mobile_number = loginResponse.getMobile_number().trim();
            }
            if (loginResponse.getServer_date_time() != null && !loginResponse.getServer_date_time().isEmpty()) {
                loginEntity.server_date_time = loginResponse.getServer_date_time().trim();
            }
            if (loginResponse.getState_code() != null && !loginResponse.getState_code().isEmpty()) {
                loginEntity.state_code = loginResponse.getState_code().trim();
            }
            if (loginResponse.getState_short_name() != null && !loginResponse.getState_short_name().isEmpty()) {
                loginEntity.state_short_name = loginResponse.getState_short_name().trim();
            }
            loginEntity.language_id = loginResponse.getLanguage_id();

            loginModel.insertLoginData(loginEntity);
        }

        AppUtils.getInstance().showLog("Login Data Inserted Successfully", ShgLoginActivity.class);
      ///  AppUtils.getInstance().makeIntent(ShgLoginActivity.this, GrandRecieptActivity.class, true);

        getAssignLocationFromServer();


    }

    private void getAssignLocationFromServer() {
        AssignShgRequest assignShgRequest = new AssignShgRequest();
        try {
            AppUtils.getInstance().showLog("loginModel=loginModel",ShgLoginActivity.class);
            List<LoginEntity> loginEntities=loginModel.getLoginInfo();
            loginEntity=loginEntities.get(0);
            String stateShortName=loginEntity.state_short_name;
            AppUtils.getInstance().showLog("stateShortName="+stateShortName,ShgLoginActivity.class);
            assignShgRequest.setUser_id("MCBOMPAPANDROID");//userId
            assignShgRequest.setImei_no("111");//imeiNumber
            assignShgRequest.setDevice_name("111");//deviceInfo
            assignShgRequest.setLocation_coordinate("111");//locationCorrdinate
            assignShgRequest.setStateShortCode(stateShortName);


            // callAssignShgApi(new JSONObject(assignShgRequest.javaToJson()));
            AppUtils.getInstance().showLog("AssignLocationRequest"+assignShgRequest.toString(),ShgLoginActivity.class);
            callAssignLocationMicroService(new JSONObject(assignShgRequest.javaToJson()));

        } catch (JSONException e) {
            AppUtils.getInstance().showLog("AssignLocationRequestObjectExp" + e.toString(), ShgLoginActivity.class);
        } catch (InterruptedException e) {
            AppUtils.getInstance().showLog("AssignLocationRequestObjectExp" + e.toString(), ShgLoginActivity.class);
        } catch (ExecutionException e) {
            AppUtils.getInstance().showLog("AssignLocationRequestObjectExp" + e.toString(), ShgLoginActivity.class);
        }catch (Exception e){
            AppUtils.getInstance().showLog("AssignLocationRequestObjectExp" + e.toString(), ShgLoginActivity.class);
        }

    }

    private void callAssignLocationMicroService(JSONObject jsonObject) {

        mResultCallBack = new VolleyResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                try {
                    if (response != null) {
                        AppUtils.getInstance().showLog("AssignLocationMicroServiceResponse" + response.toString()
                                , ShgLoginActivity.class);
                        if (response.getInt("status") == 1 ) {
                            AssignedLocation assignedLocation = AssignedLocation.jsonToJava(String.valueOf(response));
                            /*if server side database will not response then api can return null values in every string field of response */
                            if (assignedLocation.getData().size() != 0) {
                                AppUtils.getInstance().showLog("AssignLocationInsertionStart"
                                        , ShgLoginActivity.class);
                                insertAssignShgResInDB(assignedLocation);
                            }
                        } else {
                            customProgressDialog.hideProgress();
                            String errorCode = response.getJSONObject("error").getString("code");
                            showServerErrorDialog(errorCode);
                        }
                    }
                } catch (Exception e) {
                    AppUtils.getInstance().showLog("callAssignLocationMicroService" + e.toString(), ShgLoginActivity.class);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                customProgressDialog.hideProgress();
                materialDialogFactory.showExceptionDialog("BlockGpVillageMicro: "+error.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        };

        volleyService.postDataVolley("BlockGpAndVillage",AppConstant.MICRO_LOCATION,jsonObject,mResultCallBack);

    }

    private void callAssignShgApi(JSONObject jsonObject) {
        customProgressDialog.showProgress("loading", false);
        AppUtils.getInstance().showLog("ASSIGN_SHG_URL" + AppConstant.ASSIGN_SHG_URL, ShgLoginActivity.class);
        JsonObjectRequest assignShgObjectRequest = new JsonObjectRequest(Request.Method.POST, AppConstant.ASSIGN_SHG_URL
                , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                customProgressDialog.hideProgress();
                if (response != null) {
                    AppUtils.getInstance().showLog("AssignShgApiResponse" + response.toString(), ShgLoginActivity.class);
                    AssignedLocation assignedLocation = AssignedLocation.jsonToJava(response.toString());
                    insertAssignShgResInDB(assignedLocation);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customProgressDialog.hideProgress();
                AppUtils.getInstance().showLog("AssignShgApiError" + error.toString(), ShgLoginActivity.class);
            }
        });
        assignShgObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, 0));

        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(assignShgObjectRequest);

    }

    private void insertAssignShgResInDB(AssignedLocation assignedLocation) {

        List<AssignedLocation.AssignedLocationData> blockDataList = assignedLocation.getData();

        for (AssignedLocation.AssignedLocationData blockData : blockDataList) {
            BlockEntity blockEntity = new BlockEntity();

            if (blockData.getBlock_code() != null && !blockData.getBlock_code().isEmpty())
                blockEntity.block_code = blockData.getBlock_code();

            if (blockData.getBlock_code() != null && !blockData.getBlock_code().isEmpty())
                blockEntity.block_name = blockData.getBlock_name();

            blockModel.insertAll(blockEntity);

            List<AssignedLocation.GpDetails> gPsEntityList = blockData.getGp_details();
            for (AssignedLocation.GpDetails gpsData : gPsEntityList) {
                GPsEntity gPsEntity = new GPsEntity();

                if (blockData.getBlock_code() != null && !blockData.getBlock_code().isEmpty())
                    gPsEntity.block_code = blockData.getBlock_code();

                if (gpsData.getGp_code() != null && !gpsData.getGp_code().isEmpty())
                    gPsEntity.gp_code = gpsData.getGp_code();

                if (gpsData.getGp_name() != null && !gpsData.getGp_name().isEmpty())
                    gPsEntity.gp_name = gpsData.getGp_name();

                gpsModel.insertAll(gPsEntity);

                List<AssignedLocation.VillageDetails> villageDataList = gpsData.getVillage_details();
                for (AssignedLocation.VillageDetails villageData : villageDataList) {
                    VillageEntity villageEntity = new VillageEntity();

                    if (villageData.getVillage_code() != null && !villageData.getVillage_code().isEmpty())
                        villageEntity.village_code = villageData.getVillage_code();

                    if (villageData.getVillage_name() != null && !villageData.getVillage_name().isEmpty())
                        villageEntity.village_name = villageData.getVillage_name();

                    if (blockData.getBlock_code() != null && !blockData.getBlock_code().isEmpty())
                        villageEntity.block_code = blockData.getBlock_code();

                    if (gpsData.getGp_code() != null && !gpsData.getGp_code().isEmpty())
                        villageEntity.gp_code = gpsData.getGp_code();

                    villageModel.insertAll(villageEntity);
                }
            }
        }
        AppUtils.getInstance().showLog("AssigShg Data filled successfully", ShgLoginActivity.class);
        getBankDetailsFromServer();
    }

    private void getBankDetailsFromServer() {

        try {

              String loginId=loginEntity.login_id;
              String blockCode="1749002";

              String districtCode=loginId.substring(6,8);
              AppUtils.getInstance().showLog("districtCode="+districtCode+",  loginId="+loginId,ShgLoginActivity.class);

              /*MCBORJJPLINCON*/

              BankAndBranchRequest bankAndBranchRequest = new BankAndBranchRequest();

                 bankAndBranchRequest.setUser_id(loginId);
                 bankAndBranchRequest.setImei_no("111");
                 bankAndBranchRequest.setDevice_name("111");
                 bankAndBranchRequest.setLocation_coordinate("111");
                 bankAndBranchRequest.setDistrict_code(districtCode);
                 bankAndBranchRequest.setBlock_code(blockCode);
                 /* "block_code":"2712010",
        "user_id": "MCBORJJPLINCON",
        "imei_no": "111",
        "device_name": "111",
        "location_coordinate": "111"*/

           AppUtils.getInstance().showLog("bankAndBranchRequest" + bankAndBranchRequest.toString(), ShgLoginActivity.class);

            callBankBranchMicroService(new JSONObject(bankAndBranchRequest.javaToJson()));
           //callBankAndBranchApi(new JSONObject(bankAndBranchRequest.javaToJson()));

        } catch (Exception exception) {
            Toast.makeText(getApplicationContext(), "Exception via calling BankAndBranchApi", Toast.LENGTH_LONG).show();
            AppUtils.getInstance().showLog("BankAndBranchException" + exception.toString(), ShgLoginActivity.class);
        }
    }

    private void callBankBranchMicroService(JSONObject jsonObject) {
        mResultCallBack=new VolleyResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                try {
                    if (response!=null){
                        AppUtils.getInstance().showLog("BankBranchResponse="+response.toString(),ShgLoginActivity.class);
                        if (response.getInt("status")==1){
                            BankAndBranchResponse bankAndBranchResponse=BankAndBranchResponse.jsonToJava(response.toString());
                            if (bankAndBranchResponse.getData().size()>0){
                                insertBnakAndBranchResInDB(bankAndBranchResponse);
                            }
                        }else {
                            customProgressDialog.hideProgress();
                            String errorCode = response.getJSONObject("error").getString("code");
                            showServerErrorDialog(errorCode);
                        }
                    }
                }catch (Exception e){
                    AppUtils.getInstance().showLog("BankBranchApiResponseExp"+e.toString(),ShgLoginActivity.class);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                customProgressDialog.hideProgress();
                materialDialogFactory.showExceptionDialog("BankAndBranchMicro: "+error.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        };
        volleyService.postDataVolley("BankBranchData",AppConstant.MICRO_BANK_BRANCH,jsonObject,mResultCallBack);
    }

    private void callBankAndBranchApi(JSONObject jsonObject) {
        customProgressDialog.showProgress("loading", false);
        AppUtils.getInstance().showLog("BankAndBranchRequestApi" + AppConstant.BANK_BRANCH_DATA, ShgLoginActivity.class);

        JsonObjectRequest bankAndBranchObjectReq = new JsonObjectRequest(Request.Method.POST, AppConstant.BANK_BRANCH_DATA
                , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response != null) {
                        /*{"message":"failure","status":0}*/

                        AppUtils.getInstance().showLog("BankAndBranchApiResponse" + response.toString(), ShgLoginActivity.class);

                        if (response.getString("message").equalsIgnoreCase("success")){
                            // insertBnakAndBranchResInDB(response.toString());
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customProgressDialog.hideProgress();
                AppUtils.getInstance().showLog("BankAndBranchApiError" + error.toString(), ShgLoginActivity.class);
            }
        });

        bankAndBranchObjectReq.setRetryPolicy(new DefaultRetryPolicy(30000, 0, 0));

        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(bankAndBranchObjectReq);
    }

    private void insertBnakAndBranchResInDB(BankAndBranchResponse bankAndBranchResponse) {
        BankTypeModel bankTypeModel = new BankTypeModel(getApplication());
        BankModel bankModel = new BankModel(getApplication());
        BankBranchModel bankBranchModel = new BankBranchModel(getApplication());

        List<BankAndBranchResponse.BankData> bankDataList = bankAndBranchResponse.getData();
        appUtils.showLog("size of list:- " + bankDataList.size(), ShgLoginActivity.class);
        try {
            for (BankAndBranchResponse.BankData bankData : bankDataList) {
                BankTypeEntity bankTypeEntity = new BankTypeEntity();
                bankTypeEntity.banktype_code = bankData.getBanktype_code();
                bankTypeEntity.banktype_detail = bankData.getBanktype_detail();
                bankTypeModel.insertAll(bankTypeEntity);

                List<BankAndBranchResponse.BankData.BankDetails> bankDetailsList = bankData.getBank_detail();
                for (BankAndBranchResponse.BankData.BankDetails bankDetails : bankDetailsList) {

                    BankEntity bankEntity = new BankEntity();
                    bankEntity.acc_length = bankDetails.getAcc_length();
                    bankEntity.bank_code = bankDetails.getBank_code();
                    bankEntity.bank_name = bankDetails.getBank_name();
                    bankEntity.bank_level_code = bankDetails.getBanklevel_code();
                    bankEntity.entity_code = bankDetails.getEntity_code();

                    bankModel.insertAll(bankEntity);

                    List<BankAndBranchResponse.BankData.BankDetails.BranchData> branchDataList = bankDetails.getBank_branch_detail();
                    for (BankAndBranchResponse.BankData.BankDetails.BranchData branchData : branchDataList) {

                        BankBranchEntity bankBranchEntity = new BankBranchEntity();
                        bankBranchEntity.branch_code = branchData.getBranch_code();
                        bankBranchEntity.branch_name = branchData.getBranch_name();
                        bankBranchEntity.ifsc_code = branchData.getIfsc_code();
                        bankBranchEntity.bank_code = bankDetails.getBank_code();

                        bankBranchModel.insertAll(bankBranchEntity);
                    }
                }

            }
            // customProgressDialog =null;
            customProgressDialog.hideProgress();
            AppUtils.getInstance().makeIntent(ShgLoginActivity.this, ModuleSelectionActivity.class, true);
        } catch (Exception e) {
            customProgressDialog.hideProgress();
            AppUtils.getInstance().makeIntent(ShgLoginActivity.this, ShgLoginActivity.class, true);
            appUtils.showLog("Exception:- " + e, ShgLoginActivity.class);
        }

    }


    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @NonNull
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {

                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
            AppUtils.getInstance().showLog("SSLConnectionExp" + ignored.toString(), ShgLoginActivity.class);
        }
    }

    public void showServerErrorDialog(String errorCode) {
        for (ServiceMessage serviceMessage : ServiceMessage.values()) {
            if (serviceMessage.getErrorCode().equalsIgnoreCase(errorCode)) {
                DialogFactory.getInstance().showAlertDialog(getApplicationContext(), 0, getString(R.string.info)
                        , serviceMessage.getErrorMessage(), getString(R.string.ok)
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }, null, null, true);

            }
        }
    }

}