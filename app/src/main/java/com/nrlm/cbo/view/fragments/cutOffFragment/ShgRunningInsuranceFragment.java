package com.nrlm.cbo.view.fragments.cutOffFragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.Utils.DateFactory;
import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.repositories.ShgCutOffRepo;
import com.nrlm.cbo.view.Activities.ShgCutOffActivity;
import com.nrlm.cbo.view.adapters.settingAdapter.ShgNominationAdapter;
import com.nrlm.cbo.view.adapters.shgCutoffAdapters.ShgCutoffRunningInsuranceAdapter;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.nrlm.cbo.view.interfaces.OnClickListnerForRunningInsurance;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShgRunningInsuranceFragment extends BaseFragment {


    /***********all edit text*************/
    @BindView(R.id.policyNumberEt)
    TextInputEditText policyNumberEt;

    @BindView(R.id.policyNameEt)
    TextInputEditText policyNameEt;

    @BindView(R.id.premiumAmountET)
    TextInputEditText premiumAmountET;

    @BindView(R.id.policyRiskET)
    TextInputEditText policyRiskET;

    @BindView(R.id.policyStartDateET)
    TextInputEditText policyStartDateET;


    @BindView(R.id.policyEndET)
    TextInputEditText policyEndET;


    /***********all textIL*************/
    @BindView(R.id.policyNumberTIL)
    TextInputLayout policyNumberTIL;

    @BindView(R.id.policyNameTIL)
    TextInputLayout policyNameTIL;

    @BindView(R.id.premiumAmountTTL)
    TextInputLayout premiumAmountTTL;

    @BindView(R.id.policyRiskTTL)
    TextInputLayout policyRiskTTL;

    @BindView(R.id.policyStartDateTTL)
    TextInputLayout policyStartDateTTL;

    @BindView(R.id.policyEndTTL)
    TextInputLayout policyEndTTL;


    /*********all btn************/
    @BindView(R.id.addInsuranceBtn)
    MaterialButton addInsuranceBtn;

    @BindView(R.id.closeInsuranceBtn)
    MaterialButton closeInsuranceBtn;

    @BindView(R.id.closeRvBtn)
    MaterialButton closeRvBtn;

    @BindView(R.id.addMoreInsuranceBtn)
    MaterialButton addMoreInsuranceBtn;

    @BindView(R.id.updateBtn)
    MaterialButton updateBtn;






    /**********all layouts**************/
    @BindView(R.id.insuranceFormLL)
    LinearLayout insuranceFormLL;

    @BindView(R.id.recyclerRL)
    RelativeLayout recyclerRL;

    @BindView(R.id.runningInsuranceRV)
    RecyclerView runningInsuranceRV;





    /****all utils classes and repository*****/
    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    CustomProgressDialog customProgressDialog;
    AppUtils appUtils;
    DateFactory dateFactory;

    ShgCutOffRepo shgCutOffRepo;


    /**********all global variables************/
    String shgCode = "";
    String shgName = "";
    String bnkCmpnyId = "";
    String fdClickStatus = "";
    String bankCode = "";
    String branchCode = "";
    String companyId = "";
    String companyBranchId = "";

    String policyNumber="";
    String policyName="";
    String policyPremiumAmount="";
    String policyRiskCover="";
    String policyStartDate="";
    String policyEndDate="";
    String syncStatus="0";
    String insuranceId="";



    /***************all lists***********************/
    List<ShgCutOffRunningInsuranceEntity> runningInsuranceDataItem;


    ShgCutoffRunningInsuranceAdapter shgCutoffRunningInsuranceAdapter;

    OnClickListnerForRunningInsurance onClickListnerForRunningInsurance;
    ShgCutOffRunningInsuranceEntity insuranceGlobalObject;



    public static ShgRunningInsuranceFragment newInstance() {
        ShgRunningInsuranceFragment shgFragment = new ShgRunningInsuranceFragment();
        return shgFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_running_insurance_frag_layout;
    }

    @Override
    public void onFragmentReady() {
        getAllInstance();
        shgCode =appSharedPreferences.getShgCodeForVerification();

        runningInsuranceDataItem = shgCutOffRepo.getAllInsuranceData(shgCode);

        onClickListnerForRunningInsurance =new OnClickListnerForRunningInsurance() {
            @Override
            public void notifyDate(String Type, ShgCutOffRunningInsuranceEntity insuranceObject) {
                switch (Type){
                    case "edit":
                        insuranceGlobalObject =insuranceObject;
                        claerFocus(2);

                        break;
                    case "delete":
                        break;
                }

            }
        };


        if(runningInsuranceDataItem.isEmpty()){
            insuranceFormLL.setVisibility(View.VISIBLE);
            recyclerRL.setVisibility(View.GONE);
        }else {
            insuranceFormLL.setVisibility(View.GONE);
            recyclerRL.setVisibility(View.VISIBLE);
            showData();
        }

    }

    private void showData() {

      /*  View view = getLayoutInflater().inflate(R.layout.running_loan_recyclerview_layout, null);
        ButterKnife.bind(view);
        recyclerRLllll.addView(view);*/

        shgCutoffRunningInsuranceAdapter = new ShgCutoffRunningInsuranceAdapter(runningInsuranceDataItem, getContext(),getActivity().getApplication(),onClickListnerForRunningInsurance);
        runningInsuranceRV.setLayoutManager(new LinearLayoutManager(getContext()));
        runningInsuranceRV.setAdapter(shgCutoffRunningInsuranceAdapter);
        shgCutoffRunningInsuranceAdapter.notifyDataSetChanged();

    }

    private void getAllInstance() {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(getActivity());
        allDataList = AllDataList.getInstance(getActivity());
        customProgressDialog = CustomProgressDialog.newInstance(getActivity());
        appUtils = AppUtils.getInstance();
        dateFactory = DateFactory.getInstance(getActivity());

        shgCutOffRepo =new ShgCutOffRepo(getActivity().getApplication());

    }


    @OnClick(R.id.addInsuranceBtn)
    public void addInsurance(){
        policyNumber =policyNumberEt.getText().toString().trim();
        policyName =policyNameEt.getText().toString().trim();
        policyPremiumAmount =premiumAmountET.getText().toString().trim();
        policyRiskCover =policyRiskET.getText().toString().trim();
        policyStartDate =policyStartDateET.getText().toString().trim();
        policyEndDate =policyEndET.getText().toString().trim();




        if(policyNumber.isEmpty()){
            Toast.makeText(getContext(),"Enter valid Policy number..",Toast.LENGTH_SHORT).show();
        }else if(policyName.isEmpty()){
            Toast.makeText(getContext(),"Enter Policy Name..",Toast.LENGTH_SHORT).show();
        }else if(policyPremiumAmount.isEmpty()){
            Toast.makeText(getContext(),"Enter policy Premium Amount..",Toast.LENGTH_SHORT).show();
        }else if(policyRiskCover.isEmpty()){
            Toast.makeText(getContext(),"Enter policy Risk Cover Amount..",Toast.LENGTH_SHORT).show();
        }else if(policyStartDate.isEmpty()){
            Toast.makeText(getContext(),"Select Policy Start Date..",Toast.LENGTH_SHORT).show();
        }else if(policyEndDate.isEmpty()){
            Toast.makeText(getContext(),"Select Policy End Date..",Toast.LENGTH_SHORT).show();
        }else {
            insuranceId = "CRI"+appSharedPreferences.getRunningInsuranceId();
            int getId = Integer.parseInt(appSharedPreferences.getRunningInsuranceId());
            appSharedPreferences.setRunningInsuranceId(String.valueOf(getId+1));
            insertInsuranceDataInDB();
        }

    }

    private void insertInsuranceDataInDB() {
        ShgCutOffRunningInsuranceEntity shgCutOffRunningInsuranceEntity =new ShgCutOffRunningInsuranceEntity();
        shgCutOffRunningInsuranceEntity.insuranceId=insuranceId;
        shgCutOffRunningInsuranceEntity.shgCode =shgCode;
        shgCutOffRunningInsuranceEntity.policyNumber=policyNumber;
        shgCutOffRunningInsuranceEntity.policyName=policyName;
        shgCutOffRunningInsuranceEntity.policyPremiumAmount=policyPremiumAmount;
        shgCutOffRunningInsuranceEntity.policyRiskCover=policyRiskCover;
        shgCutOffRunningInsuranceEntity.policyStartDate=policyStartDate;
        shgCutOffRunningInsuranceEntity.policyEndDate=policyEndDate;
        shgCutOffRunningInsuranceEntity.insuranceSyncStatus=syncStatus;

        shgCutOffRepo.insertAll(shgCutOffRunningInsuranceEntity);


        runningInsuranceDataItem.clear();
        //runningInsuranceRV.setLayoutManager(null);


        runningInsuranceDataItem = shgCutOffRepo.getAllInsuranceData(shgCode);
       // shgCutoffRunningInsuranceAdapter.notifyDataSetChanged();

        insuranceFormLL.setVisibility(View.GONE);
        recyclerRL.setVisibility(View.VISIBLE);

        showData();

    }

    @OnClick(R.id.closeInsuranceBtn)
    public void closeInsuranceForm(){
        //go to cutoff screen
        shgCutOffRepo.delete();
    }

    @OnClick(R.id.addMoreInsuranceBtn)
    public void addMoreInsurance(){
        claerFocus(1);
        insuranceFormLL.setVisibility(View.VISIBLE);
        recyclerRL.setVisibility(View.GONE);

        updateBtn.setVisibility(View.GONE);
        addInsuranceBtn.setVisibility(View.VISIBLE);



    }

    private void claerFocus(int i) {
        switch (i){
            case 1:


                policyNumberEt.setText(null);
                policyNameEt.setText(null);
                premiumAmountET.setText(null);
                policyRiskET.setText(null);
                policyStartDateET.setText(null);
                policyEndET.setText(null);
                break;
            case 2:
                insuranceFormLL.setVisibility(View.VISIBLE);
                recyclerRL.setVisibility(View.GONE);

                policyNumberEt.setText(insuranceGlobalObject.policyNumber);
                policyNameEt.setText(insuranceGlobalObject.policyName);
                premiumAmountET.setText(insuranceGlobalObject.policyPremiumAmount);
                policyRiskET.setText(insuranceGlobalObject.policyRiskCover);
                policyStartDateET.setText(insuranceGlobalObject.policyStartDate);
                policyEndET.setText(insuranceGlobalObject.policyEndDate);

                updateBtn.setVisibility(View.VISIBLE);
                addInsuranceBtn.setVisibility(View.GONE);


                break;
        }
    }

    @OnClick(R.id.updateBtn)
    public void updateAllData(){
        policyNumber =policyNumberEt.getText().toString().trim();
        policyName =policyNameEt.getText().toString().trim();
        policyPremiumAmount =premiumAmountET.getText().toString().trim();
        policyRiskCover =policyRiskET.getText().toString().trim();
        policyStartDate =policyStartDateET.getText().toString().trim();
        policyEndDate =policyEndET.getText().toString().trim();

        if(policyNumber.isEmpty()){
            Toast.makeText(getContext(),"Enter valid Policy number..",Toast.LENGTH_SHORT).show();
        }else if(policyName.isEmpty()){
            Toast.makeText(getContext(),"Enter Policy Name..",Toast.LENGTH_SHORT).show();
        }else if(policyPremiumAmount.isEmpty()){
            Toast.makeText(getContext(),"Enter policy Premium Amount..",Toast.LENGTH_SHORT).show();
        }else if(policyRiskCover.isEmpty()){
            Toast.makeText(getContext(),"Enter policy Risk Cover Amount..",Toast.LENGTH_SHORT).show();
        }else if(policyStartDate.isEmpty()){
            Toast.makeText(getContext(),"Select Policy Start Date..",Toast.LENGTH_SHORT).show();
        }else if(policyEndDate.isEmpty()){
            Toast.makeText(getContext(),"Select Policy End Date..",Toast.LENGTH_SHORT).show();
        }else {
            updateDataInDB();
        }

    }

    private void updateDataInDB() {
        ShgCutOffRunningInsuranceEntity shgCutOffRunningInsuranceEntity =new ShgCutOffRunningInsuranceEntity();
        shgCutOffRunningInsuranceEntity.insuranceId=insuranceGlobalObject.insuranceId;
        shgCutOffRunningInsuranceEntity.shgCode =insuranceGlobalObject.shgCode;
        shgCutOffRunningInsuranceEntity.policyNumber=policyNumber;
        shgCutOffRunningInsuranceEntity.policyName=policyName;
        shgCutOffRunningInsuranceEntity.policyPremiumAmount=policyPremiumAmount;
        shgCutOffRunningInsuranceEntity.policyRiskCover=policyRiskCover;
        shgCutOffRunningInsuranceEntity.policyStartDate=policyStartDate;
        shgCutOffRunningInsuranceEntity.policyEndDate=policyEndDate;
        shgCutOffRunningInsuranceEntity.insuranceSyncStatus=insuranceGlobalObject.insuranceSyncStatus;

        shgCutOffRepo.updateInsuranceData(shgCutOffRunningInsuranceEntity);

        runningInsuranceDataItem.clear();
        runningInsuranceDataItem = shgCutOffRepo.getAllInsuranceData(shgCode);
        insuranceFormLL.setVisibility(View.GONE);
        recyclerRL.setVisibility(View.VISIBLE);
        showData();
    }

   /* public class InsuranceForm{

        public InsuranceForm(LinearLayout linearLayout) {
            View view = getLayoutInflater().inflate(R.layout.running_insurance_form, null);
            ButterKnife.bind(view);
            linearLayout.addView(view);
        }
    }*/



}
