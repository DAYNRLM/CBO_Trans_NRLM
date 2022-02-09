package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListAdapter;

import com.android.volley.VolleyError;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.Utils.DateFactory;
import com.nrlm.cbo.Utils.DeviceInfoUtils;
import com.nrlm.cbo.Utils.LocationMasterUtils;
import com.nrlm.cbo.Utils.MaterialDialogFactory;
import com.nrlm.cbo.database.room.datamodels.BlockModel;
import com.nrlm.cbo.database.room.datamodels.GpsModel;
import com.nrlm.cbo.database.room.datamodels.VillageModel;
import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyBranchEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyNameEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffFixedDepositEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffLoanTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffLoanSourceEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffMemberPurposeEntity;
import com.nrlm.cbo.database.room.entities.MasterIsRfReturendEntity;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.MasterNomineeRelationEntity;
import com.nrlm.cbo.database.room.entities.MasterPaymentAgencyTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgSubActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptLoanEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptSubTypeLoanSourceEntity;
import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentEntity;
import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentSubTypeEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.entity.MasterSavingEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.model.request.LittleMasterDataRequest;
import com.nrlm.cbo.model.request.ShgDataRequest;
import com.nrlm.cbo.model.response.LittleMasterData;
import com.nrlm.cbo.model.response.MicroServiceMasterREsponse;
import com.nrlm.cbo.model.response.ShgDataResponse;
import com.nrlm.cbo.model.response.ShgMemberDataResponse;
import com.nrlm.cbo.webService.volley.VolleyResult;
import com.nrlm.cbo.webService.volley.VolleyService;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModuleSelectionActivity extends AppCompatActivity {

    @BindView(R.id.userTypeSpinner)
    AutoCompleteTextView userTypeSpinner;

    @BindView(R.id.spinnerTextLayout)
    TextInputLayout spinnerTextLayout;

    @BindView(R.id.gotoHomeBtn)
    MaterialButton gotoHomeBtn;

    private ArrayAdapter<String> userTypeArray;
    String userType = "";

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

    ShgDataRepo shgDataRepo;
    ShgmemberRepo shgmemberRepo;
    MasterDataRepo masterDataRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_selection);
        ButterKnife.bind(this);
        getAllInstance();


        userTypeArray = new ArrayAdapter<String>(ModuleSelectionActivity.this, R.layout.spinner_textview
                , getResources().getStringArray(R.array.moduleArray));
        userTypeSpinner.setAdapter(userTypeArray);


        userTypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerTextLayout.setError(null);
                userType = "SHG";
            }
        });
    }

    /*****get all instance ********/
    private void getAllInstance() {
        shgDataRepo = new ShgDataRepo(getApplication());
        shgmemberRepo = new ShgmemberRepo(getApplication());
        masterDataRepo = new MasterDataRepo(getApplication());
        allDataList = AllDataList.getInstance(ModuleSelectionActivity.this);
        appUtils = AppUtils.getInstance();
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ModuleSelectionActivity.this);
        volleyService = VolleyService.getInstance(ModuleSelectionActivity.this);
        deviceInfoUtils = DeviceInfoUtils.getInstance(ModuleSelectionActivity.this);
        //customProgressDialog =CustomProgressDialog.getInstance(ModuleSelectionActivity.this);
        customProgressDialog = CustomProgressDialog.newInstance(ModuleSelectionActivity.this);
        // materialDialogFactory = MaterialDialogFactory.getInstance(ModuleSelectionActivity.this);
        materialDialogFactory = MaterialDialogFactory.newInstance(ModuleSelectionActivity.this);
        dateFactory = DateFactory.getInstance(ModuleSelectionActivity.this);
        locationMasterUtils = LocationMasterUtils.getInstance(ModuleSelectionActivity.this);
    }
    @OnClick(R.id.gotoHomeBtn)
    public void goToHome() {
        if (userType.trim().equalsIgnoreCase("")) {
            spinnerTextLayout.setError("Select User type first.");
        } else {
            callShgDataApi();
        }
    }
    private void callShgDataApi() {
        customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
        JSONObject shgDataObject = null;
        try {
            ShgDataRequest shgDataRequest = new ShgDataRequest();
            shgDataRequest.setState_short_name("mp");
            shgDataRequest.setBlock_code("1749002");
            shgDataRequest.setUser_id("MCBOMPAPANDROID");
            shgDataRequest.setImei_no("111");
            shgDataRequest.setDevice_name("111");
            shgDataRequest.setLocation_coordinate("111");
            shgDataObject = new JSONObject(shgDataRequest.javaToJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mResultCallBack = new VolleyResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                customProgressDialog.hideProgress();
                try {
                    ShgDataResponse shgDataResponse = ShgDataResponse.jsonToJava(response.toString());
                    appUtils.showLog("shg data response:" + shgDataResponse.toString(), ModuleSelectionActivity.class);
                    inseartShgDataInDB(shgDataResponse);

                } catch (Exception e) {
                    appUtils.showLog("Exception in shg data:" + e.toString(), ModuleSelectionActivity.class);
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
        volleyService.postDataVolley("shg_data_request", AppConstant.MICRO_SHG_DATA_URL, shgDataObject, mResultCallBack);
    }
    private void inseartShgDataInDB(ShgDataResponse shgDataResponse) {
        List<ShgDataResponse.ShgData> shgDetails = shgDataResponse.getData();
        appUtils.showLog("size of list:- " + shgDetails.size(), ModuleSelectionActivity.class);
        for (ShgDataResponse.ShgData shgData : shgDetails) {
            ShgDataEntity shgDataEntity = new ShgDataEntity();
            shgDataEntity.promotedBy = shgData.getPromotedBy();
            shgDataEntity.shgCode = shgData.getShgCode();
            shgDataEntity.bankCode = (String) shgData.getBankCode();
            shgDataEntity.lastMeetingNo = shgData.getLastMeetingNo();
            shgDataEntity.shgType = shgData.getShgType();
            shgDataEntity.cuttOffStatus = shgData.getCuttOffStatus();
            shgDataEntity.verificationStatus = shgData.getVerificationStatus();
            shgDataEntity.settingStatus = shgData.getSettingStatus();
            shgDataEntity.lastMeetingDate = shgData.getLastMeetingDate();
            shgDataEntity.groupFormationDate = shgData.getGroupFormationDate();
            shgDataEntity.shgName = shgData.getShgName();
            shgDataEntity.accOpeningDate = (String) shgData.getOpDateOfAccount();
            shgDataEntity.bankAccNo = (String) shgData.getBankAccountNo();
            shgDataEntity.branchCode = (String) shgData.getBankBranchCode();
            shgDataEntity.entityCode = shgData.getEntityCode();
            shgDataEntity.promoter_name = shgData.getEntityCode();
            shgDataEntity.loan_running = shgData.getEntityCode();
            shgDataEntity.shg_category = shgData.getEntityCode();
            shgDataEntity.cashbook_no = shgData.getEntityCode();
            shgDataEntity.cashbook_page = shgData.getEntityCode();
            shgDataEntity.meeting_frequency = shgData.getEntityCode();
            shgDataRepo.insertAll(shgDataEntity);
            /********old parsing************************/
           /* shgDataEntity.promotedBy = shgData.getPromoted_by();
            shgDataEntity.shgCode = shgData.getShg_code();
            shgDataEntity.bankCode = shgData.getBank_code();
            shgDataEntity.lastMeetingNo = shgData.getLast_meeting_no();
            shgDataEntity.shgType = shgData.getShg_type();
            shgDataEntity.cuttOffStatus = shgData.getCutt_off_status();
            shgDataEntity.verificationStatus = shgData.getVerification_status();
            shgDataEntity.settingStatus = shgData.getSetting_status();
            shgDataEntity.lastMeetingDate = shgData.getLast_meeting_date();
            shgDataEntity.groupFormationDate = shgData.getGroup_formation_date();
            shgDataEntity.shgName = shgData.getShg_name();
            shgDataEntity.accOpeningDate = shgData.getAcc_opening_date();
            shgDataEntity.bankAccNo = shgData.getBank_acc_no();
            shgDataEntity.branchCode = shgData.getBranch_code();
            shgDataEntity.entityCode = shgData.getEntity_code();
            shgDataRepo.insertAll(shgDataEntity);*/
            ShgSettingSavingFromMemberEntity shgSettingSavingFromMemberEntity=new ShgSettingSavingFromMemberEntity();
            shgSettingSavingFromMemberEntity.shg_code=shgData.getShgCode();
            shgSettingSavingFromMemberEntity.saving_type="1";
            shgSettingSavingFromMemberEntity.amount="";
            shgSettingSavingFromMemberEntity.roi="";
            new ShgSettingSavingFromMemberRepo(getApplication()).insert(shgSettingSavingFromMemberEntity);
        }
        customProgressDialog.hideProgress();
        callShgMemberDataApi();
    }
        private void callShgMemberDataApi() {
        customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
        JSONObject shgMemberDataObject = null;
        try {
            ShgDataRequest shgDataRequest = new ShgDataRequest();
            shgDataRequest.setState_short_name("mp");
            shgDataRequest.setBlock_code("1749002");
            shgDataRequest.setUser_id("MCBOMPAPANDROID");
            shgDataRequest.setImei_no("111");
            shgDataRequest.setDevice_name("111");
            shgDataRequest.setLocation_coordinate("111");
            shgMemberDataObject = new JSONObject(shgDataRequest.javaToJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mResultCallBack = new VolleyResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                customProgressDialog.hideProgress();
                try {
                    ShgMemberDataResponse shgMemberDataResponse = ShgMemberDataResponse.jsonToJava(response.toString());
                    appUtils.showLog("shg data response:" + shgMemberDataResponse.toString(), ModuleSelectionActivity.class);
                    inseartShgMemberDataInDB(shgMemberDataResponse);

                } catch (Exception e) {
                    appUtils.showLog("Exception:" + e.toString(), ModuleSelectionActivity.class);
                }
            }
            @Override
            public void notifyError(String requestType, VolleyError error) {
                // customProgressDialog.hideProgress();
                materialDialogFactory.showExceptionDialog(error.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        };
        volleyService.postDataVolley("shg_member_data_request", AppConstant.MICRO_MEMBER_DATA_URL, shgMemberDataObject, mResultCallBack);
    }

    private void inseartShgMemberDataInDB(ShgMemberDataResponse shgMemberDataResponse) {
        List<ShgMemberDataResponse.MemberData> shgMemberList = shgMemberDataResponse.getData();
        appUtils.showLog("size of list:- " + shgMemberList.size(), ModuleSelectionActivity.class);

        try {
            for (ShgMemberDataResponse.MemberData shgmember : shgMemberList) {
                ShgMemberDataEntity shgMemberDataEntity = new ShgMemberDataEntity();
                shgMemberDataEntity.shgCode = shgmember.getShgCode();
                shgMemberDataEntity.leader = shgmember.getLeader();
                shgMemberDataEntity.belongingName = shgmember.getBelongingName();
                shgMemberDataEntity.aplbpl = shgmember.getAplbpl();
                shgMemberDataEntity.gender = shgmember.getGender();
                shgMemberDataEntity.disability = shgmember.getDisability();
                shgMemberDataEntity.birthDate = shgmember.getDob();
                shgMemberDataEntity.pipCategory = shgmember.getPipCategory();
                shgMemberDataEntity.memberName = shgmember.getMemberName();
                shgMemberDataEntity.entityCode = shgmember.getEntityCode();
                shgMemberDataEntity.religon = shgmember.getReligion();
                shgMemberDataEntity.socialCategory = shgmember.getSocialCategory();
                shgMemberDataEntity.shgMemberCode = shgmember.getShgMemberCode();
                shgMemberDataEntity.aadhaar = (String) shgmember.getAadhaar();
                shgMemberDataEntity.mobile_number = shgmember.getMobileNumber();
                shgMemberDataEntity.active_details = (String) shgmember.getActiveDetails();
                shgMemberDataEntity.mem_bank_code = (String) shgmember.getMemBankCode();
                shgMemberDataEntity.mem_branch_code = (String) shgmember.getMemBranchCode();
                shgMemberDataEntity.ac_no = (String) shgmember.getAcNo();
                shgMemberDataEntity.aadhar_seeded_sb_ac = (String) shgmember.getAadharSeededSbAc();
                shgMemberDataEntity.adhar_valid = (String) shgmember.getAdharValid();
                shgMemberDataEntity.book_keeper_member = (String) shgmember.getBookKeeperMember();
                shgMemberDataEntity.education = shgmember.getEducation();
                shgMemberDataEntity.other_education = shgmember.getOtherEducation();
                shgMemberDataEntity.enroll_in_pmjy = shgmember.getEnrollInPmjy();
                shgMemberDataEntity.enroll_in_pmsby = shgmember.getEnrollInPmsby();
                shgMemberDataEntity.enroll_in_lic = shgmember.getEnrollInLic();
                shgMemberDataEntity.enroll_in_hic = shgmember.getEnrollInHic();
                shgmemberRepo.insertAll(shgMemberDataEntity);
            }
            customProgressDialog.hideProgress();
        }catch (Exception e){
            appUtils.showLog("Exception SHG MEMBER PARSINg:" + e.toString(), ModuleSelectionActivity.class);
        }

        callMasterDataApi();
    }

    private void callMasterDataApi() {
        customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
        JSONObject masterDataObject = null;
        try {
            LittleMasterDataRequest littleMasterDataRequest = new LittleMasterDataRequest();
            littleMasterDataRequest.setDevice_name("MCBOMPAPANDROID");
            littleMasterDataRequest.setUser_id("111");
            littleMasterDataRequest.setImei_no("111");
            littleMasterDataRequest.setLocation_coordinate("111");
            masterDataObject = new JSONObject(littleMasterDataRequest.javaToJson());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mResultCallBack = new VolleyResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                try {
                    appUtils.showLog("RESPONSE:" + response, ModuleSelectionActivity.class);
                   /* LittleMasterData littleMasterData = LittleMasterData.jsonToJava(response.toString());
                    insertMasterDataInDb(littleMasterData);*/

                    MicroServiceMasterREsponse microServiceMasterREsponse =MicroServiceMasterREsponse.jsonToJava(response.toString());
                    insertMicroMasterDataInDB(microServiceMasterREsponse);


                } catch (Exception e) {
                    appUtils.showLog("Exception: in Master" + e.toString(), ModuleSelectionActivity.class);
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                // customProgressDialog.hideProgress();
                materialDialogFactory.showExceptionDialog(error.toString(), "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
            }
        };
        volleyService.postDataVolley("mster_data_request", AppConstant.MICRO_MASTER_DATA_URL, masterDataObject, mResultCallBack);
    }

    private void insertMicroMasterDataInDB(MicroServiceMasterREsponse microServiceMasterREsponse) {
        appUtils.showLog("size of master data" , ModuleSelectionActivity.class);
        try {

            /*****************shg seeting data********************/
            List<MicroServiceMasterREsponse.ShgSetting> shgSeetingDataList = microServiceMasterREsponse.getShgSetting();
            for(MicroServiceMasterREsponse.ShgSetting seetingData : shgSeetingDataList){

                List<MicroServiceMasterREsponse.Meetingfreq> meetingFreqDataList = seetingData.getMeetingfreq();
                for (MicroServiceMasterREsponse.Meetingfreq meetingData : meetingFreqDataList){
                    MeetingFrequencyEntity meetingFrequencyEntity = new MeetingFrequencyEntity();
                    meetingFrequencyEntity.meeting_frequency = meetingData.getMeetingfrequency();
                    meetingFrequencyEntity.meeting_frequency_name = meetingData.getMeetingfrequencyname();
                    masterDataRepo.insertAllFrequency(meetingFrequencyEntity);
                }

                List<MicroServiceMasterREsponse.Isrfreturnto> reReturnDataList = seetingData.getIsrfreturnto();
                for (MicroServiceMasterREsponse.Isrfreturnto rfData : reReturnDataList) {
                    MasterIsRfReturendEntity masterIsRfReturendEntity = new MasterIsRfReturendEntity();
                    masterIsRfReturendEntity.rf_to_be_return_name = rfData.getRftobereturnName();
                    masterIsRfReturendEntity.rf_to_be_return_value = rfData.getRftobereturnValue();
                    masterDataRepo.insertAllRfReturend(masterIsRfReturendEntity);
                }

                List<MicroServiceMasterREsponse.SavingFromMember> savingDataList = seetingData.getSavingFromMember();
                for (MicroServiceMasterREsponse.SavingFromMember savingData : savingDataList) {
                    MasterMemberSavingEntity masterSavingEntity = new MasterMemberSavingEntity();
                    masterSavingEntity.saving_type_name = savingData.getSavingTypeName();
                    masterSavingEntity.saving_type_code = savingData.getSavingTypeCode();
                    masterDataRepo.insertAllMemberSaving(masterSavingEntity);
                }

                List<MicroServiceMasterREsponse.MemberRelationNominee> nomineeDataList = seetingData.getMemberRelationNominee();
                for (MicroServiceMasterREsponse.MemberRelationNominee nomineeData : nomineeDataList) {
                    MasterNomineeRelationEntity masterNomineeRelationEntity = new MasterNomineeRelationEntity();
                    masterNomineeRelationEntity.relation_name = nomineeData.getRelationName();
                    masterNomineeRelationEntity.relation_code = nomineeData.getRelationCode();
                    masterDataRepo.insertAllNomineeRelation(masterNomineeRelationEntity);
                }

                List<MicroServiceMasterREsponse.ShgActivityCategory> shgCatDataList = seetingData.getShgActivityCategory();
                for (MicroServiceMasterREsponse.ShgActivityCategory catData : shgCatDataList) {
                    MasterSeetingShgActivityEntity masterSeetingShgActivityEntity = new MasterSeetingShgActivityEntity();
                    masterSeetingShgActivityEntity.category_name = catData.getCategoryName();
                    masterSeetingShgActivityEntity.category_value = catData.getCategoryValue();
                    masterDataRepo.insertAllShgActivity(masterSeetingShgActivityEntity);
                    List<MicroServiceMasterREsponse.ShgSubActivity> subCatList = catData.getShgSubActivityList();
                    for (MicroServiceMasterREsponse.ShgSubActivity subCatData : subCatList) {
                        MasterSeetingShgSubActivityEntity masterSeetingShgSubActivityEntity = new MasterSeetingShgSubActivityEntity();
                        masterSeetingShgSubActivityEntity.category_value = catData.getCategoryValue();
                        masterSeetingShgSubActivityEntity.sub_activity_id = subCatData.getSubActivityId();
                        masterSeetingShgSubActivityEntity.sub_activity_name = subCatData.getSubActivityName();
                        masterDataRepo.insertAllShgSubActivity(masterSeetingShgSubActivityEntity);
                    }
                }
            }

            /**************shg member cutoff data*******************/
            List<MicroServiceMasterREsponse.ShgMemberCutOff> shgMemberCutOffs = microServiceMasterREsponse.getShgMemberCutOff();
            for (MicroServiceMasterREsponse.ShgMemberCutOff shgMemberCutOff : shgMemberCutOffs) {

                List<MicroServiceMasterREsponse.LoanSource> loanSources = shgMemberCutOff.getLoanSource();
                for (MicroServiceMasterREsponse.LoanSource loanSource : loanSources) {
                    MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity = new MasterCuttOffLoanSourceEntity();
                    masterCuttOffLoanSourceEntity.loan_source_name = loanSource.getLoanSourceName();
                    masterCuttOffLoanSourceEntity.loan_source_value = loanSource.getLoanSourceValue();
                    masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity);
                }
                List<MicroServiceMasterREsponse.MemPurpose> memPurposes = shgMemberCutOff.getMemPurpose();
                    for (MicroServiceMasterREsponse.MemPurpose memPurpose : memPurposes) {
                        MasterCuttOffMemberPurposeEntity masterCuttOffMemberPurposeEntity = new MasterCuttOffMemberPurposeEntity();
                        masterCuttOffMemberPurposeEntity.discription = memPurpose.getDiscription();
                        masterCuttOffMemberPurposeEntity.purpose_id = memPurpose.getPurposeId();
                        masterDataRepo.insertAllMemberPurpose(masterCuttOffMemberPurposeEntity);
                    }
            }

            /**************shg cutoff data****************************/
            List<MicroServiceMasterREsponse.ShgCutOff> shgCutOffDataList = microServiceMasterREsponse.getShgCutOff();
            for (MicroServiceMasterREsponse.ShgCutOff shgCutOffdata : shgCutOffDataList) {

                List<MicroServiceMasterREsponse.BankCompany> bankCompanydataList = shgCutOffdata.getBankCompany();
                for (MicroServiceMasterREsponse.BankCompany bankData : bankCompanydataList) {
                    MasterCutoffBankCompnyEntity masterCutoffBankCompnyEntity = new MasterCutoffBankCompnyEntity();
                    masterCutoffBankCompnyEntity.bank_company_name = bankData.getBankCompanyName();
                    masterCutoffBankCompnyEntity.bank_company_value = bankData.getBankCompanyValue();
                    masterDataRepo.insertAllBankCompany(masterCutoffBankCompnyEntity);
                }
                List<MicroServiceMasterREsponse.FixedDepInv> fdDataList = shgCutOffdata.getFixedDepInv();
                for (MicroServiceMasterREsponse.FixedDepInv fdData : fdDataList) {
                    MasterCutoffFixedDepositEntity masterCutoffFixedDepositEntity = new MasterCutoffFixedDepositEntity();
                    masterCutoffFixedDepositEntity.fixed_deposit_inv_name = fdData.getFixedDepositInvName();
                    masterCutoffFixedDepositEntity.fixed_deposit_inv_value = fdData.getFixedDepositInvValue();
                    masterDataRepo.insertAllFD(masterCutoffFixedDepositEntity);
                }
                List<MicroServiceMasterREsponse.Company> companyList = shgCutOffdata.getCompany();
                for (MicroServiceMasterREsponse.Company companyData : companyList) {
                    MasterCutoffCompanyNameEntity masterCutoffCompanyNameEntity = new MasterCutoffCompanyNameEntity();
                    masterCutoffCompanyNameEntity.company_name_id =companyData.getCompanyCode();
                    masterCutoffCompanyNameEntity.company_name = companyData.getCompanyName();
                    masterCutoffCompanyNameEntity.syncStatus="1";
                    masterDataRepo.insertAllCompany(masterCutoffCompanyNameEntity);
                    List<MicroServiceMasterREsponse.CompanyBranch> companyBranchList = companyData.getCompanyBranch();
                    for(MicroServiceMasterREsponse.CompanyBranch companyBranchData : companyBranchList){
                        MasterCutoffCompanyBranchEntity masterCutoffCompanyBranchEntity =new MasterCutoffCompanyBranchEntity();
                        masterCutoffCompanyBranchEntity.company_name_id =companyData.getCompanyCode();
                        masterCutoffCompanyBranchEntity.company_branch_name =companyBranchData.getCompanyBranchName();
                        masterCutoffCompanyBranchEntity.company_branch_name_id =companyBranchData.getCompanyBranchCode();
                        masterCutoffCompanyBranchEntity.syncStatus="1";
                        masterDataRepo.insertAllCompanyBranch(masterCutoffCompanyBranchEntity);
                    }
                }
                List<MicroServiceMasterREsponse.LoanFrom> loanFromList = shgCutOffdata.getLoanFrom();
                for (MicroServiceMasterREsponse.LoanFrom loanFormData:loanFromList){
                    MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity1 = new MasterCuttOffLoanSourceEntity();
                    masterCuttOffLoanSourceEntity1.loan_source_name =loanFormData.getLoanFromName();
                    masterCuttOffLoanSourceEntity1.loan_source_value = loanFormData.getLoanFromId();
                    masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity1);
                }
                List<MicroServiceMasterREsponse.LoanType> loanTypeList = shgCutOffdata.getLoanType();
                for (MicroServiceMasterREsponse.LoanType loanTypeData:loanTypeList){
                    MasterCutoffLoanTypeEntity masterCutoffLoanTypeEntity =new MasterCutoffLoanTypeEntity();
                    masterCutoffLoanTypeEntity.loan_type_id=loanTypeData.getLoanTypeId();
                    masterCutoffLoanTypeEntity.loan_type=loanTypeData.getLoanTypeName();
                    masterDataRepo.insertAllLoanType(masterCutoffLoanTypeEntity);
                }
            }

            /************shg transcation******************************/
            List<MicroServiceMasterREsponse.TransShgPayment> shgTranPaymentDataList = microServiceMasterREsponse.getTransShgPayment();
            for (MicroServiceMasterREsponse.TransShgPayment paymentData : shgTranPaymentDataList) {
                MasterTransShgPaymentEntity masterTransShgPaymentEntity = new MasterTransShgPaymentEntity();
                masterTransShgPaymentEntity.payments_type_id = paymentData.getPaymentsTypeId();
                masterTransShgPaymentEntity.payment_discription = paymentData.getPaymentDiscription();
                masterDataRepo.insertAllPaymentType(masterTransShgPaymentEntity);

                List<MicroServiceMasterREsponse.ShgPaymentSubType> subTypePaymentDataList = paymentData.getShgPaymentSubType();
                for (MicroServiceMasterREsponse.ShgPaymentSubType subpaymentdata : subTypePaymentDataList) {
                    MasterTransShgPaymentSubTypeEntity masterTransShgPaymentSubTypeEntity = new MasterTransShgPaymentSubTypeEntity();
                    masterTransShgPaymentSubTypeEntity.payment_sub_discription = subpaymentdata.getPaymentDiscription();
                    masterTransShgPaymentSubTypeEntity.payments_Sub_type_id = subpaymentdata.getPaymentsTypeId();
                    masterTransShgPaymentSubTypeEntity.payments_type_id = paymentData.getPaymentsTypeId();
                    masterDataRepo.insertAllPaymentSubType(masterTransShgPaymentSubTypeEntity);
                }
            }

            /***********shg trans paymnt*****************************/
            List<MicroServiceMasterREsponse.TransShgReceipt> transShgReceipts = microServiceMasterREsponse.getTransShgReceipt();
            for (MicroServiceMasterREsponse.TransShgReceipt transShgReceipt : transShgReceipts) {
                MasterShgReceiptEntity masterShgReceiptEntity = new MasterShgReceiptEntity();
                masterShgReceiptEntity.receipt_description = transShgReceipt.getReceiptDescription();
                masterShgReceiptEntity.receipt_id = transShgReceipt.getReceiptId();
                masterDataRepo.insertShgReceiptPartialData(masterShgReceiptEntity);

                List<MicroServiceMasterREsponse.ShgReceiptSubTypeLoanSource> shgReceiptSubTypeLoanSources = transShgReceipt.getShgReceiptSubTypeLoanSource();
                if (shgReceiptSubTypeLoanSources != null) {
                    for (MicroServiceMasterREsponse.ShgReceiptSubTypeLoanSource shgReceiptSubTypeLoanSource : shgReceiptSubTypeLoanSources) {
                        MasterShgReceiptSubTypeLoanSourceEntity masterShgReceiptSubTypeLoanSourceEntity = new MasterShgReceiptSubTypeLoanSourceEntity();
                        masterShgReceiptSubTypeLoanSourceEntity.receipt_id = transShgReceipt.getReceiptId();
                        masterShgReceiptSubTypeLoanSourceEntity.sub_receipt_id = shgReceiptSubTypeLoanSource.getSubReceiptId();
                        masterShgReceiptSubTypeLoanSourceEntity.sub_receipt_description = shgReceiptSubTypeLoanSource.getSubReceiptDescription();
                        masterDataRepo.insertShgReceiptSubTypeLoanSource(masterShgReceiptSubTypeLoanSourceEntity);
                       /* List<MicroServiceMasterREsponse.ShgRecptLoan> shgRecptLoans = shgReceiptSubTypeLoanSource.getShgRecptLoan();
                        if (shgRecptLoans != null) {
                            for (LittleMasterData.ShgRecptLoan shgRecptLoan : shgRecptLoans) {
                                MasterShgReceiptLoanEntity masterShgReceiptLoanEntity = new MasterShgReceiptLoanEntity();
                                masterShgReceiptLoanEntity.sub_receipt_id = shgReceiptSubTypeLoanSource.getSubReceiptId();
                                masterShgReceiptLoanEntity.shg_recpt_loan_name = shgRecptLoan.getShgRecptLoanName();
                                masterShgReceiptLoanEntity.shg_recpt_loan_value = shgRecptLoan.getShgRecptLoanValue();
                                masterDataRepo.insertShgReceiptLoan(masterShgReceiptLoanEntity);


                            }
                        }*/
                    }
                }
            }

            customProgressDialog.hideProgress();
            appSharedPreferences.setLoginStatus("done");
            setAllLocalIds();
            customProgressDialog.showProgress(AppConstant.DIALOG_MSG,false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    customProgressDialog.hideProgress();
                    AppUtils.getInstance().makeIntent(ModuleSelectionActivity.this, HomeActivity.class, false);
                }
            },2000);


        }catch (Exception e){
            appUtils.showLog("EXPECTION iN MASTER DATA: "+e , ModuleSelectionActivity.class);
        }

    }

    private void insertMasterDataInDb(LittleMasterData littleMasterData) {
        appUtils.showLog("size of master data" , ModuleSelectionActivity.class);
        try{
            List<LittleMasterData.ShgSetting> shgSeetingDataList = littleMasterData.getShgSetting();
            for (LittleMasterData.ShgSetting seetingData : shgSeetingDataList) {

                List<LittleMasterData.Meetingfreq> meetingFreqDataList = seetingData.getMeetingfreq();
                for (LittleMasterData.Meetingfreq meetingData : meetingFreqDataList) {
                    MeetingFrequencyEntity meetingFrequencyEntity = new MeetingFrequencyEntity();

                    meetingFrequencyEntity.meeting_frequency = meetingData.getMeetingfrequency();
                    meetingFrequencyEntity.meeting_frequency_name = meetingData.getMeetingfrequencyname();


                    masterDataRepo.insertAllFrequency(meetingFrequencyEntity);

                }

                List<LittleMasterData.Isrfreturnto> reReturnDataList = seetingData.getIsrfreturnto();
                for (LittleMasterData.Isrfreturnto rfData : reReturnDataList) {
                    MasterIsRfReturendEntity masterIsRfReturendEntity = new MasterIsRfReturendEntity();

                    masterIsRfReturendEntity.rf_to_be_return_name = rfData.getRftobereturnName();
                    masterIsRfReturendEntity.rf_to_be_return_value = rfData.getRftobereturnValue();
                    masterDataRepo.insertAllRfReturend(masterIsRfReturendEntity);
                }

                List<LittleMasterData.SavingFromMember> savingDataList = seetingData.getSavingFromMember();
                for (LittleMasterData.SavingFromMember savingData : savingDataList) {

                    MasterMemberSavingEntity masterSavingEntity = new MasterMemberSavingEntity();
                    masterSavingEntity.saving_type_name = savingData.getSavingTypeName();
                    masterSavingEntity.saving_type_code = savingData.getSavingTypeCode();
                    masterDataRepo.insertAllMemberSaving(masterSavingEntity);


                }

                List<LittleMasterData.MemberRelationNominee> nomineeDataList = seetingData.getMemberRelationNominee();
                for (LittleMasterData.MemberRelationNominee nomineeData : nomineeDataList) {
                    MasterNomineeRelationEntity masterNomineeRelationEntity = new MasterNomineeRelationEntity();
                    masterNomineeRelationEntity.relation_name = nomineeData.getRelationName();
                    masterNomineeRelationEntity.relation_code = nomineeData.getRelationCode();
                    masterDataRepo.insertAllNomineeRelation(masterNomineeRelationEntity);

                }


                List<LittleMasterData.ShgActivityCategory> shgCatDataList = seetingData.getShgActivityCategory();
                for (LittleMasterData.ShgActivityCategory catData : shgCatDataList) {

                    MasterSeetingShgActivityEntity masterSeetingShgActivityEntity = new MasterSeetingShgActivityEntity();
                    masterSeetingShgActivityEntity.category_name = catData.getCategoryName();
                    masterSeetingShgActivityEntity.category_value = catData.getCategoryValue();

                    masterDataRepo.insertAllShgActivity(masterSeetingShgActivityEntity);


                    List<LittleMasterData.ShgSubActivity> subCatList = catData.getShgSubActivity();
                    for (LittleMasterData.ShgSubActivity subCatData : subCatList) {

                        MasterSeetingShgSubActivityEntity masterSeetingShgSubActivityEntity = new MasterSeetingShgSubActivityEntity();
                        masterSeetingShgSubActivityEntity.category_value = catData.getCategoryValue();
                        masterSeetingShgSubActivityEntity.sub_activity_id = subCatData.getSubActivityId();
                        masterSeetingShgSubActivityEntity.sub_activity_name = subCatData.getSubActivityName();

                        masterDataRepo.insertAllShgSubActivity(masterSeetingShgSubActivityEntity);
                    }
                }
            }

            List<LittleMasterData.ShgMemberCutOff> shgMemberCutOffs = littleMasterData.getShgMemberCutOff();
            for (LittleMasterData.ShgMemberCutOff shgMemberCutOff : shgMemberCutOffs) {
                List<LittleMasterData.LoanSource> loanSources = shgMemberCutOff.getLoanSource();

                for (LittleMasterData.LoanSource loanSource : loanSources) {
                    MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity = new MasterCuttOffLoanSourceEntity();
                    masterCuttOffLoanSourceEntity.loan_source_name = loanSource.getLoanSourceName();
                    masterCuttOffLoanSourceEntity.loan_source_value = loanSource.getLoanSourceValue();
                    masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity);
                }
            }

            List<LittleMasterData.ShgMemberCutOff> shgMemberCutOffs1 = littleMasterData.getShgMemberCutOff();
            for (LittleMasterData.ShgMemberCutOff shgMemberCutOff : shgMemberCutOffs1) {
                List<LittleMasterData.MemPurpose> memPurposes = shgMemberCutOff.getMemPurpose();
                for (LittleMasterData.MemPurpose memPurpose : memPurposes) {
                    MasterCuttOffMemberPurposeEntity masterCuttOffMemberPurposeEntity = new MasterCuttOffMemberPurposeEntity();
                    masterCuttOffMemberPurposeEntity.discription = memPurpose.getDiscription();
                    masterCuttOffMemberPurposeEntity.purpose_id = memPurpose.getPurposeId();
                    masterDataRepo.insertAllMemberPurpose(masterCuttOffMemberPurposeEntity);
                }
            }

            /**************by lincon****************/
            List<LittleMasterData.ShgCutOff> shgCutOffDataList = littleMasterData.getShgCutOff();
            for (LittleMasterData.ShgCutOff shgCutOffdata : shgCutOffDataList) {
                List<LittleMasterData.BankCompany> bankCompanydataList = shgCutOffdata.getBankCompany();
                for (LittleMasterData.BankCompany bankData : bankCompanydataList) {
                    MasterCutoffBankCompnyEntity masterCutoffBankCompnyEntity = new MasterCutoffBankCompnyEntity();
                    masterCutoffBankCompnyEntity.bank_company_name = bankData.getBankCompanyName();
                    masterCutoffBankCompnyEntity.bank_company_value = bankData.getBankCompanyValue();
                    masterDataRepo.insertAllBankCompany(masterCutoffBankCompnyEntity);

                }

                List<LittleMasterData.FixedDepInv> fdDataList = shgCutOffdata.getFixedDepInv();
                for (LittleMasterData.FixedDepInv fdData : fdDataList) {
                    MasterCutoffFixedDepositEntity masterCutoffFixedDepositEntity = new MasterCutoffFixedDepositEntity();
                    masterCutoffFixedDepositEntity.fixed_deposit_inv_name = fdData.getFixedDepositInvName();
                    masterCutoffFixedDepositEntity.fixed_deposit_inv_value = fdData.getFixedDepositInvValue();
                    masterDataRepo.insertAllFD(masterCutoffFixedDepositEntity);
                }

            }

            List<LittleMasterData.TransShgPayment> shgTranPaymentDataList = littleMasterData.getTransShgPayment();
            for (LittleMasterData.TransShgPayment paymentData : shgTranPaymentDataList) {
                MasterTransShgPaymentEntity masterTransShgPaymentEntity = new MasterTransShgPaymentEntity();
                masterTransShgPaymentEntity.payments_type_id = paymentData.getPaymentsTypeId();
                masterTransShgPaymentEntity.payment_discription = paymentData.getPaymentDiscription();
                masterDataRepo.insertAllPaymentType(masterTransShgPaymentEntity);

                List<LittleMasterData.ShgPaymentSubType> subTypePaymentDataList = paymentData.getShgPaymentSubType();
                for (LittleMasterData.ShgPaymentSubType subpaymentdata : subTypePaymentDataList) {
                    MasterTransShgPaymentSubTypeEntity masterTransShgPaymentSubTypeEntity = new MasterTransShgPaymentSubTypeEntity();
                    masterTransShgPaymentSubTypeEntity.payment_sub_discription = subpaymentdata.getPaymentDiscription();
                    masterTransShgPaymentSubTypeEntity.payments_Sub_type_id = subpaymentdata.getPaymentsTypeId();
                    masterTransShgPaymentSubTypeEntity.payments_type_id = paymentData.getPaymentsTypeId();
                    masterDataRepo.insertAllPaymentSubType(masterTransShgPaymentSubTypeEntity);
                }
            }
            /*************by manish*****************/

            List<LittleMasterData.TransShgReceipt> transShgReceipts = littleMasterData.getTransShgReceipt();
            for (LittleMasterData.TransShgReceipt transShgReceipt : transShgReceipts) {
                MasterShgReceiptEntity masterShgReceiptEntity = new MasterShgReceiptEntity();
                masterShgReceiptEntity.receipt_description = transShgReceipt.getReceiptDescription();
                masterShgReceiptEntity.receipt_id = transShgReceipt.getReceiptId();
                masterDataRepo.insertShgReceiptPartialData(masterShgReceiptEntity);
                List<LittleMasterData.ShgReceiptSubTypeLoanSource> shgReceiptSubTypeLoanSources = transShgReceipt.getShgReceiptSubTypeLoanSource();
                if (shgReceiptSubTypeLoanSources != null) {
                    for (LittleMasterData.ShgReceiptSubTypeLoanSource shgReceiptSubTypeLoanSource : shgReceiptSubTypeLoanSources) {
                        MasterShgReceiptSubTypeLoanSourceEntity masterShgReceiptSubTypeLoanSourceEntity = new MasterShgReceiptSubTypeLoanSourceEntity();
                        masterShgReceiptSubTypeLoanSourceEntity.receipt_id = transShgReceipt.getReceiptId();
                        masterShgReceiptSubTypeLoanSourceEntity.sub_receipt_id = shgReceiptSubTypeLoanSource.getSubReceiptId();
                        masterShgReceiptSubTypeLoanSourceEntity.sub_receipt_description = shgReceiptSubTypeLoanSource.getSubReceiptDescription();
                        masterDataRepo.insertShgReceiptSubTypeLoanSource(masterShgReceiptSubTypeLoanSourceEntity);
                        List<LittleMasterData.ShgRecptLoan> shgRecptLoans = shgReceiptSubTypeLoanSource.getShgRecptLoan();
                        if (shgRecptLoans != null) {
                            for (LittleMasterData.ShgRecptLoan shgRecptLoan : shgRecptLoans) {
                                MasterShgReceiptLoanEntity masterShgReceiptLoanEntity = new MasterShgReceiptLoanEntity();
                                masterShgReceiptLoanEntity.sub_receipt_id = shgReceiptSubTypeLoanSource.getSubReceiptId();
                                masterShgReceiptLoanEntity.shg_recpt_loan_name = shgRecptLoan.getShgRecptLoanName();
                                masterShgReceiptLoanEntity.shg_recpt_loan_value = shgRecptLoan.getShgRecptLoanValue();
                                masterDataRepo.insertShgReceiptLoan(masterShgReceiptLoanEntity);
                            }
                        }
                    }
                }
            }

/*
            *//**************insert loan type************//*
            MasterCutoffLoanTypeEntity masterCutoffLoanTypeEntity =new MasterCutoffLoanTypeEntity();
            masterCutoffLoanTypeEntity.loan_type_id="1";
            masterCutoffLoanTypeEntity.loan_type="Term Loan";
            masterDataRepo.insertAllLoanType(masterCutoffLoanTypeEntity);

            MasterCutoffLoanTypeEntity masterCutoffLoanTypeEntity2 =new MasterCutoffLoanTypeEntity();
            masterCutoffLoanTypeEntity2.loan_type_id="2";
            masterCutoffLoanTypeEntity2.loan_type="Cash Credit Loan";
            masterDataRepo.insertAllLoanType(masterCutoffLoanTypeEntity2);

            MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity1 = new MasterCuttOffLoanSourceEntity();
            masterCuttOffLoanSourceEntity1.loan_source_name = "VO";
            masterCuttOffLoanSourceEntity1.loan_source_value = "2";
            masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity1);

            MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity2 = new MasterCuttOffLoanSourceEntity();
            masterCuttOffLoanSourceEntity2.loan_source_name = "CLF";
            masterCuttOffLoanSourceEntity2.loan_source_value = "3";
            masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity2);

            MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity3 = new MasterCuttOffLoanSourceEntity();
            masterCuttOffLoanSourceEntity3.loan_source_name = "MFI";
            masterCuttOffLoanSourceEntity3.loan_source_value = "3";
            masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity3);

            MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity4 = new MasterCuttOffLoanSourceEntity();
            masterCuttOffLoanSourceEntity4.loan_source_name = "Other";
            masterCuttOffLoanSourceEntity4.loan_source_value = "4";
            masterDataRepo.insertAllLoanSource(masterCuttOffLoanSourceEntity4);
            *//*****************************************/


            customProgressDialog.hideProgress();
            appSharedPreferences.setLoginStatus("done");

            setAllLocalIds();
            customProgressDialog.showProgress(AppConstant.DIALOG_MSG,false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    customProgressDialog.hideProgress();
                    AppUtils.getInstance().makeIntent(ModuleSelectionActivity.this, HomeActivity.class, false);
                }
            },5000);


        }catch (Exception e){
            appUtils.showLog("Exception IN PARSINg:" + e.toString(), ModuleSelectionActivity.class);
        }
    }

    private void setAllLocalIds() {
        appSharedPreferences.setCompanyId("1");
        appSharedPreferences.setCompanyBranchId("1");
        appSharedPreferences.setRunningInsuranceId("1");
        appSharedPreferences.setRunningLoanId("1");
    }
}