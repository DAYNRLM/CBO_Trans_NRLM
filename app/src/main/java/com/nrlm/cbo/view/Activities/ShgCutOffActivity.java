package com.nrlm.cbo.view.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
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
import com.nrlm.cbo.Utils.MyDatePicker;
import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyBranchEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyNameEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffFixedDepositEntity;
import com.nrlm.cbo.database.room.entities.ShgCutoffEntity;
import com.nrlm.cbo.database.room.repositories.BankBranchRepo;
import com.nrlm.cbo.database.room.repositories.BankRepo;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgCutOffRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.model.response.LittleMasterData;
import com.nrlm.cbo.view.fragments.cutOffFragment.SHGCutOffFragment;
import com.nrlm.cbo.view.interfaces.OnBackPressedListener;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShgCutOffActivity extends AppCompatActivity implements OnBackPressedListener {
    OnBackPressedListener onBackPressedListener;

    /*****all text view******/
    @BindView(R.id.tbTitle)
    TextView tbTitle;

    @BindView(R.id.shgName_Tv)
    TextView shgName_Tv;


    /***********all edit text***************/
    @BindView(R.id.meetingConductedEt)
    TextInputEditText meetingConductedEt;

    @BindView(R.id.lastMeetingDateET)
    TextInputEditText lastMeetingDateET;

    @BindView(R.id.shgCutoffDateEt)
    TextInputEditText shgCutoffDateEt;

    @BindView(R.id.fdNumberEt)
    TextInputEditText fdNumberEt;

    @BindView(R.id.fdAmountEt)
    TextInputEditText fdAmountEt;

    @BindView(R.id.cashInHandET)
    TextInputEditText cashInHandET;

    @BindView(R.id.cashInBankET)
    TextInputEditText cashInBankET;

    @BindView(R.id.savingVoET)
    TextInputEditText savingVoET;

    @BindView(R.id.savingClfET)
    TextInputEditText savingClfET;

    @BindView(R.id.capitalVoET)
    TextInputEditText capitalVoET;

    @BindView(R.id.capitalClfET)
    TextInputEditText capitalClfET;

    @BindView(R.id.grantSrlmET)
    TextInputEditText grantSrlmET;

    @BindView(R.id.rfRecivedSrlmET)
    TextInputEditText rfRecivedSrlmET;

    @BindView(R.id.rfReutrnedSrlmET)
    TextInputEditText rfReutrnedSrlmET;

    @BindView(R.id.cifRecivedSrlmET)
    TextInputEditText cifRecivedSrlmET;

    @BindView(R.id.cifReturnedSlrmET)
    TextInputEditText cifReturnedSlrmET;

    @BindView(R.id.otherReciptET)
    TextInputEditText otherReciptET;

    @BindView(R.id.grantOtherET)
    TextInputEditText grantOtherET;

    @BindView(R.id.vrfGrantNrlmET)
    TextInputEditText vrfGrantNrlmET;

    @BindView(R.id.closedLoanFromBankET)
    TextInputEditText closedLoanFromBankET;

    @BindView(R.id.closedAmountBankET)
    TextInputEditText closedAmountBankET;

    @BindView(R.id.closedLoanVoET)
    TextInputEditText closedLoanVoET;

    @BindView(R.id.amountClosedVoET)
    TextInputEditText amountClosedVoET;

    @BindView(R.id.closedLoanClfET)
    TextInputEditText closedLoanClfET;

    @BindView(R.id.closedAmountClfET)
    TextInputEditText closedAmountClfET;


    /************all spinner*******************/
    @BindView(R.id.fdSpinner)
    AutoCompleteTextView fdSpinner;

    @BindView(R.id.bankCompanySpinner)
    AutoCompleteTextView bankCompanySpinner;

    @BindView(R.id.bankCompanyNameSpinner)
    AutoCompleteTextView bankCompanyNameSpinner;

    @BindView(R.id.branchnameSpineer)
    AutoCompleteTextView branchnameSpineer;

    /**********text input layout*****************/
    @BindView(R.id.bankNameTIL)
    TextInputLayout bankNameTIL;

    @BindView(R.id.branchTIL)
    TextInputLayout branchTIL;

    @BindView(R.id.meetingInputLayout)
    TextInputLayout meetingInputLayout;

    @BindView(R.id.bankCompnyTIL)
    TextInputLayout bankCompnyTIL;

    @BindView(R.id.fdNumberTIL)
    TextInputLayout fdNumberTIL;

    @BindView(R.id.fdAmountTIL)
    TextInputLayout fdAmountTIL;

    @BindView(R.id.shgCutoffDateTTL)
    TextInputLayout shgCutoffDateTTL;

    @BindView(R.id.cashInBankTTL)
    TextInputLayout cashInBankTTL;

    @BindView(R.id.cashInHandTTL)
    TextInputLayout cashInHandTTL;

    @BindView(R.id.savingVoTTL)
    TextInputLayout savingVoTTL;

    @BindView(R.id.savingClfTTL)
    TextInputLayout savingClfTTL;

    @BindView(R.id.capitalVoTTL)
    TextInputLayout capitalVoTTL;

    @BindView(R.id.capitalClfTTL)
    TextInputLayout capitalClfTTL;

    @BindView(R.id.grantSrlmTTL)
    TextInputLayout grantSrlmTTL;

    @BindView(R.id.rfRecivedSrlmTTL)
    TextInputLayout rfRecivedSrlmTTL;

    @BindView(R.id.rfReutrnedSrlmTTL)
    TextInputLayout rfReutrnedSrlmTTL;

    @BindView(R.id.cifRecivedSrlmTTL)
    TextInputLayout cifRecivedSrlmTTL;

    @BindView(R.id.cifReturnedSlrmTTL)
    TextInputLayout cifReturnedSlrmTTL;

    @BindView(R.id.otherReciptTTL)
    TextInputLayout otherReciptTTL;

    @BindView(R.id.grantOtherTTL)
    TextInputLayout grantOtherTTL;

    @BindView(R.id.vrfGrantNrlmTTL)
    TextInputLayout vrfGrantNrlmTTL;

    @BindView(R.id.closedLoanFromBankTTL)
    TextInputLayout closedLoanFromBankTTL;

    @BindView(R.id.closedAmountBankTTL)
    TextInputLayout closedAmountBankTTL;

    @BindView(R.id.closedLoanVoTTL)
    TextInputLayout closedLoanVoTTL;

    @BindView(R.id.amountClosedVoTTL)
    TextInputLayout amountClosedVoTTL;

    @BindView(R.id.closedLoanClfTTL)
    TextInputLayout closedLoanClfTTL;

    @BindView(R.id.closedAmountClfTTL)
    TextInputLayout closedAmountClfTTL;


    /**********all btn****************/
    @BindView(R.id.cutoffBackBtn)
    MaterialButton cutoffBackBtn;

    @BindView(R.id.cutOffSubmitBtn)
    MaterialButton cutOffSubmitBtn;

    @BindView(R.id.cutOffCloseBtn)
    MaterialButton cutOffCloseBtn;

    @BindView(R.id.addCompanyBtn)
    MaterialButton addCompanyBtn;

    @BindView(R.id.addCompanyBranchBtn)
    MaterialButton addCompanyBranchBtn;

    @BindView(R.id.addRunningLoanBtn)
    MaterialButton addRunningLoanBtn;

    @BindView(R.id.addRunningInsuranceBtn)
    MaterialButton addRunningInsuranceBtn;

    @BindView(R.id.saveCutoffBtn)
    MaterialButton saveCutoffBtn;

    @BindView(R.id.updateCutoffBtn)
    MaterialButton updateCutoffBtn;

    @BindView(R.id.runningLoanCB)
    CheckBox runningLoanCB;

    @BindView(R.id.runninginsuranceCB)
    CheckBox runninginsuranceCB;


    /***************all layout***************/
    @BindView(R.id.addRunningLoanLL)
    LinearLayout addRunningLoanLL;

    @BindView(R.id.addRunningInsuranceLL)
    LinearLayout addRunningInsuranceLL;


    /***********all card view***********/
    @BindView(R.id.fdCardview)
    MaterialCardView fdCardview;


    /****all utils classes and repository*****/
    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    CustomProgressDialog customProgressDialog;
    AppUtils appUtils;
    DateFactory dateFactory;

    BankRepo bankRepo;
    BankBranchRepo bankBranchRepo;
    ShgmemberRepo shgmemberRepo;
    ShgDataRepo shgDataRepo;
    MasterDataRepo masterDataRepo;
    ShgSettingSavingFromMemberRepo shgSettingSavingFromMemberRepo;
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

    String shgFormationDate = "";
    String todayDate = "";
    String formSubmitStatus = "";
    int count = 0;

    String meetingConducted = "";
    String lastMeetingDate = "";
    String shgCutoffDate = "";
    String cashInHand = "";
    String cashInBank = "";
    String fdId = "";
    String savingWithVo = "";
    String shareCapitalVo = "";
    String savingWithClf = "";
    String shareCapitalClf = "";
    String grantSrlm = "";
    String rfRecivedSrlm = "";
    String rfReturnedSrlm = "";
    String cifRecivedSrlm = "";
    String cifReturnedSrlm = "";
    String otherReceipt = "";
    String grantOtherResource = "";
    String vrfGrantNrlm = "";
    String closedLoanBank = "";
    String amountBank = "";
    String closedLoanVo = "";
    String amountVo = "";
    String closedLoanClf = "";
    String amountClf = "";



    /***********all data list***********/
    List<MasterCutoffBankCompnyEntity> bankCompanyDataList;
    List<MasterCutoffFixedDepositEntity> yesNoDataList;
    List<BankEntity> bankDataList;
    List<BankBranchEntity> bankBranchDataList;
    List<MasterCutoffCompanyNameEntity> companyNamedatalist;
    List<MasterCutoffCompanyBranchEntity> companyBranchNamedatalist;
    List<ShgCutoffEntity> shgCutoffDataList;


    /*******all adapter****************/
    ArrayAdapter<String> bankCompanyAdapter;
    ArrayAdapter<String> yesNoAdapter;
    ArrayAdapter<String> bankAdapter;
    ArrayAdapter<String> branchAdapter;
    ArrayAdapter<String> companyAdapter;
    ArrayAdapter<String> companyBranchAdapter;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_cut_off);
        ButterKnife.bind(this);
        getAllInstance();
        tbTitle.setText("SHG Cut-Off");

        shgCode = appSharedPreferences.getShgCodeForVerification();
        shgName = shgDataRepo.getshgName(shgCode);

        shgFormationDate = shgDataRepo.getFormationDate(shgCode);//dd-mm-yyyy
        todayDate = dateFactory.getTodayDate();//dd-mm-yyyy

        shgName_Tv.setText(shgName + " SHG" + " (" + shgCode + ")");

        count = dateFactory.getCountFWM(shgFormationDate, todayDate, "M");// get this M-W-F from settings
        shgCutoffDataList = shgCutOffRepo.getShgCutOffData(shgCode);

        //openDataPicker();
        setBtnVisibility();

        loadMasterData();

        setAllSpinnerListner();

        meetingConductedEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int getDate = Integer.parseInt(meetingConductedEt.getText().toString().trim());
                    if (getDate > count) {
                        meetingCountDialog();
                    }
                }
            }
        });
        runningLoanCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    addRunningLoanLL.setVisibility(View.VISIBLE);
                } else {
                    addRunningLoanLL.setVisibility(View.GONE);
                }
            }
        });

        runninginsuranceCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addRunningInsuranceLL.setVisibility(View.VISIBLE);
                } else {
                    addRunningInsuranceLL.setVisibility(View.GONE);
                }
            }
        });







        // AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), new SHGCutOffFragment(), ShgCutOffActivity.class.getName(), true, R.id.cutoffFramLayout);
    }

    @Override
    public void doBack() {
        Toast.makeText(ShgCutOffActivity.this, "Hello in frag", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void openDataPicker() {
       /* LocalDateTime local = LocalDateTime.of(year, month, day, 0, 0);
        local.atZone(ZoneId.ofOffset("UTC", ZoneOffset.UTC)).toInstant().toEpochMilli();*/

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();


        long today = MaterialDatePicker.todayInUtcMilliseconds();

        calendar.setTimeInMillis(today);



      /*  calendar.roll(Calendar.MONTH,Calendar.FEBRUARY);
        long feb =calendar.getTimeInMillis();

        calendar.roll(Calendar.MONTH,Calendar.MARCH);
        long mar =calendar.getTimeInMillis();

        calendar.set(Calendar.MONTH,Calendar.FEBRUARY);
        long feb =calendar.getTimeInMillis();

        calendar.set(Calendar.MONTH,Calendar.MARCH);
        long mar =calendar.getTimeInMillis();

        calendar.set(2021,2,1);
        long start = calendar.getTimeInMillis();

        calendar.set(2021,2,25);
        long end = calendar.getTimeInMillis();*/


        CalendarConstraints.Builder builder = new CalendarConstraints.Builder();
       /* builder.setStart(feb);
        builder.setEnd(mar);*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse("2021-03-01");
            long starDateTime = d.getTime();


            Date d2 = sdf.parse("2021-03-25");
            long endDateTime = d2.getTime();
            builder.setStart(starDateTime);
            builder.setEnd(endDateTime);


        } catch (ParseException e) {
            e.printStackTrace();

        }

//        builder.setStart(start);
//        builder.setEnd(end);


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setSelection(today);
        materialDateBuilder.setCalendarConstraints(builder.build());
        materialDateBuilder.setTitleText("SELECT A DATE");

        MaterialDatePicker materialDatePicker = materialDateBuilder.build();


        lastMeetingDateET.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });

        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        lastMeetingDateET.setText("" + materialDatePicker.getHeaderText());

                    }
                });
    }

    @OnClick(R.id.cutOffCloseBtn)
    public void closeClick() {

    }


    @OnClick(R.id.cutOffSubmitBtn)
    public void submitClick() {
        if(!formSubmitStatus.isEmpty()){
            shgDataRepo.updateShgCutoffStatus(shgCode,"1");
            customProgressDialog.showProgress(AppConstant.DIALOG_MSG,false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    appUtils.makeIntent(ShgCutOffActivity.this, CutOffActivity.class, true);
                }
            },2000);
        }else {
            Toast.makeText(ShgCutOffActivity.this,"First Save the Cut-off form data...",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.cutoffBackBtn)
    public void backClick() {

    }

    @OnClick(R.id.lastMeetingDateET)
    public void lastMeetingDate() {
        String getTodatDate = dateFactory.changeFormateYYMMDD(todayDate);
        String formationDate = dateFactory.changeFormateYYMMDD(shgFormationDate);
        DialogFragment dialogFragment = new MyDatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                java.util.Calendar cal = GregorianCalendar.getInstance();
                cal.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDateandTime = sdf.format(cal.getTime());
                lastMeetingDateET.setText(currentDateandTime);
            }
        }, dateFactory.getCalendarTime(formationDate), dateFactory.getCalendarTime(getTodatDate), ShgCutOffActivity.this);
        dialogFragment.show(getSupportFragmentManager(), "Date");

    }

    @OnClick(R.id.shgCutoffDateEt)
    public void shgCutoffDate() {
        lastMeetingDate = lastMeetingDateET.getText().toString().trim();
        if (!lastMeetingDate.isEmpty()) {
            String getTodatDate = dateFactory.changeFormateYYMMDD(todayDate);
            DialogFragment dialogFragment = new MyDatePicker(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    java.util.Calendar cal = GregorianCalendar.getInstance();
                    cal.set(year, monthOfYear, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDateandTime = sdf.format(cal.getTime());
                    shgCutoffDateEt.setText(currentDateandTime);
                }
            }, dateFactory.getCalendarTime(lastMeetingDate), dateFactory.getCalendarTime(getTodatDate), ShgCutOffActivity.this);
            dialogFragment.show(getSupportFragmentManager(), "Date");
        } else {
            //set dilog if required
            Toast.makeText(ShgCutOffActivity.this, "First Select Last Meeting date..", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.addCompanyBtn)
    public void addNewCompany() {
        TextView dialogTitleTv;
        MaterialButton closeBtn, okBtn;
        TextInputEditText dialogCompanyET;
        TextInputLayout dialogCompanyTIL;
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ShgCutOffActivity.this);
        View customLayout = getLayoutInflater().inflate(R.layout.company_branch_custom_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog dialog = materialAlertDialogBuilder.show();

        dialogTitleTv = dialog.findViewById(R.id.dialogTitleTv);
        closeBtn = dialog.findViewById(R.id.closeBtn);
        okBtn = dialog.findViewById(R.id.okBtn);
        dialogCompanyET = dialog.findViewById(R.id.dialogCompanyET);
        dialogCompanyTIL = dialog.findViewById(R.id.dialogCompanyTIL);

        dialogTitleTv.setText("Add New Company");
        dialogCompanyTIL.setHint("Enter Company Name");


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getCompanyName = dialogCompanyET.getText().toString();
                if (getCompanyName.isEmpty()) {
                    Toast.makeText(ShgCutOffActivity.this, "Please Enter a company Name..", Toast.LENGTH_SHORT).show();
                } else {
                    String localCompanyId = "MC" + appSharedPreferences.getCompanyId();
                    MasterCutoffCompanyNameEntity masterCutoffCompanyNameEntity = new MasterCutoffCompanyNameEntity();
                    masterCutoffCompanyNameEntity.company_name_id = localCompanyId;
                    masterCutoffCompanyNameEntity.company_name = getCompanyName;
                    masterCutoffCompanyNameEntity.syncStatus = "0";
                    masterDataRepo.insertAllCompany(masterCutoffCompanyNameEntity);

                    int getId = Integer.parseInt(appSharedPreferences.getCompanyId());
                    appSharedPreferences.setCompanyId(String.valueOf(getId + 1));
                    dialog.dismiss();
                    clearFocus(5);


                }

            }
        });

    }

    @OnClick(R.id.addCompanyBranchBtn)
    public void addNewCompanyBranch() {
        TextView dialogTitleTv;
        MaterialButton closeBtn, okBtn;
        TextInputEditText dialogCompanyET;
        TextInputLayout dialogCompanyTIL;
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ShgCutOffActivity.this);
        View customLayout = getLayoutInflater().inflate(R.layout.company_branch_custom_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog dialog = materialAlertDialogBuilder.show();

        dialogTitleTv = dialog.findViewById(R.id.dialogTitleTv);
        closeBtn = dialog.findViewById(R.id.closeBtn);
        okBtn = dialog.findViewById(R.id.okBtn);
        dialogCompanyET = dialog.findViewById(R.id.dialogCompanyET);
        dialogCompanyTIL = dialog.findViewById(R.id.dialogCompanyTIL);

        dialogTitleTv.setText("Add New Company Branch");
        dialogCompanyTIL.setHint("Enter Company Branch");

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getCompanyBranchName = dialogCompanyET.getText().toString();
                if (getCompanyBranchName.isEmpty()) {
                    Toast.makeText(ShgCutOffActivity.this, "Please Enter a company Branch Name..", Toast.LENGTH_SHORT).show();
                } else {
                    String getBranchId = "MCB" + appSharedPreferences.getCompanyBranchId();
                    MasterCutoffCompanyBranchEntity masterCutoffCompanyBranchEntity = new MasterCutoffCompanyBranchEntity();
                    masterCutoffCompanyBranchEntity.company_name_id = companyId;
                    masterCutoffCompanyBranchEntity.company_branch_name = getCompanyBranchName;
                    masterCutoffCompanyBranchEntity.company_branch_name_id = getBranchId;
                    masterCutoffCompanyBranchEntity.syncStatus = "0";

                    masterDataRepo.insertAllCompanyBranch(masterCutoffCompanyBranchEntity);

                    int getId = Integer.parseInt(appSharedPreferences.getCompanyBranchId());
                    appSharedPreferences.setCompanyId(String.valueOf(getId + 1));
                    dialog.dismiss();
                    clearFocus(8);

                }
            }
        });


    }

    @OnClick(R.id.runningLoanCB)
    public void clickOnRunningLoanCheckBox(){
        if(!formSubmitStatus.isEmpty()){


        }else {
            runningLoanCB.setChecked(false);
            Toast.makeText(ShgCutOffActivity.this,"First Save the Cut-off form data...",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.runninginsuranceCB)
    public void clickOnRunningInsuranceCheckBox(){
        if(!formSubmitStatus.isEmpty()){


        }else {
            runninginsuranceCB.setChecked(false);
            Toast.makeText(ShgCutOffActivity.this,"First Save the Cut-off form data...",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.addRunningLoanBtn)
    public void addRunningLoan() {

        appSharedPreferences.setCutoffScreenStatus("1");
        appUtils.makeIntent(ShgCutOffActivity.this, ShgCutoffCommonActivity.class, true);


    }

    @OnClick(R.id.addRunningInsuranceBtn)
    public void addRunningInsurance() {

        appSharedPreferences.setCutoffScreenStatus("2");
        appUtils.makeIntent(ShgCutOffActivity.this, ShgCutoffCommonActivity.class, true);


    }

    @OnClick(R.id.saveCutoffBtn)
    public void saveCutoffData() {
        getAllString();
        validateAllData();
    }

    @OnClick(R.id.updateCutoffBtn)
    public void updateCutoffData(){
        shgCutOffRepo.deleteShgCutOff(shgCode);
        getAllString();
        validateAllData();
    }

    public void clearFocus(int id) {
        switch (id) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                bnkCmpnyId = "";
                bankNameTIL.setHint("Bank Name");
                branchTIL.setHint("Bank Branch Name");
                bankCompanySpinner.setText(null);
                bankCompanyNameSpinner.setText(null);
                branchnameSpineer.setText(null);
                addCompanyBtn.setVisibility(View.GONE);


                break;
            case 4:
                /****load all data for bank *********/
                bankNameTIL.setHint("Bank Name");
                branchTIL.setHint("Bank Branch Name");
                bankCompanyNameSpinner.setText(null);
                branchnameSpineer.setText(null);

                addCompanyBtn.setVisibility(View.GONE);
                addCompanyBranchBtn.setVisibility(View.GONE);


                companyId = "";
                companyBranchId = "";
                companyBranchNamedatalist.clear();
                companyNamedatalist.clear();

                bankCode = "";
                branchCode = "";
                bankDataList.clear();
                bankBranchDataList.clear();
                loadBankData();
                break;

            case 5:
                /****load all data for company*********/
                bankNameTIL.setHint("Company Name");
                branchTIL.setHint("Company Branch Name");
                bankCompanyNameSpinner.setText(null);
                branchnameSpineer.setText(null);

                addCompanyBtn.setVisibility(View.GONE);
                addCompanyBranchBtn.setVisibility(View.GONE);


                companyId = "";
                companyBranchId = "";
                companyBranchNamedatalist.clear();
                companyNamedatalist.clear();

                bankCode = "";
                branchCode = "";
                bankDataList.clear();
                bankBranchDataList.clear();

                loadCompanyData();


                break;
            case 6:
                addCompanyBtn.setVisibility(View.VISIBLE);
                break;
            case 7:

                addCompanyBranchBtn.setVisibility(View.VISIBLE);
                break;
            case 8:
                branchnameSpineer.setText(null);
                companyBranchId = "";
                companyBranchNamedatalist.clear();
                addCompanyBranchBtn.setVisibility(View.GONE);
                loadCompanyBranchData(companyId);
                break;
        }

    }

    private void loadCompanyData() {
        companyNamedatalist = masterDataRepo.getMasterCompanyData();

        companyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getCompanyName());
        bankCompanyNameSpinner.setAdapter(companyAdapter);
        companyAdapter.notifyDataSetChanged();

        bankCompanyNameSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cName = companyNamedatalist.get(position).company_name;
                if (cName.equalsIgnoreCase("Other")) {
                    clearFocus(6);

                } else {
                    companyId = companyNamedatalist.get(position).company_name_id;
                    loadCompanyBranchData(companyId);
                }
            }
        });


    }

    private void loadCompanyBranchData(String companyId) {
        companyBranchNamedatalist = masterDataRepo.getMasterCompanyBranchData(companyId);

        if (companyBranchNamedatalist.isEmpty()) {
            addCompanyBranchBtn.setVisibility(View.VISIBLE);
        } else {
            List<String> branchname = masterDataRepo.getCompanyBranchName(companyId);
            companyBranchAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, branchname);
            branchnameSpineer.setAdapter(companyBranchAdapter);
            companyBranchAdapter.notifyDataSetChanged();

            branchnameSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String branchStatus = companyBranchNamedatalist.get(position).company_branch_name;
                    if (branchStatus.equalsIgnoreCase("Other")) {
                        addCompanyBranchBtn.setVisibility(View.VISIBLE);
                    } else {
                        addCompanyBranchBtn.setVisibility(View.GONE);
                    }

                }
            });
        }
    }

    private void loadBankData() {
        bankDataList = bankRepo.getAllBankData();

        bankAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, bankRepo.getBankName());
        bankCompanyNameSpinner.setAdapter(bankAdapter);
        bankAdapter.notifyDataSetChanged();

        bankCompanyNameSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bankCode = bankDataList.get(position).bank_code;
                loadBankBranchdata(bankCode);
            }
        });


    }

    private void loadBankBranchdata(String bankCode) {
        bankBranchDataList = bankBranchRepo.getAllBankBranchData(bankCode);


        branchAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, bankBranchRepo.getBranchName(bankCode));
        branchnameSpineer.setAdapter(branchAdapter);
        branchAdapter.notifyDataSetChanged();

        branchnameSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void getAllString(){
        meetingConducted = meetingConductedEt.getText().toString().trim();
        lastMeetingDate = lastMeetingDateET.getText().toString().trim();
        shgCutoffDate = shgCutoffDateEt.getText().toString().trim();

        cashInHand = cashInHandET.getText().toString().trim();
        cashInBank = cashInBankET.getText().toString().trim();

        savingWithVo = savingVoET.getText().toString().trim();
        savingWithClf = savingClfET.getText().toString().trim();
        shareCapitalVo = capitalVoET.getText().toString().trim();
        shareCapitalClf = capitalClfET.getText().toString().trim();

        grantSrlm = grantSrlmET.getText().toString().trim();
        rfRecivedSrlm = rfRecivedSrlmET.getText().toString().trim();
        rfReturnedSrlm = rfReutrnedSrlmET.getText().toString().trim();
        cifRecivedSrlm = cifRecivedSrlmET.getText().toString().trim();
        cifReturnedSrlm = cifReturnedSlrmET.getText().toString().trim();
        grantOtherResource = grantOtherET.getText().toString().trim();
        vrfGrantNrlm = vrfGrantNrlmET.getText().toString().trim();
        otherReceipt = otherReciptET.getText().toString().trim();


        closedLoanBank = closedLoanFromBankET.getText().toString().trim();
        amountBank = closedAmountBankET.getText().toString().trim();
        closedLoanVo = closedLoanVoET.getText().toString().trim();
        amountVo = amountClosedVoET.getText().toString().trim();
        closedLoanClf = closedLoanClfET.getText().toString().trim();
        amountClf = closedAmountClfET.getText().toString().trim();
    }

    private void validateAllData() {
        if(meetingConducted.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Meeting conducted till Date.. " , Toast.LENGTH_SHORT).show();
        }else {
            int getDate = Integer.parseInt(meetingConducted);
            if (getDate > count){
                meetingCountDialog();
            }else {
                vali();
            }
        }

    }

    private void vali() {
        if(lastMeetingDate.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Select last Meeting Date" , Toast.LENGTH_SHORT).show();
        }else if(shgCutoffDate.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Select Cut-Off Meeting Date" , Toast.LENGTH_SHORT).show();
        }else if(cashInHand.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Cash in Hand" , Toast.LENGTH_SHORT).show();
        }else if(cashInBank.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Cash in Bank" , Toast.LENGTH_SHORT).show();
        }else if(fdId.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Select fixed deposit investment" , Toast.LENGTH_SHORT).show();
        }else if(savingWithVo.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter saving with VO" , Toast.LENGTH_SHORT).show();
        }else if(shareCapitalVo.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter share capital with Vo" , Toast.LENGTH_SHORT).show();
        }else if(savingWithClf.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter saving with CLF" , Toast.LENGTH_SHORT).show();
        }else if(shareCapitalClf.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter share capital with CLF" , Toast.LENGTH_SHORT).show();
        }else if(grantSrlm.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Startup Grant from SRLM" , Toast.LENGTH_SHORT).show();
        }else if(rfRecivedSrlm.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter RF recevied from SRLM" , Toast.LENGTH_SHORT).show();
        }else if(rfReturnedSrlm.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter RF returned to SRLM" , Toast.LENGTH_SHORT).show();
        }else if(cifRecivedSrlm.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter CIF recevied from SRLM" , Toast.LENGTH_SHORT).show();
        }else if(cifReturnedSrlm.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter CIF returned to SRLM" , Toast.LENGTH_SHORT).show();
        }else if(otherReceipt.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Other Receipts" , Toast.LENGTH_SHORT).show();
        }else if(grantOtherResource.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Grant from other Govt. Source" , Toast.LENGTH_SHORT).show();
        }else if(vrfGrantNrlm.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter VRF grant from NRLM" , Toast.LENGTH_SHORT).show();
        }else if(closedLoanBank.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Number of Colosed Loan from Bank" , Toast.LENGTH_SHORT).show();
        }else if(amountBank.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Amount of closed Loan from Bank" , Toast.LENGTH_SHORT).show();
        }else if(closedLoanVo.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Number of Colosed Loan from VO" , Toast.LENGTH_SHORT).show();
        }else if(amountVo.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Amount of closed Loan from Bank" , Toast.LENGTH_SHORT).show();
        }else if(closedLoanClf.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Number of Colosed Loan from CLF" , Toast.LENGTH_SHORT).show();
        }else if(amountClf.isEmpty()){
            Toast.makeText(ShgCutOffActivity.this, "Enter Amount of closed Loan from CLF" , Toast.LENGTH_SHORT).show();
        }else {
            saveDataInDb();
        }
    }

    private void saveDataInDb() {
        Toast.makeText(ShgCutOffActivity.this, "Data saved " + count, Toast.LENGTH_SHORT).show();
        ShgCutoffEntity shgCutoffEntity =new ShgCutoffEntity();
        shgCutoffEntity.shg_code=shgCode;
        shgCutoffEntity.cutoff_id="";
        shgCutoffEntity.cutoff_sync_status="0";
        shgCutoffEntity.meeting_conducted=meetingConducted;
        shgCutoffEntity.last_meeting_date=lastMeetingDate;
        shgCutoffEntity.shg_cutoff_data=shgCutoffDate;
        shgCutoffEntity.cash_in_hand=cashInHand;
        shgCutoffEntity.cash_in_bank=cashInBank;
        shgCutoffEntity.fixed_deposit=fdId;//sed id for yes or no
        shgCutoffEntity.saving_with_vo=savingWithVo;
        shgCutoffEntity.saving_with_clf=savingWithClf;
        shgCutoffEntity.share_capital_with_vo=shareCapitalVo;
        shgCutoffEntity.share_capital_with_clf=shareCapitalClf;
        shgCutoffEntity.startup_grant_from_srlm=grantSrlm;
        shgCutoffEntity.rf_received_from_srlm=rfRecivedSrlm;
        shgCutoffEntity.cif_received_from_srlm=cifRecivedSrlm;
        shgCutoffEntity.rf_returned_to_srlm=rfReturnedSrlm;
        shgCutoffEntity.cif_returned_to_srlm=cifReturnedSrlm;
        shgCutoffEntity.other_receipts=otherReceipt;
        shgCutoffEntity.grant_from_govt_source=grantOtherResource;
        shgCutoffEntity.vrf_grant_from_nrlm=vrfGrantNrlm;
        shgCutoffEntity.closed_loan_from_bank=closedLoanBank;
        shgCutoffEntity.amount_for_closed_loan_bank=amountBank;
        shgCutoffEntity.closed_loan_from_vo=closedLoanVo;
        shgCutoffEntity.amount_for_closed_loan_vo=amountVo;
        shgCutoffEntity.closed_loan_from_clf=closedLoanClf;
        shgCutoffEntity.amount_for_closed_loan_clf=amountClf;
        shgCutOffRepo.insertAllCutoffData(shgCutoffEntity);

        formSubmitStatus ="ok";
        customProgressDialog.showProgress(AppConstant.DIALOG_MSG,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                customProgressDialog.hideProgress();
                saveCutoffBtn.setVisibility(View.GONE);
                updateCutoffBtn.setVisibility(View.VISIBLE);
            }
        },2000);
    }

    public void meetingCountDialog() {
        // Toast.makeText(ShgCutOffActivity.this, "meeting not more then:- " + count, Toast.LENGTH_SHORT).show();
        TextView dialogTv, dialogCheckFieldTv;
        MaterialButton closeBtn, okBtn;
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(ShgCutOffActivity.this);
        View customLayout = getLayoutInflater().inflate(R.layout.common_custom_right_wrong_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog dialog = materialAlertDialogBuilder.show();

        dialogTv = customLayout.findViewById(R.id.dialogTv);
        dialogCheckFieldTv = customLayout.findViewById(R.id.dialogCheckFieldTv);
        closeBtn = customLayout.findViewById(R.id.closeBtn);
        okBtn = customLayout.findViewById(R.id.okBtn);

        dialogTv.setText("Meeting till date should be less then or equal to: " + count);
        dialogCheckFieldTv.setText("Please check the field: " + "Meeting conducted Till Date");

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingConductedEt.setText(null);
                dialog.dismiss();
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingConductedEt.setText(null);
                dialog.dismiss();
            }
        });

    }

    private void setBtnVisibility() {
        if(shgCutoffDataList.isEmpty()){
            saveCutoffBtn.setVisibility(View.VISIBLE);
            updateCutoffBtn.setVisibility(View.GONE);
        }else {
            saveCutoffBtn.setVisibility(View.GONE);
            updateCutoffBtn.setVisibility(View.VISIBLE);
            showLocalData();
        }
    }

    private void showLocalData() {
        ShgCutoffEntity shgCutoffEntity =shgCutoffDataList.get(0);
        meetingConductedEt.setText(shgCutoffEntity.meeting_conducted);
        lastMeetingDateET.setText(shgCutoffEntity.last_meeting_date);
        shgCutoffDateEt.setText(shgCutoffEntity.shg_cutoff_data);

        cashInHandET.setText(shgCutoffEntity.cash_in_hand);
        cashInBankET.setText(shgCutoffEntity.cash_in_bank);
        fdSpinner.setText(shgCutoffEntity.fixed_deposit);
        fdId=shgCutoffEntity.fixed_deposit;

        savingVoET.setText(shgCutoffEntity.saving_with_vo);
        savingClfET.setText(shgCutoffEntity.saving_with_clf);
        capitalVoET.setText(shgCutoffEntity.share_capital_with_vo);
        capitalClfET.setText(shgCutoffEntity.share_capital_with_clf);


        grantSrlmET.setText(shgCutoffEntity.startup_grant_from_srlm);
        rfRecivedSrlmET.setText(shgCutoffEntity.rf_received_from_srlm);
        rfReutrnedSrlmET.setText(shgCutoffEntity.rf_returned_to_srlm);
        cifRecivedSrlmET.setText(shgCutoffEntity.cif_received_from_srlm);
        cifReturnedSlrmET.setText(shgCutoffEntity.cif_returned_to_srlm);
        grantOtherET.setText(shgCutoffEntity.grant_from_govt_source);
        vrfGrantNrlmET.setText(shgCutoffEntity.vrf_grant_from_nrlm);
        otherReciptET.setText(shgCutoffEntity.other_receipts);

        closedLoanFromBankET.setText(shgCutoffEntity.closed_loan_from_bank);
        closedAmountBankET.setText(shgCutoffEntity.amount_for_closed_loan_bank);
        amountClosedVoET.setText(shgCutoffEntity.closed_loan_from_vo);
        amountClosedVoET.setText(shgCutoffEntity.amount_for_closed_loan_vo);
        closedLoanClfET.setText(shgCutoffEntity.closed_loan_from_clf);
        closedAmountClfET.setText(shgCutoffEntity.closed_loan_from_clf);

        formSubmitStatus ="ok";


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getMeetingConductedCount() {
        String formationdate = shgDataRepo.getFormationDate(shgCode);
        appUtils.showLog("Formation Date:- " + formationdate, ShgCutOffActivity.class);
        //28-05-2015
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse("2021-01-01");
            long starDateTime = d.getTime();
            Date d2 = sdf.parse("2021-04-01");
            long endDateTime = d2.getTime();

            int i = monthsBetweenDates(d, d2);
            appUtils.showLog("No Of Months:- " + i, ShgCutOffActivity.class);

        } catch (ParseException e) {
            e.printStackTrace();

        }

       /* Period diff = Period.between(
                LocalDate.parse("2016-08-31").withDayOfMonth(1),
                LocalDate.parse("2016-11-30").withDayOfMonth(1));
        System.out.println(diff); //P3M*/

        /*long monthsBetween = ChronoUnit.MONTHS.between(
                LocalDate.parse("2016-08-31").withDayOfMonth(1),
                LocalDate.parse("2016-11-30").withDayOfMonth(1));*/


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int monthsBetweenDates(Date startDate, Date endDate) {

       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            long weeksInYear = ChronoUnit.WEEKS.between(startDate, endDate);
        }*/

        DateTime dateTime1 = new DateTime(startDate);
        DateTime dateTime2 = new DateTime(endDate);

        int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
        appUtils.showLog("No Of Weeks:- " + weeks, ShgCutOffActivity.class);

        int days = Days.daysBetween(dateTime1, dateTime2).getDays();
        if (days >= 15) {

        }

        appUtils.showLog("No Of days:- " + days, ShgCutOffActivity.class);


        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        int monthsBetween = 0;
        int dateDiff = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);

        if (dateDiff < 0) {
            int borrrow = end.getActualMaximum(Calendar.DAY_OF_MONTH);
            dateDiff = (end.get(Calendar.DAY_OF_MONTH) + borrrow) - start.get(Calendar.DAY_OF_MONTH);
            monthsBetween--;

            if (dateDiff > 0) {
                monthsBetween++;
            }
        } else {
            monthsBetween++;
        }
        monthsBetween += end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        monthsBetween += (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return monthsBetween;
    }

    private void setAllSpinnerListner() {

        fdSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*********here we get id not name just for testing we get name *******/
                fdClickStatus = yesNoDataList.get(position).fixed_deposit_inv_name;
                fdId = yesNoDataList.get(position).fixed_deposit_inv_value;
                if (fdClickStatus.equalsIgnoreCase("Yes")) {
                    fdCardview.setVisibility(View.VISIBLE);
                } else if (fdClickStatus.equalsIgnoreCase("No")) {
                    fdCardview.setVisibility(View.GONE);
                    clearFocus(3);
                }

            }
        });


        bankCompanySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*********here we get id not name just for testing we get name *******/
               /* InputMethodManager inputMethodManager = (InputMethodManager) getApplication().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);*/
                InputMethodManager in = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                bnkCmpnyId = bankCompanyDataList.get(position).bank_company_name;
                if (bnkCmpnyId.equalsIgnoreCase("Bank")) {
                    clearFocus(4);

                } else if (bnkCmpnyId.equalsIgnoreCase("Company")) {

                    clearFocus(5);
                }
            }
        });
    }

    private void loadMasterData() {
        yesNoDataList = masterDataRepo.getMasterFixedDepositData();
        yesNoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getFdName());
        fdSpinner.setAdapter(yesNoAdapter);
        yesNoAdapter.notifyDataSetChanged();

        bankCompanyDataList = masterDataRepo.getMasterBankCompanyData();
        bankCompanyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getMasterBankCompanyName()); //Binding the Gps data with Gp spinner
        bankCompanySpinner.setAdapter(bankCompanyAdapter);
        bankCompanyAdapter.notifyDataSetChanged();


    }

    private void getAllInstance() {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ShgCutOffActivity.this);
        allDataList = AllDataList.getInstance(ShgCutOffActivity.this);
        customProgressDialog = CustomProgressDialog.newInstance(ShgCutOffActivity.this);
        appUtils = AppUtils.getInstance();
        dateFactory = DateFactory.getInstance(ShgCutOffActivity.this);


        bankRepo = new BankRepo(getApplication());
        bankBranchRepo = new BankBranchRepo(getApplication());
        shgDataRepo = new ShgDataRepo(getApplication());
        shgmemberRepo = new ShgmemberRepo(getApplication());
        masterDataRepo = new MasterDataRepo(getApplication());
        shgSettingSavingFromMemberRepo = new ShgSettingSavingFromMemberRepo(getApplication());
        shgCutOffRepo = new ShgCutOffRepo(getApplication());


        bankCompanyDataList = new ArrayList<>();
        yesNoDataList = new ArrayList<>();
        bankDataList = new ArrayList<>();
        bankBranchDataList = new ArrayList<>();
        companyNamedatalist = new ArrayList<>();
        companyBranchNamedatalist = new ArrayList<>();
    }
}