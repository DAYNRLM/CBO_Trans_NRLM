package com.nrlm.cbo.view.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.DateFactory;
import com.nrlm.cbo.Utils.MaterialDialogFactory;
import com.nrlm.cbo.Utils.MyDatePicker;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.MemberCutOffEntity;
import com.nrlm.cbo.database.room.entities.MemberCutOffRunningLoanEntity;
import com.nrlm.cbo.database.room.entities.MemberCutOffSavingEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.adapters.memberCutOffAdapters.MemberCutOffSavingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MemberCutOffLayActivity extends AppCompatActivity {
    ArrayList<String> masterMemberSavingTypeNameData,loanSourceName;

    /*@BindView(R.id.addBt)
    MaterialButton addBt;
    @BindView(R.id.saving_for_memberRv)
    RecyclerView savingFormemberRv;*/
    LinearLayout layoutlist;
  //  @BindView(R.id.tbTitle)
   // TextView titleTv;
    @BindView(R.id.title)
    TextView title_activityTV;
    @BindView(R.id.addImg)
    ImageView addImg;
    @BindView(R.id.runningLoanCB)
    CheckBox runningLoanCB;
    @BindView(R.id.add_runing_loanBT)
    MaterialButton addRuningLoanBT;
    @BindView(R.id.shg_member_loanLL)
    LinearLayout shgMemberLoanLL;
    @BindView(R.id.shgName_Tv)
    TextView shgNameTv;
    int loanCount=0;
    MaterialButton deleteRuningLoanBT;
    ArrayList<String> cutoffMemberPurposeNameData;
    AppSharedPreferences appSharedPreferences;
    String selectedShgNameCutOff,selectedMemberNameMemCutSC;
    @BindView(R.id.member_nameTv)
    TextView memberNameTv;
    @BindView(R.id.member_codeTV)
    TextView memberCodeTv;
    @BindView(R.id.number_of_closed_loanEt)
    TextInputEditText numberOfCloseLoanEt;
    @BindView(R.id.amount_of_closedloanEt)
    TextInputEditText amountOfClosedLoanEt;
    @BindView(R.id.meeting_attendedET)
    TextInputEditText meetingsSinceBeigning;
    AppUtils appUtils;
    int count=0;
    MasterDataRepo masterDataRepo;
    int loanNumber=0;
    int acc=0; //to access one time the method
    List<MemberCutOffRunningLoanEntity> memberCutOffRunningLoanData;
    List<MemberCutOffSavingEntity> memberCutOffSavingData;
    DateFactory dateFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_cut_off_lay);
        ButterKnife.bind(this);
        try {
            layoutlist = findViewById(R.id.layout_list);
            deleteRuningLoanBT = findViewById(R.id.delete_runing_loanBT);
            // titleTv.setText("Member CutOff");
            initialization();
            dbValuesOverTheForm();
            shgNameTv.setText(selectedShgNameCutOff + "(" + appSharedPreferences.getSelectedShgCutOffSc() + ")");
            memberCodeTv.setText(appSharedPreferences.getMemberCutOffSelectedMemberCode());
            memberNameTv.setText(selectedMemberNameMemCutSC);
        }catch (Exception e)
        {
            appUtils.showLog("MemberCutOffLayActiviy inside the oncreate error"+e,MemberCutOffLayActivity.class);

        }
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    title_activityTV.setVisibility(View.GONE);
                } else if (isShow) {
                    isShow = false;
                    title_activityTV.setVisibility(View.VISIBLE);
               }
            }
        });*/
        runningLoanCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addRuningLoanBT.setVisibility(View.VISIBLE); // on the visibility of the button
                    setRunningLoanLayFromDB();
                } else {
                    shgMemberLoanLL.removeAllViews(); //After uncheck to delete the whole view of the shgMember running loan
                    addRuningLoanBT.setVisibility(View.GONE);
                    deleteRuningLoanBT.setVisibility(View.GONE);
                }
            }
        });

        // adapterCalling();
    }

    private void dbValuesOverTheForm() {
        MemberCutOffEntity memberCutOffData=masterDataRepo.getMemberCutOff(appSharedPreferences.getMemberCutOffSelectedMemberCode());
        if(memberCutOffData!=null)
        {
        meetingsSinceBeigning.setText(memberCutOffData.attended_mettings_since_beignning);
        numberOfCloseLoanEt.setText(memberCutOffData.number_of_close_loan);
        amountOfClosedLoanEt.setText(memberCutOffData.amount_of_close_loan);
        meetingsSinceBeigning.setFocusable(false);
        numberOfCloseLoanEt.setFocusable(false);
        amountOfClosedLoanEt.setFocusable(false);
        }
        if(memberCutOffSavingData!=null)
        {
            setMemberCutOffSavinFromDB();
        }
        if(memberCutOffRunningLoanData!=null)
        {
                setRunningLoanLayFromDB();
        }

    }

    private void setMemberCutOffSavinFromDB() {
        for(MemberCutOffSavingEntity memberCutOffSavingObj:memberCutOffSavingData) {
            View savingView = getLayoutInflater().inflate(R.layout.member_cutoff_saving_custom_lay, null, false);   //Set the view including the spinner and its type and amount
            addImg.setVisibility(View.GONE);
            AppCompatSpinner savingTypeSp = (AppCompatSpinner) savingView.findViewById(R.id.savingtypeSp);
            savingTypeSp.setFocusable(false);
            EditText savingET = savingView.findViewById(R.id.saving_amount);
            ImageView imageRemv = (ImageView) savingView.findViewById(R.id.image_remove);
           /* imageRemv.setOnClickListener(new View.OnClickListener() {                //if you what editable uncomment it
                @Override
                public void onClick(View v) {
                    //  view.getVerticalScrollbarPosition();
                    // TextView savingcout= (TextView)view.findViewById(R.id.snoTv);
                    //savingCount=Integer.valueOf(savingcout.getText().toString())-1;
              *//*  if(count>=1)
              //to remove the saving type
                {
                    AppCompatSpinner savingAdditionSp    =(AppCompatSpinner)view.findViewById(R.id.savingtypeSp);
                    masterMemberSavingTypeNameData.add(savingAdditionSp.getSelectedItem().toString());
                    //savingAdditionSp.get
                }*//*
                   String savingName= masterMemberSavingTypeNameData.get(savingTypeSp.getSelectedItemPosition());
                   masterDataRepo.deleteMemberSaving(appSharedPreferences.getMemberCutOffSelectedMemberCode(),savingName);
                   removeSavingView(savingView);             //Removing the view of saving type
                }
            });*/
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, masterMemberSavingTypeNameData);   // set the saving type over the spinner
            savingTypeSp.setAdapter(arrayAdapter);
            int spinnerPosition = arrayAdapter.getPosition(memberCutOffSavingObj.saving_type);
            savingTypeSp.setSelection(spinnerPosition);
            savingET.setText(memberCutOffSavingObj.amount);
            layoutlist.addView(savingView);
        }

    }
    private void setMemberRuningLoanLay() {
        View runnningLoanview=getLayoutInflater().inflate(R.layout.shg_member_loan_custom_lay,null,false);//Adding the running loan layout
        TextInputEditText loanNumber= runnningLoanview.findViewById(R.id.loan_numberET);
        TextInputEditText loanDateET=runnningLoanview.findViewById(R.id.loan_dateET);
        loanDateET.setFocusable(false);
        loanDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new MyDatePicker(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        java.util.Calendar cal = GregorianCalendar.getInstance();
                        cal.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String currentDateandTime = sdf.format(cal.getTime());
                        loanDateET.setText(currentDateandTime);
                    }
                }, MemberCutOffLayActivity.this);
                dialogFragment.show(getSupportFragmentManager(), "Date");
            }
        });

        loanCount++;
        if (memberCutOffRunningLoanData.size()!=0 && acc==0) {
            loanCount=memberCutOffRunningLoanData.size()+1;
            loanNumber.setText(""+loanCount);
            loanNumber.setFocusable(false);
            acc=1;
        }else {
            loanNumber.setText(""+loanCount);
            loanNumber.setFocusable(false);
        }
         AutoCompleteTextView loanSourceSp=(AutoCompleteTextView)runnningLoanview.findViewById(R.id.loan_sourceSP);
         ArrayAdapter loansourceAd=new ArrayAdapter(this,R.layout.spinner_textview,loanSourceName);
         loanSourceSp.setAdapter(loansourceAd);
         loansourceAd.notifyDataSetChanged();
        AutoCompleteTextView purposeSp=(AutoCompleteTextView)runnningLoanview.findViewById(R.id.purposeSP);
        ArrayAdapter purposeAd=new ArrayAdapter(this,R.layout.spinner_textview,cutoffMemberPurposeNameData);
        purposeSp.setAdapter(purposeAd);
        purposeAd.notifyDataSetChanged();
                /*            deleteRuningLoanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRunningLoanLay(view);                   //Removing the running loan layout
            }
        });*/
        deleteRuningLoanBT.setVisibility(View.VISIBLE);    //if you want to made editable
        shgMemberLoanLL.addView(runnningLoanview);          //Adding the running loan view
        deleteRuningLoanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               View runningLoanChildView=shgMemberLoanLL.getChildAt(shgMemberLoanLL.getChildCount()-1);
                // TextInputEditText loanNumber=runningLoanChildView.findViewById(R.id.loan_numberET);
                //masterDataRepo.deleteRuningLoan(loanNumber.getText().toString());
                removeRunningLoanLay(runnningLoanview);
                if(!isRunningLoanChildAv())
                    deleteRuningLoanBT.setVisibility(View.GONE);

            }
        });

    }

    private void removeRunningLoanLay(View view) {
         loanCount--;
         shgMemberLoanLL.removeView(view);      //Running loan removed
    }
    private void initialization() {
         appSharedPreferences=new AppSharedPreferences(this);
        dateFactory=DateFactory.getInstance(MemberCutOffLayActivity.this);
         appUtils=new AppUtils();
         masterDataRepo=new MasterDataRepo(getApplication());
         memberCutOffRunningLoanData=masterDataRepo.getRuningLoanData(appSharedPreferences.getMemberCutOffSelectedMemberCode());
         memberCutOffSavingData=masterDataRepo.getMemberCutOffSavingData(appSharedPreferences.getMemberCutOffSelectedMemberCode());
         masterMemberSavingTypeNameData = masterDataRepo.getMasterMemberSavingName();   //Fatch the member saving type through the database
         cutoffMemberPurposeNameData=(ArrayList<String>) new MasterDataRepo(getApplication()).getMasterCuttOffMemberPurposeName();
         loanSourceName=(ArrayList<String>) new MasterDataRepo(getApplication()).getloanSourceName();
         selectedShgNameCutOff  =new ShgDataRepo(getApplication()).getshgName(appSharedPreferences.getSelectedShgCutOffSc());
         selectedMemberNameMemCutSC=new ShgmemberRepo(getApplication()).getMemberObject(appSharedPreferences.getMemberCutOffSelectedMemberCode()).memberName;
    }
