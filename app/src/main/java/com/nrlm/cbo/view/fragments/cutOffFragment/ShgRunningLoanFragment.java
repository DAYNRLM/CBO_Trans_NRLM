package com.nrlm.cbo.view.fragments.cutOffFragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.Utils.DateFactory;
import com.nrlm.cbo.Utils.MyDatePicker;
import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffLoanTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffLoanSourceEntity;
import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.ShgLoansEntity;
import com.nrlm.cbo.database.room.repositories.BankBranchRepo;
import com.nrlm.cbo.database.room.repositories.BankRepo;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgCutOffRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgLoanRepo;
import com.nrlm.cbo.view.adapters.shgCutoffAdapters.ShgCutoffRunningInsuranceAdapter;
import com.nrlm.cbo.view.adapters.shgCutoffAdapters.ShgCutoffRunningLoanAdapter;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.nrlm.cbo.R;
import com.nrlm.cbo.view.adapters.RunningLoanAdapter;
import com.nrlm.cbo.database.AppDataBase;
import com.nrlm.cbo.database.room.entity.AddShgLoan;
import com.nrlm.cbo.view.interfaces.OnClickListnerForRunningInsurance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShgRunningLoanFragment extends BaseFragment {


    /******all material btn***********/
    @BindView(R.id.runningLoanSubmitBtn)
    MaterialButton runningLoanSubmitBtn;

    @BindView(R.id.runningLoanCloseBtn)
    MaterialButton runningLoanCloseBtn;

    @BindView(R.id.addMoreBtn)
    MaterialButton addMoreBtn;

    /*************all layout**********/
    @BindView(R.id.bankDetailLL)
    LinearLayout bankDetailLL;

    @BindView(R.id.bankDetailCardView)
    MaterialCardView bankDetailCardView;

    @BindView(R.id.recyclerRL)
    RelativeLayout recyclerRL;

    @BindView(R.id.loanFormRL)
    RelativeLayout loanFormRL;

    @BindView(R.id.formScrolView)
    ScrollView formScrolView;

    @BindView(R.id.runningLoanRV)
    RecyclerView runningLoanRV;

    /***********all TIL***************/
    @BindView(R.id.loanFromTIL)
    TextInputLayout loanFromTIL;

    @BindView(R.id.loanNumberTIL)
    TextInputLayout loanNumberTIL;

    @BindView(R.id.bankNameTIL)
    TextInputLayout bankNameTIL;

    @BindView(R.id.loanTypeTIL)
    TextInputLayout loanTypeTIL;

    @BindView(R.id.loanFromNameTIL)
    TextInputLayout loanFromNameTIL;

    @BindView(R.id.loanDisbDateTTL)
    TextInputLayout loanDisbDateTTL;

    @BindView(R.id.loanDisbAmountTTL)
    TextInputLayout loanDisbAmountTTL;

    @BindView(R.id.branchNameTIL)
    TextInputLayout branchNameTIL;

    @BindView(R.id.ifscCodeTIL)
    TextInputLayout ifscCodeTIL;

    @BindView(R.id.loanSencationDateTIL)
    TextInputLayout loanSencationDateTIL;

    @BindView(R.id.loanSancationAmountTIL)
    TextInputLayout loanSancationAmountTIL;

    @BindView(R.id.rateOfIntresrTTL)
    TextInputLayout rateOfIntresrTTL;

    @BindView(R.id.noOfInsalTTL)
    TextInputLayout noOfInsalTTL;

    @BindView(R.id.instalmentAmpuntTTL)
    TextInputLayout instalmentAmpuntTTL;

    @BindView(R.id.installmentRepPaidTTL)
    TextInputLayout installmentRepPaidTTL;

    @BindView(R.id.principalPaidTTL)
    TextInputLayout principalPaidTTL;

    @BindView(R.id.principalOverdueTTL)
    TextInputLayout principalOverdueTTL;

    @BindView(R.id.intrestOverdueTTL)
    TextInputLayout intrestOverdueTTL;

    @BindView(R.id.intrestPaidTTL)
    TextInputLayout intrestPaidTTL;

    /*********all spinner************/
    @BindView(R.id.loanFromSpinner)
    AutoCompleteTextView loanFromSpinner;

    @BindView(R.id.bankNameSpinner)
    AutoCompleteTextView bankNameSpinner;

    @BindView(R.id.branchNameSpinner)
    AutoCompleteTextView branchNameSpinner;

    @BindView(R.id.loanTypeSpinner)
    AutoCompleteTextView loanTypeSpinner;

    /********all edit text************/
    @BindView(R.id.loanNumberEt)
    TextInputEditText loanNumberEt;

    @BindView(R.id.ifscCodeEt)
    TextInputEditText ifscCodeEt;

    @BindView(R.id.loanSencationDateEt)
    TextInputEditText loanSencationDateEt;

    @BindView(R.id.loanDisbDateET)
    TextInputEditText loanDisbDateET;

    @BindView(R.id.loanDisbAmountET)
    TextInputEditText loanDisbAmountET;

    @BindView(R.id.loanSancationAmountEt)
    TextInputEditText loanSancationAmountEt;

    @BindView(R.id.rateOfIntresrEt)
    TextInputEditText rateOfIntresrEt;

    @BindView(R.id.noOfInstalmentET)
    TextInputEditText noOfInstalmentET;

    @BindView(R.id.loanFromNameEt)
    TextInputEditText loanFromNameEt;

    @BindView(R.id.instalmentAmpuntET)
    TextInputEditText instalmentAmpuntET;

    @BindView(R.id.installmentRepPaidET)
    TextInputEditText installmentRepPaidET;

    @BindView(R.id.principalPaidtET)
    TextInputEditText principalPaidtET;

    @BindView(R.id.principalOverdueET)
    TextInputEditText principalOverdueET;

    @BindView(R.id.intrestOverdueET)
    TextInputEditText intrestOverdueET;

    @BindView(R.id.intrestPaidET)
    TextInputEditText intrestPaidET;

    /**********all global variables************/
    String shgCode = "";
    String shgName = "";
    String loanFromId = "";
    String loanTypeId = "";
    String loanNumber = "";
    String bankCode = "";
    String branchCode = "";
    String ifscCode = "";
    String loanSanctionDate = "";
    String loanSanctionAmount = "";
    String loanDisbDate = "";
    String loanDisbAmount = "";
    String roiYearly = "";
    String noOfInstalment = "";
    String instalmentAmount = "";
    String noOfInstalmentRepaid = "";
    String principalPaid = "";
    String principalOverdue = "";
    String intrestOverdue = "";
    String intrestPaid = "";

    /****all utils classes and repository*****/
    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    CustomProgressDialog customProgressDialog;
    AppUtils appUtils;
    DateFactory dateFactory;

    ShgDataRepo shgDataRepo;
    ShgCutOffRepo shgCutOffRepo;
    ShgLoanRepo shgLoanRepo;
    MasterDataRepo masterDataRepo;
    BankRepo bankRepo;
    BankBranchRepo bankBranchRepo;


    /*************all list***************/
    List<ShgLoansEntity> shgLoansEntities;
    List<MasterCutoffLoanTypeEntity> loanTypeDataItem;
    List<MasterCuttOffLoanSourceEntity> loanSourceDataItem;
    List<BankEntity> bankDataItemList;
    List<BankBranchEntity> bankBranchDataItemList;

    /***********all adapter**************/
    ArrayAdapter<String> loanSourceAdapter;
    ArrayAdapter<String> loanTypeAdapter;
    ArrayAdapter<String> bankNameAdapter;
    ArrayAdapter<String> branchNameAdapter;

    OnClickListnerForRunningInsurance onClickListnerForRunningInsurance;
    ShgCutoffRunningLoanAdapter shgCutoffRunningLoanAdapter;


    public static ShgRunningLoanFragment newInstance() {
        ShgRunningLoanFragment shgFragment = new ShgRunningLoanFragment();
        return shgFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_running_loan_layout;
    }

    @Override
    public void onFragmentReady() {
        getAllInstance();
        shgCode =appSharedPreferences.getShgCodeForVerification();

        shgLoansEntities =shgLoanRepo.getAllInsuranceData(shgCode, AppConstant.CUT_OFF_RUNNING_LOAN_TAG);

        onClickListnerForRunningInsurance =new OnClickListnerForRunningInsurance() {
            @Override
            public void notifyDate(String Type, ShgCutOffRunningInsuranceEntity insuranceObject) {
                switch (Type){
                    case "edit":

                        break;
                    case "delete":
                        break;
                }

            }
        };

        loadMasterData();

        if(shgLoansEntities.isEmpty()){
            recyclerRL.setVisibility(View.GONE);
            loanFormRL.setVisibility(View.VISIBLE);
        }else {
            recyclerRL.setVisibility(View.VISIBLE);
            loanFormRL.setVisibility(View.GONE);
            showData();
        }
    }

    private void loadMasterData() {
        loanSourceDataItem =masterDataRepo.getMasterLoanSource();

        loanSourceAdapter =new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getloanSourceName());
        loanFromSpinner.setAdapter(loanSourceAdapter);
        loanSourceAdapter.notifyDataSetChanged();

        loanFromSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //here we get id for testing we get the name
                loanFromId =loanSourceDataItem.get(position).loan_source_name;
                if(loanFromId.equalsIgnoreCase("SHG")){
                    clearFocus(1);
                }else if(loanFromId.equalsIgnoreCase("VO")){
                    clearFocus(2);

                }else if(loanFromId.equalsIgnoreCase("CLF")){
                    clearFocus(3);

                }else if(loanFromId.equalsIgnoreCase("MFI")){
                    clearFocus(3);

                }else if(loanFromId.equalsIgnoreCase("Other")){
                    clearFocus(5);
                }
            }
        });

        bankDataItemList = bankRepo.getAllBankData();

        bankNameAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, bankRepo.getBankName());
        bankNameSpinner.setAdapter(bankNameAdapter);
        bankNameAdapter.notifyDataSetChanged();

        bankNameSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bankCode = bankDataItemList.get(position).bank_code;
                loadBankBranchdata(bankCode);
            }
        });



    }

    private void loadBankBranchdata(String bankCode) {
        bankBranchDataItemList = bankBranchRepo.getAllBankBranchData(bankCode);


        branchNameAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, bankBranchRepo.getBranchName(bankCode));
        branchNameSpinner.setAdapter(branchNameAdapter);
        branchNameAdapter.notifyDataSetChanged();

        branchNameSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                branchCode=bankBranchDataItemList.get(position).branch_code;
                ifscCode =bankBranchDataItemList.get(position).ifsc_code;
                ifscCodeEt.setText(ifscCode);
            }
        });
    }

    private void getAllInstance() {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(getActivity());
        allDataList = AllDataList.getInstance(getActivity());
        customProgressDialog = CustomProgressDialog.newInstance(getActivity());
        appUtils = AppUtils.getInstance();
        dateFactory = DateFactory.getInstance(getActivity());

        shgDataRepo =new ShgDataRepo(getActivity().getApplication());
        shgCutOffRepo =new ShgCutOffRepo(getActivity().getApplication());
        shgLoanRepo =new ShgLoanRepo(getActivity().getApplication());
        masterDataRepo =new MasterDataRepo(getActivity().getApplication());
        bankRepo =new BankRepo(getActivity().getApplication());
        bankBranchRepo =new BankBranchRepo(getActivity().getApplication());

    }

    public void clearFocus(int id){
        switch (id){
            case 1:
                loanTypeId="";
                loanTypeSpinner.setText(null);

                loanNumber="";
                loanNumberEt.setText(null);

                bankCode="";
                bankNameSpinner.setText(null);

                branchCode="";
                branchNameSpinner.setText(null);

                ifscCode ="";
                ifscCodeEt.setText(null);

                loanSanctionDate="";
                loanDisbDate="";
                loanSanctionAmount="";
                loanDisbAmount="";

                loanSencationDateEt.setText(null);
                loanDisbDateET.setText(null);
                loanSancationAmountEt.setText(null);
                loanDisbAmountET.setText(null);



                bankDetailCardView.setVisibility(View.VISIBLE);
                loanTypeTIL.setVisibility(View.VISIBLE);
                loanFromNameTIL.setVisibility(View.GONE);
                loadBankSelectionData();
                break;
            case 2:
                loanTypeId="";
                loanTypeSpinner.setText(null);

                loanNumber="";
                loanNumberEt.setText(null);

                bankCode="";
                bankNameSpinner.setText(null);

                branchCode="";
                branchNameSpinner.setText(null);

                ifscCode ="";
                ifscCodeEt.setText(null);

                loanSanctionDate="";
                loanDisbDate="";
                loanSanctionAmount="";
                loanDisbAmount="";

                loanSencationDateEt.setText(null);
                loanDisbDateET.setText(null);
                loanSancationAmountEt.setText(null);
                loanDisbAmountET.setText(null);


                bankDetailCardView.setVisibility(View.GONE);
                loanTypeTIL.setVisibility(View.GONE);
                loanFromNameTIL.setVisibility(View.VISIBLE);
                loanFromNameTIL.setHint("VO Name");

                break;
            case 3:
                loanTypeId="";
                loanTypeSpinner.setText(null);

                loanNumber="";
                loanNumberEt.setText(null);

                bankCode="";
                bankNameSpinner.setText(null);

                branchCode="";
                branchNameSpinner.setText(null);

                ifscCode ="";
                ifscCodeEt.setText(null);

                loanSanctionDate="";
                loanDisbDate="";
                loanSanctionAmount="";
                loanDisbAmount="";

                loanSencationDateEt.setText(null);
                loanDisbDateET.setText(null);
                loanSancationAmountEt.setText(null);
                loanDisbAmountET.setText(null);

                


                bankDetailCardView.setVisibility(View.GONE);
                loanTypeTIL.setVisibility(View.GONE);
                loanFromNameTIL.setVisibility(View.VISIBLE);
                loanFromNameTIL.setHint("CLF Name");
                break;
            case 4:
                loanTypeId="";
                loanTypeSpinner.setText(null);

                loanNumber="";
                loanNumberEt.setText(null);

                bankCode="";
                bankNameSpinner.setText(null);

                branchCode="";
                branchNameSpinner.setText(null);

                ifscCode ="";
                ifscCodeEt.setText(null);

                loanSanctionDate="";
                loanDisbDate="";
                loanSanctionAmount="";
                loanDisbAmount="";

                loanSencationDateEt.setText(null);
                loanDisbDateET.setText(null);
                loanSancationAmountEt.setText(null);
                loanDisbAmountET.setText(null);


                bankDetailCardView.setVisibility(View.GONE);
                loanTypeTIL.setVisibility(View.GONE);
                loanFromNameTIL.setVisibility(View.VISIBLE);
                loanFromNameTIL.setHint("MFI Name");
                break;
            case 5:
                loanTypeId="";
                loanTypeSpinner.setText(null);

                loanNumber="";
                loanNumberEt.setText(null);

                bankCode="";
                bankNameSpinner.setText(null);

                branchCode="";
                branchNameSpinner.setText(null);

                ifscCode ="";
                ifscCodeEt.setText(null);

                loanSanctionDate="";
                loanDisbDate="";
                loanSanctionAmount="";
                loanDisbAmount="";

                loanSencationDateEt.setText(null);
                loanDisbDateET.setText(null);
                loanSancationAmountEt.setText(null);
                loanDisbAmountET.setText(null);


                bankDetailCardView.setVisibility(View.GONE);
                loanTypeTIL.setVisibility(View.GONE);
                loanFromNameTIL.setVisibility(View.VISIBLE);
                loanFromNameTIL.setHint("Other Source Name");
                break;
        }
    }

    private void loadBankSelectionData() {
        loanTypeDataItem =masterDataRepo.getMasterLoanType();

        loanTypeAdapter =new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getloanTypeName());
        loanTypeSpinner.setAdapter(loanTypeAdapter);
        loanTypeAdapter.notifyDataSetChanged();

        loanTypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loanTypeId=loanTypeDataItem.get(position).loan_type_id;
            }
        });



    }

    @OnClick(R.id.runningLoanSubmitBtn)
    public void addRunningLoan(){
        loanNumber= loanNumberEt.getText().toString().trim();
        loanSanctionAmount=loanSancationAmountEt.getText().toString().trim();
        loanSanctionDate=loanSencationDateEt.getText().toString().trim();
        loanDisbDate=loanDisbDateET.getText().toString().trim();
        loanDisbAmount=loanDisbAmountET.getText().toString().trim();

        roiYearly =rateOfIntresrEt.getText().toString().trim();
        noOfInstalment =noOfInstalmentET.getText().toString().trim();
        instalmentAmount =instalmentAmpuntET.getText().toString().trim();
        noOfInstalmentRepaid =installmentRepPaidET.getText().toString().trim();
        principalPaid =principalPaidtET.getText().toString().trim();
        principalOverdue =principalOverdueET.getText().toString().trim();
        intrestOverdue =intrestOverdueET.getText().toString().trim();
        intrestPaid =intrestPaidET.getText().toString().trim();


        if(loanFromId.equalsIgnoreCase("")){
            Toast.makeText(getContext(),"select Loan from first..",Toast.LENGTH_SHORT).show();
        }else if(loanFromId.equalsIgnoreCase("SHG")){
            if(loanTypeId.equalsIgnoreCase("")){
                Toast.makeText(getContext(),"select Loan type first..",Toast.LENGTH_SHORT).show();
            }else if(loanNumber.isEmpty()){
                Toast.makeText(getContext(),"Enter Valid loan number",Toast.LENGTH_SHORT).show();
            }else if(bankCode.equalsIgnoreCase("")){
                Toast.makeText(getContext(),"please select Bank",Toast.LENGTH_SHORT).show();
            }else if(branchCode.equalsIgnoreCase("")){
                Toast.makeText(getContext(),"please select Bank branch",Toast.LENGTH_SHORT).show();
            }else {
                checkCommonCondition();
            }

        }else {
            checkCommonCondition();

        }
    }

    private void checkCommonCondition() {
        if(loanNumber.isEmpty()){
            Toast.makeText(getContext(),"Enter Valid loan number",Toast.LENGTH_SHORT).show();
        }else if(loanSanctionDate.isEmpty()){
            Toast.makeText(getContext(),"Select loan Sanction Date",Toast.LENGTH_SHORT).show();
        }else if(loanSanctionAmount.isEmpty()){
            Toast.makeText(getContext(),"Enter loan Sanction Amount",Toast.LENGTH_SHORT).show();
        }else if(loanDisbDate.isEmpty()){
            Toast.makeText(getContext(),"Select loan Disburse Date",Toast.LENGTH_SHORT).show();
        }else if(loanDisbAmount.isEmpty()){
            Toast.makeText(getContext(),"Enter loan Disburse Amount",Toast.LENGTH_SHORT).show();
        }else if(roiYearly.isEmpty()){
            Toast.makeText(getContext(),"Enter ROI(Yearly in %)/Enter 0",Toast.LENGTH_SHORT).show();
        }else if(noOfInstalment.isEmpty()){
            Toast.makeText(getContext(),"Enter Number Of Instalment",Toast.LENGTH_SHORT).show();
        }else if(instalmentAmount.isEmpty()){
            Toast.makeText(getContext(),"Enter Instalment Amount",Toast.LENGTH_SHORT).show();
        }else if(noOfInstalmentRepaid.isEmpty()){
            Toast.makeText(getContext(),"Enter Number Of Instalment-Repaid",Toast.LENGTH_SHORT).show();
        }else if(principalPaid.isEmpty()){
            Toast.makeText(getContext(),"Enter Principal Paid amount",Toast.LENGTH_SHORT).show();
        }else if(principalOverdue.isEmpty()){
            Toast.makeText(getContext(),"Enter Principal Overdue",Toast.LENGTH_SHORT).show();
        }else if(intrestOverdue.isEmpty()){
            Toast.makeText(getContext(),"Enter Intrest Overdue",Toast.LENGTH_SHORT).show();
        }else if(intrestPaid.isEmpty()){
            Toast.makeText(getContext(),"Enter intrest-Paid",Toast.LENGTH_SHORT).show();
        }else {
            addLoanInLocalDB();
        }

    }

    private void addLoanInLocalDB() {
        Toast.makeText(getContext(),"Data Save Done",Toast.LENGTH_SHORT).show();
        ShgLoansEntity shgLoansEntity =new ShgLoansEntity();

        String getId ="MCRL"+appSharedPreferences.getRunningLoanId();

        //one filed missing loan from source name
        shgLoansEntity.shg_Code= shgCode;
        shgLoansEntity.shg_loan_id=getId;
        shgLoansEntity.loan_status_comming_from=AppConstant.CUT_OFF_RUNNING_LOAN_TAG;
        shgLoansEntity.loan_sync_status="0";

        shgLoansEntity.loan_from_code=loanFromId;
        shgLoansEntity.loan_type_code=loanTypeId;
        shgLoansEntity.loan_Number_code=loanNumber;
        shgLoansEntity.bank_name_code=bankCode;
        shgLoansEntity.branch_name_code=branchCode;
        shgLoansEntity.bank_ifsc_code=ifscCode;
        shgLoansEntity.loan_sanction_date=loanSanctionDate;
        shgLoansEntity.loan_sanction_amount=loanSanctionAmount;
        shgLoansEntity.loan_disburses_date=loanDisbDate;
        shgLoansEntity.loan_disburses_amount=loanDisbAmount;

        shgLoansEntity.loan_roi=roiYearly;
        shgLoansEntity.number_of_instalment=noOfInstalment;
        shgLoansEntity.instalment_amount=instalmentAmount;
        shgLoansEntity.number_of_instalment_repaid=noOfInstalmentRepaid;

        shgLoansEntity.principal_paid=principalPaid;
        shgLoansEntity.principal_overdue=principalOverdue;
        shgLoansEntity.intrest_overdue=intrestOverdue;
        shgLoansEntity.intrest_paid=intrestPaid;

        shgLoanRepo.insertAll(shgLoansEntity);


        int loanLocalId = Integer.parseInt(appSharedPreferences.getRunningInsuranceId());
        appSharedPreferences.setRunningLoanId(String.valueOf(loanLocalId+1));

        getActivity().finish();
        startActivity(getActivity().getIntent());

    }


    @OnClick(R.id.loanSencationDateEt)
    public void loanSencationDate() {
        String getTodatDate = dateFactory.getTodayDateYYMMDD();
        String shgFormationDate = dateFactory.changeFormateYYMMDD(shgDataRepo.getFormationDate(shgCode)); //dd-mm-yyyy
        DialogFragment dialogFragment = new MyDatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar cal = GregorianCalendar.getInstance();
                cal.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDateandTime = sdf.format(cal.getTime());
                loanSencationDateEt.setText(currentDateandTime);
            }
        }, dateFactory.getCalendarTime(shgFormationDate), dateFactory.getCalendarTime(getTodatDate), getContext());
        dialogFragment.show(getFragmentManager(), "Date");
    }

    @OnClick(R.id.loanDisbDateET)
    public void loanDisbDate(){
        loanSanctionDate =loanSencationDateEt.getText().toString().trim();
        String getTodatDate =dateFactory.getTodayDateYYMMDD();
        if(loanSanctionDate.isEmpty()){
            Toast.makeText(getContext(),"First select loan Sanction Date",Toast.LENGTH_SHORT).show();
        }else {
            DialogFragment dialogFragment = new MyDatePicker(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.set( year, monthOfYear, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDateandTime = sdf.format( cal.getTime());
                    loanDisbDateET.setText(currentDateandTime);
                }
            },dateFactory.getCalendarTime(loanSanctionDate),dateFactory.getCalendarTime(getTodatDate),getContext());
            dialogFragment.show(getFragmentManager(), "Date");
        }
    }


    @OnClick(R.id.addMoreBtn)
    public void addMoreLoan(){
        recyclerRL.setVisibility(View.GONE);
        loanFormRL.setVisibility(View.VISIBLE);
    }

    private void showData() {
        shgCutoffRunningLoanAdapter = new ShgCutoffRunningLoanAdapter(shgLoansEntities, getContext(),getActivity().getApplication(),onClickListnerForRunningInsurance);
        runningLoanRV.setLayoutManager(new LinearLayoutManager(getContext()));
        runningLoanRV.setAdapter(shgCutoffRunningLoanAdapter);
        shgCutoffRunningLoanAdapter.notifyDataSetChanged();
    }
    /*@OnClick(R.id.addLoanBtn)
    void lonFormClick() {

     //   AppUtils.getInstance().replaceFragment(getFragmentManager(), ShgRunningLoanForm.newInstance(), ShgRunningLoanForm.class.getName(), true, R.id.cutoffFramLayout);
    }*/
}