//    private void adapterCalling() {
        ///   MemberCutOffSavingAdapter memberCutOffSavingAdapter=new MemberCutOffSavingAdapter(MemberCutOffLayActivity.this,masterMemberSavingType,addBt);
        //savingFormemberRv.setLayoutManager(new LinearLayoutManager(MemberCutOffLayActivity.this));
        // savingFormemberRv.setAdapter(memberCutOffSavingAdapter);
        /// memberCutOffSavingAdapter.notifyDataSetChanged();
  ///  }
    @OnClick(R.id.add_runing_loanBT)
     public void addRuningLoanView()
    {

     setMemberRuningLoanLay();    //Adding the running loan layout
    }
    @OnClick(R.id.button_add)
    public void addView() {
      //  savingCount++;
        View savingView = getLayoutInflater().inflate(R.layout.member_cutoff_saving_custom_lay, null, false);   //Set the view including the spinner and its type and amount
        AppCompatSpinner savingTypeSp = (AppCompatSpinner) savingView.findViewById(R.id.savingtypeSp);
        addImg.setVisibility(View.GONE);
        ImageView imageRemv = savingView.findViewById(R.id.image_remove);
      //  TextView snoTv = view.findViewById(R.id.snoTv);
      //  layoutlist.getChildCount();
        //snoTv.setText(""+savingCount);
        EditText savingET = savingView.findViewById(R.id.saving_amount);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, masterMemberSavingTypeNameData);   // set the saving type over the spinner
        savingTypeSp.setAdapter(arrayAdapter);
        imageRemv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  view.getVerticalScrollbarPosition();
              // TextView savingcout= (TextView)view.findViewById(R.id.snoTv);
                //savingCount=Integer.valueOf(savingcout.getText().toString())-1;
              /*  if(count>=1)
              //to remove the saving type
                {
                    AppCompatSpinner savingAdditionSp    =(AppCompatSpinner)view.findViewById(R.id.savingtypeSp);
                    masterMemberSavingTypeNameData.add(savingAdditionSp.getSelectedItem().toString());
                    //savingAdditionSp.get
                }*/
                String savingName= masterMemberSavingTypeNameData.get(savingTypeSp.getSelectedItemPosition());
                masterDataRepo.deleteMemberSaving(appSharedPreferences.getMemberCutOffSelectedMemberCode(),savingName);
                removeSavingView(savingView);             //Removing the view of saving type
            }
        });
        if(layoutlist.getChildCount()<5) {                //Adding the view of saving type
            layoutlist.addView(savingView);
              /*    if(count>=1) {
                      removeSavingNameOnAddition(layoutlist.getChildAt(count));   //to remove from the saving type
                  }
                count++;*/
        } else
        {
            new MaterialDialogFactory(this).alertDialog("You are receiving this alert because your saving details have reached the maximum limit.", "Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

              }
          },false);
        }
    }
    @OnClick(R.id.cutOffSubmitBtn)
    public void onClickOfSubmit()
    {
        MemberCutOffEntity memberCutOffData=masterDataRepo.getMemberCutOff(appSharedPreferences.getMemberCutOffSelectedMemberCode());
               if(memberCutOffData==null) {
                   getDataFromMemberCutOffForm();
               }
        getDataFromMemberCutOffSavingForm();
        getDataFromMemberLoanForm();

        syncJson();
    }
    private void syncJson() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.accumulate("cut_off",getJsonArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject getJsonArray()
    {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        try {
        List<MemberCutOffEntity> memberCutOffNotsyncData=masterDataRepo.getNotSyncData("0");   //to get the memberCutOff data
        for(int i=0;i<memberCutOffNotsyncData.size();i++) {
        jsonObject.accumulate("member_cutoff", getJsonObjectForMemberCutOff());
            jsonArray.put(jsonObject);
         }
        } catch (JSONException e) {
        e.printStackTrace();
        }
        return jsonObject;
    }

    private Object getJsonObjectForMemberCutOff() {
        JSONArray array=new JSONArray();
        return array;
    }
    private void getDataFromMemberCutOffSavingForm() {
         try {
             for (int i = 1; i < layoutlist.getChildCount(); i++) {
                 View savingView = layoutlist.getChildAt(i);
                 AppCompatSpinner savingTypeSp =(AppCompatSpinner) savingView.findViewById(R.id.savingtypeSp);
                 EditText editTextEt = (EditText) savingView.findViewById(R.id.saving_amount);
                 MemberCutOffSavingEntity memberCutOffSavingEntity = new MemberCutOffSavingEntity();
                 memberCutOffSavingEntity.member_saving_id = "";
                 memberCutOffSavingEntity.shg_code = appSharedPreferences.getSelectedShgCutOffSc();
                 memberCutOffSavingEntity.amount = editTextEt.getText().toString();
                 memberCutOffSavingEntity.member_code = appSharedPreferences.getMemberCutOffSelectedMemberCode();
                 String selectedSaving=  masterMemberSavingTypeNameData.get(savingTypeSp.getSelectedItemPosition());
                 memberCutOffSavingEntity.saving_type_code=masterDataRepo.getSavingTypeCode(selectedSaving);
                 memberCutOffSavingEntity.saving_type =selectedSaving; //need to change in id
                 memberCutOffSavingEntity.sync_status = "0";
                 masterDataRepo.insertMemberCutOffSaving(memberCutOffSavingEntity);
             }
         }catch (Exception e)
         {
             appUtils.showLog("Error in getDataFromMemberCutOffSavingForm"+e,MemberCutOffLayActivity.class);
         }
    }
    private void getDataFromMemberCutOffForm() {
        MemberCutOffEntity memberCutOffEntity=new MemberCutOffEntity();
        memberCutOffEntity.member_cutOff_id="";
        memberCutOffEntity.shg_code=appSharedPreferences.getSelectedShgCutOffSc();
        memberCutOffEntity.member_code=appSharedPreferences.getMemberCutOffSelectedMemberCode();
        memberCutOffEntity.number_of_close_loan=numberOfCloseLoanEt.getText().toString();
        memberCutOffEntity.amount_of_close_loan=amountOfClosedLoanEt.getText().toString();
        memberCutOffEntity.attended_mettings_since_beignning=meetingsSinceBeigning.getText().toString();
        masterDataRepo.insertMemberCuttOff(memberCutOffEntity);
    }
    private void getDataFromMemberLoanForm() {
          for(int i=memberCutOffRunningLoanData.size();i<shgMemberLoanLL.getChildCount();i++)
          {
              View loanFormVw=shgMemberLoanLL.getChildAt(i);
              AutoCompleteTextView loansourceSp=loanFormVw.findViewById(R.id.loan_sourceSP);
              TextInputEditText loanNumber=loanFormVw.findViewById(R.id.loan_numberET);
              TextInputEditText  loanDate=loanFormVw.findViewById(R.id.loan_dateET);
              TextInputEditText loanAmount=loanFormVw.findViewById(R.id.loan_amountET);
              TextInputEditText rateOfIntrest=loanFormVw.findViewById(R.id.rate_intrestET);
              TextInputEditText numberOfInstallment=loanFormVw.findViewById(R.id.numberof_inslamentET);
              TextInputEditText  instalmentAmount=loanFormVw.findViewById(R.id.instalment_amt_ET);
              TextInputEditText  repaymentStartinigMonth=loanFormVw.findViewById(R.id.repayment_str_ET);
              TextInputEditText amountPaid=loanFormVw.findViewById(R.id.amout_paidET);
              AutoCompleteTextView purposeSp=loanFormVw.findViewById(R.id.purposeSP);
              TextInputEditText principalOverdue=loanFormVw.findViewById(R.id.principal_ovrET);
              TextInputEditText intrestOverdue=loanFormVw.findViewById(R.id.intrest_ovrET);
          /*    public  String  member_running_loan_id;
              public String shg_code;
              public String member_code;
              public String loan_source;
              public String loan_number;
              public String loan_date;
              public String loan_amount;
              public String rate_of_intrest;
              public String number_of_installments;
              public String repayment_starting_month;
              public String amount_paid;
              public String purpose;
              public String principal_overdue;
              public String intrest_overdue;
              public String member_running_loan_sync_status;*/
              MemberCutOffRunningLoanEntity memberCutOffRunningLoanEntity=new MemberCutOffRunningLoanEntity();
              memberCutOffRunningLoanEntity.member_running_loan_id="";
              memberCutOffRunningLoanEntity.shg_code=appSharedPreferences.getSelectedShgCutOffSc();
              memberCutOffRunningLoanEntity.member_code=appSharedPreferences.getMemberCutOffSelectedMemberCode();
              memberCutOffRunningLoanEntity.loan_source=loansourceSp.getText().toString();
              memberCutOffRunningLoanEntity.loan_number=loanNumber.getText().toString();
              memberCutOffRunningLoanEntity.loan_date=loanDate.getText().toString();
              memberCutOffRunningLoanEntity.loan_amount=loanAmount.getText().toString();
              memberCutOffRunningLoanEntity.rate_of_intrest=rateOfIntrest.getText().toString();
              memberCutOffRunningLoanEntity.number_of_installments= numberOfInstallment.getText().toString();
              memberCutOffRunningLoanEntity.installments_amount=instalmentAmount.getText().toString();
              memberCutOffRunningLoanEntity.repayment_starting_month=repaymentStartinigMonth.getText().toString();
              memberCutOffRunningLoanEntity.amount_paid=amountPaid.getText().toString();
              memberCutOffRunningLoanEntity.purpose=purposeSp.getText().toString();
              memberCutOffRunningLoanEntity.principal_overdue=principalOverdue.getText().toString();
              memberCutOffRunningLoanEntity.intrest_overdue=intrestOverdue.getText().toString();
              memberCutOffRunningLoanEntity.member_running_loan_sync_status="";
              masterDataRepo.insertAllMemberRunningLoan(memberCutOffRunningLoanEntity);
             }
    }
    private void removeSavingNameOnAddition(View view) {
        View savingView=view;
        AppCompatSpinner savingSP=(AppCompatSpinner) savingView.findViewById(R.id.savingtypeSp);
        for(int i=0;i<masterMemberSavingTypeNameData.size();i++)
        {
            if(masterMemberSavingTypeNameData.get(i).equalsIgnoreCase( savingSP.getSelectedItem().toString()))
            {
                masterMemberSavingTypeNameData.remove(i);
            }
        }
    }

    private void setRunningLoanLayFromDB()
    {
        for (MemberCutOffRunningLoanEntity memberCutOffRunningLoanObj:memberCutOffRunningLoanData) {
            View runnningLoanview = getLayoutInflater().inflate(R.layout.shg_member_loan_custom_lay, null, false);//Adding the running loan layout
            TextInputEditText loanNumber = runnningLoanview.findViewById(R.id.loan_numberET);
            TextInputEditText loanDate = runnningLoanview.findViewById(R.id.loan_dateET);
            TextInputEditText loanAmount = runnningLoanview.findViewById(R.id.loan_amountET);
            TextInputEditText rateOfIntrest = runnningLoanview.findViewById(R.id.rate_intrestET);
            TextInputEditText numberOfInstallment = runnningLoanview.findViewById(R.id.numberof_inslamentET);
            TextInputEditText instalmentAmount = runnningLoanview.findViewById(R.id.instalment_amt_ET);
            TextInputEditText repaymentStartinigMonth = runnningLoanview.findViewById(R.id.repayment_str_ET);
            TextInputEditText amountPaid = runnningLoanview.findViewById(R.id.amout_paidET);
            TextInputEditText principalOverdue = runnningLoanview.findViewById(R.id.principal_ovrET);
            TextInputEditText intrestOverdue = runnningLoanview.findViewById(R.id.intrest_ovrET);
            loanNumber.setText(memberCutOffRunningLoanObj.loan_number);
            loanNumber.setFocusable(false);
            loanDate.setText(memberCutOffRunningLoanObj.loan_date);
            loanAmount.setText(memberCutOffRunningLoanObj.loan_amount);
            rateOfIntrest.setText(memberCutOffRunningLoanObj.rate_of_intrest);
            numberOfInstallment.setText(memberCutOffRunningLoanObj.number_of_installments);
            instalmentAmount.setText(memberCutOffRunningLoanObj.installments_amount);
            repaymentStartinigMonth.setText(memberCutOffRunningLoanObj.repayment_starting_month);
            amountPaid.setText(memberCutOffRunningLoanObj.amount_paid);
            principalOverdue.setText(memberCutOffRunningLoanObj.principal_overdue);
            intrestOverdue.setText(memberCutOffRunningLoanObj.intrest_overdue);
            AutoCompleteTextView loanSourceSp = (AutoCompleteTextView) runnningLoanview.findViewById(R.id.loan_sourceSP);
            loanSourceSp.setText(memberCutOffRunningLoanObj.loan_source);
            ArrayAdapter loansourceAd = new ArrayAdapter(this, R.layout.spinner_textview, loanSourceName);
            loanSourceSp.setAdapter(loansourceAd);
            loansourceAd.notifyDataSetChanged();
            AutoCompleteTextView purposeSp = (AutoCompleteTextView) runnningLoanview.findViewById(R.id.purposeSP);
            purposeSp.setText(memberCutOffRunningLoanObj.purpose);
            ArrayAdapter purposeAd = new ArrayAdapter(this, R.layout.spinner_textview, cutoffMemberPurposeNameData);
            purposeSp.setAdapter(purposeAd);
            purposeAd.notifyDataSetChanged();
            loanNumber.setFocusable(false);
            loanDate.setFocusable(false);
            loanAmount.setFocusable(false);
            rateOfIntrest.setFocusable(false);
            numberOfInstallment.setFocusable(false);
            instalmentAmount.setFocusable(false);
            repaymentStartinigMonth.setFocusable(false);
            amountPaid.setFocusable(false);
            principalOverdue.setFocusable(false);
            intrestOverdue .setFocusable(false);
            purposeSp.setFocusable(false);
            purposeSp.clearFocus();

            loanSourceSp.setFocusable(false);
            loanSourceSp.clearFocus();

                /*            deleteRuningLoanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRunningLoanLay(view);                   //Removing the running loan layout
            }
        });*/
           // deleteRuningLoanBT.setVisibility(View.VISIBLE);
            addRuningLoanBT.setVisibility(View.VISIBLE);
            runningLoanCB.setChecked(true);

            shgMemberLoanLL.addView(runnningLoanview);
            //Adding the running loan view
        }
    }
   private boolean isRunningLoanChildAv()
    {
        if(shgMemberLoanLL.getChildCount()>0)
            return true;
        else
            return false;
    }
    private void removeSavingView(View view) {
        layoutlist.removeView(view);
    }
}