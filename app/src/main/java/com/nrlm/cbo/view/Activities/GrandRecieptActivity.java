package com.nrlm.cbo.view.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.Utils.AppConditionalConstant;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.PreferenceFactory;
import com.nrlm.cbo.Utils.PrefrenceManager;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.AddedReceiptEntityDataModel;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgSavingInfoEntityDataModel;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptLoanEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptSubTypeLoanSourceEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.view.adapters.receiptAdapters.AddedReceiptAdapter;
import com.nrlm.cbo.view.adapters.spinnerAdapter.ReceiptSubTypeSpinnerAdapter;
import com.nrlm.cbo.view.adapters.spinnerAdapter.ReceiptSubtToSubTypeSpinnerAdapter;
import com.nrlm.cbo.view.adapters.spinnerAdapter.ReceiptTypeSpinnerAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GrandRecieptActivity extends AppCompatActivity {

    @BindView(R.id.toolbarTV)
    TextView toolbarTV;

    @BindView(R.id.shg_nameTV)
    TextView shg_nameTV;

    @BindView(R.id.shg_codeTV)
    TextView shg_codeTV;

    @BindView(R.id.meeting_dateTV)
    TextView meeting_dateTV;

    @BindView(R.id.meeting_noTVTV)
    TextView meeting_noTVTV;

    @BindView(R.id.cash_book_noTV)
    TextView cash_book_noTV;

    @BindView(R.id.cash_book_page_noTV)
    TextView cash_book_page_noTV;
    /*------------------------------------------------------*/

    @BindView(R.id.reciept_type_MBS)
    Spinner reciept_type_MBS;

    @BindView(R.id.reciept_sub_type_MBS)
    Spinner reciept_sub_type_MBS;

    @BindView(R.id.reciept_sub_to_sub_type_MBS)
    Spinner reciept_sub_to_sub_type_MBS;

    /*-----------------------------Show selected view  cardview labels-----------------------------------------*/

    @BindView(R.id.show_seleted_viewCV)
    CardView show_seleted_viewCV;

    @BindView(R.id.added_recieptRV)
    RecyclerView added_recieptRV;


    /*---------------------------Add new loan cardview labels------------------------------------------------------*/

    @BindView(R.id.add_new_loanCV)
    CardView add_new_loanCV;

    @BindView(R.id.loan_typeSpinner)
    Spinner loan_typeSpinner;

    @BindView(R.id.bank_nameSpinner)
    Spinner bank_nameSpinner;

    @BindView(R.id.branch_nameSpinner)
    Spinner branch_nameSpinner;

    @BindView(R.id.ifsc_codeTV)
    TextView ifsc_codeTV;

    @BindView(R.id.loan_numberTIET)
    TextInputEditText loan_numberTIET;

    @BindView(R.id.loan_sncsn_amountTIET)
    TextInputEditText loan_sncsn_amountTIET;

    @BindView(R.id.loan_sanction_dateLL)
    LinearLayout loan_sanction_dateLL;

    @BindView(R.id.loan_sncsn_dateTV)
    TextView loan_sncsn_dateTV;

    @BindView(R.id.rate_of_interestTIET)
    TextInputEditText rate_of_interestTIET;

    @BindView(R.id.loan_repay_byTIET)
    TextInputEditText loan_repay_byTIET;

    @BindView(R.id.no_of_installmentTIET)
    TextInputEditText no_of_installmentTIET;

    @BindView(R.id.disbursed_dateLL)
    LinearLayout disbursed_dateLL;

    @BindView(R.id.disbursed_dateTV)
    TextView disbursed_dateTV;

    @BindView(R.id.loan_repay_start_dateLL)
    LinearLayout loan_repay_start_dateLL;

    @BindView(R.id.loan_repay_start_dateTV)
    TextView loan_repay_start_dateTV;

    @BindView(R.id.emiTIET)
    TextInputEditText emiTIET;

    @BindView(R.id.cashbookpagenoTV)
    TextView cashbookpagenoTV;

    @BindView(R.id.transactionModeSpinner)
    Spinner transactionModeSpinner;

    @BindView(R.id.cash_credit_limitTIET)
    TextInputEditText cash_credit_limitTIET;

    @BindView(R.id.add_more_receiptsCV)
    CardView add_more_receiptsCV;


    private Context context;
    private String selectedShgCode, selectedEntityCode, selectedRecieptTypeID, receiptSubTypeId, receiptSubToSubTypeId;
    private ShgSavingInfoEntityDataModel shgSavingInfoEntityDataModel;
    private ShgDataRepo shgDataRepo;
    private MasterDataRepo masterDataRepo;
    List<MasterShgReceiptEntity> masterShgReceiptEntityList = null;
    private List<MasterShgReceiptSubTypeLoanSourceEntity> masterReceiptSubTypeList = new ArrayList<MasterShgReceiptSubTypeLoanSourceEntity>();
    private List<MasterShgReceiptLoanEntity> masterReceiptSubToSubTypesList = new ArrayList<MasterShgReceiptLoanEntity>();


    /*======================= cases strings ====================*/
    private Dialog dialog;
    private String receivedDate = "";
    private String date="",transactionMode="",added_reciept_spinner1_Value="",added_reciept_spinner2_Value="",added_reciept_spinner3_Value=""
                    ,text_data_label1_Value="",text_data_label2_Value="",edit_textTIET1_value="",edit_textTIET2_value="",
            edit_textTIET3_value="", edit_textTIET4_value="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grand_reciept);
        ButterKnife.bind(this);

        context = this;
        shgDataRepo = new ShgDataRepo(getApplication());
        masterDataRepo = new MasterDataRepo(getApplication());
        selectedShgCode = PreferenceFactory.getInstance().getSharedPrefrencesData(PrefrenceManager.getPrefShgCode(), context);
        selectedEntityCode = PreferenceFactory.getInstance().getSharedPrefrencesData(PrefrenceManager.getPrefEntityCode(), context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                    toolbarTV.setVisibility(View.GONE);
                } else if (isShow) {
                    isShow = false;
                    toolbarTV.setVisibility(View.VISIBLE);

                }
            }
        });


        try {
            shgSavingInfoEntityDataModel = shgDataRepo.getShgSavingInfo(selectedEntityCode, selectedShgCode);
            masterShgReceiptEntityList = masterDataRepo.getAllReceiptType();

            if (shgSavingInfoEntityDataModel != null) {
                AppUtils.getInstance().showLog("GrandRecieptActivityInfo=" + shgSavingInfoEntityDataModel.getShgName() +
                        "" + shgSavingInfoEntityDataModel.getShgCode() + shgSavingInfoEntityDataModel.getLastMeetingDate()
                        + "Mno=" + shgSavingInfoEntityDataModel.getLastMeetingNo(), MemberSavingActivity.class);
                shg_nameTV.setText(shgSavingInfoEntityDataModel.getShgName());
                shg_codeTV.setText(shgSavingInfoEntityDataModel.getShgCode());
                meeting_dateTV.setText(shgSavingInfoEntityDataModel.getLastMeetingDate());
                meeting_noTVTV.setText(shgSavingInfoEntityDataModel.getLastMeetingNo());
                cash_book_noTV.setText("10");
                cash_book_page_noTV.setText("10");
            }
            List<AddedReceiptEntityDataModel> xyz = new ArrayList<>();
            AddedReceiptEntityDataModel addedReceiptEntityDataModel = new AddedReceiptEntityDataModel();
            addedReceiptEntityDataModel.setCashBookPageNo("25");
            AddedReceiptEntityDataModel addedReceiptEntityDataModel1 = new AddedReceiptEntityDataModel();
            addedReceiptEntityDataModel.setCashBookPageNo("25");
            xyz.add(addedReceiptEntityDataModel);
            xyz.add(addedReceiptEntityDataModel1);
            selectedRecieptTypeID = "3";

            AddedReceiptAdapter addedReceiptAdapter = new AddedReceiptAdapter(context, selectedRecieptTypeID, xyz);
            added_recieptRV.setLayoutManager(new LinearLayoutManager(context));
            added_recieptRV.setAdapter(addedReceiptAdapter);


            if (masterShgReceiptEntityList != null && masterShgReceiptEntityList.size() > 0) {

                MasterShgReceiptEntity masterShgReceiptEntity = new MasterShgReceiptEntity();
                masterShgReceiptEntity.id = 0;
                masterShgReceiptEntity.receipt_description = AppConstant.SELECT_RECEIPT_TYPE;
                masterShgReceiptEntity.receipt_id = "0";
                masterShgReceiptEntityList.add(0, masterShgReceiptEntity);

                ReceiptTypeSpinnerAdapter receiptTypeSpinnerAdapter = new ReceiptTypeSpinnerAdapter(context, masterShgReceiptEntityList);
                reciept_type_MBS.setAdapter(receiptTypeSpinnerAdapter);
            }

            reciept_type_MBS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    MasterShgReceiptEntity masterShgReceiptEntity = (MasterShgReceiptEntity) parent
                            .getItemAtPosition(position);
                    AppUtils.getInstance().showLog("masterShgReceiptEntity" + masterShgReceiptEntity.receipt_description
                            , GrandRecieptActivity.class);
                    masterReceiptSubTypeList.clear();
                    masterReceiptSubToSubTypesList.clear();
                    reciept_sub_type_MBS.setVisibility(View.GONE);
                    reciept_sub_to_sub_type_MBS.setVisibility(View.GONE);

                    selectedRecieptTypeID = masterShgReceiptEntity.receipt_id.trim();

                    Toast.makeText(context, "Selected item="
                            + masterShgReceiptEntityList.get(position).receipt_description, Toast.LENGTH_LONG).show();


                    try {
                        masterReceiptSubTypeList = masterDataRepo.getReceiptSubTypes(selectedRecieptTypeID);
                        AppUtils.getInstance().showLog("masterReceiptSubTypeListSize" + masterReceiptSubTypeList.size()
                                , GrandRecieptActivity.class);
                        if (masterReceiptSubTypeList != null && masterReceiptSubTypeList.size() > 0) {

                            MasterShgReceiptSubTypeLoanSourceEntity masterReceiptSubType = new MasterShgReceiptSubTypeLoanSourceEntity();
                            masterReceiptSubType.id = 0;
                            masterReceiptSubType.sub_receipt_description = AppConstant.SELECT_RECEIPT_SUB_TYPE;
                            masterReceiptSubType.receipt_id = "0";
                            masterReceiptSubType.sub_receipt_id = "0";
                            masterReceiptSubTypeList.add(0, masterReceiptSubType);

                            reciept_sub_type_MBS.setVisibility(View.VISIBLE);
                            ReceiptSubTypeSpinnerAdapter receiptSubTypeSpinnerAdapter = new ReceiptSubTypeSpinnerAdapter(context,
                                    0, masterReceiptSubTypeList);
                            reciept_sub_type_MBS.setAdapter(receiptSubTypeSpinnerAdapter);
                        } else {
                            /* perform some view task */

                            switch (selectedRecieptTypeID) {
                                case "2":
                                    /*grand*/
                                    AddedReceiptEntityDataModel addedReceiptEntityDataModel = new AddedReceiptEntityDataModel();
                                    addedReceiptEntityDataModel.setCashBookPageNo("25");
                                    List<AddedReceiptEntityDataModel> xyz = new ArrayList<>();
                                    xyz.add(addedReceiptEntityDataModel);

                                    AddedReceiptAdapter addedReceiptAdapter = new AddedReceiptAdapter(context, selectedRecieptTypeID, xyz);
                                    added_recieptRV.setLayoutManager(new LinearLayoutManager(context));
                                    added_recieptRV.setAdapter(addedReceiptAdapter);

                                    break;
                                case "3":
                                    /*fund received from mission*/
                                    break;
                                case "4":
                                    /*other*/
                                    break;
                                case "6":
                                    /*repayment loan by shg member*/
                                    break;
                                case "7":
                                    /*receipt FD*/
                                    break;
                                case "9":
                                    /*amount withdrawal fom bank */
                                    break;
                            }

                        }

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            reciept_sub_type_MBS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MasterShgReceiptSubTypeLoanSourceEntity masterReceiptSubTypeItem = (MasterShgReceiptSubTypeLoanSourceEntity) parent
                            .getItemAtPosition(position);
                    AppUtils.getInstance().showLog("masterReceiptSubTypeItem" + masterReceiptSubTypeItem.sub_receipt_description
                            , GrandRecieptActivity.class);

                    receiptSubTypeId = masterReceiptSubTypeItem.sub_receipt_id.trim();
                    masterReceiptSubToSubTypesList.clear();
                    reciept_sub_to_sub_type_MBS.setVisibility(View.GONE);


                    try {
                        masterReceiptSubToSubTypesList = masterDataRepo.getReceiptSubToSubTypes(receiptSubTypeId);
                        if (masterReceiptSubToSubTypesList != null && masterReceiptSubToSubTypesList.size() > 0) {

                            MasterShgReceiptLoanEntity masterReceiptSubToSubType = new MasterShgReceiptLoanEntity();
                            masterReceiptSubToSubType.id = 0;
                            masterReceiptSubToSubType.shg_recpt_loan_name = AppConstant.SELECT_AGENCY_TYPE;
                            masterReceiptSubToSubType.shg_recpt_loan_value = "0";
                            masterReceiptSubToSubType.sub_receipt_id = "0";
                            masterReceiptSubToSubTypesList.add(0, masterReceiptSubToSubType);

                            reciept_sub_to_sub_type_MBS.setVisibility(View.VISIBLE);

                            ReceiptSubtToSubTypeSpinnerAdapter receiptSubtToSubTypeSpinnerAdapter = new ReceiptSubtToSubTypeSpinnerAdapter(context,
                                    0, masterReceiptSubToSubTypesList);
                            reciept_sub_to_sub_type_MBS.setAdapter(receiptSubtToSubTypeSpinnerAdapter);

                        } else {
                            /* perform some view task */
                        }

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            reciept_sub_to_sub_type_MBS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MasterShgReceiptLoanEntity masterShgReceiptLoanEntity = (MasterShgReceiptLoanEntity) parent.getItemAtPosition(position);
                    receiptSubToSubTypeId = masterShgReceiptLoanEntity.shg_recpt_loan_value.trim();
                    AppUtils.getInstance().showLog("receiptSubToSubTypeId= " + receiptSubToSubTypeId, GrandRecieptActivity.class);
                    if (receiptSubToSubTypeId.trim().equalsIgnoreCase(AppConditionalConstant.RECEIPT_ADD_NEW_LOAN_ID)) {

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.add_more_receiptsCV)
    public void cardviewOnclick() {
        openFullPageDialog();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openFullPageDialog() {

        dialog = new Dialog(context, android.R.style.Widget);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.receipt_full_screen_dialog);

        Spinner added_reciept_spinner1 = (Spinner) dialog.findViewById(R.id.added_reciept_spinner1);
        Spinner added_reciept_spinner2 = (Spinner) dialog.findViewById(R.id.added_reciept_spinner2);
        Spinner added_reciept_spinner3 = (Spinner) dialog.findViewById(R.id.added_reciept_spinner3);

        CardView text1CV = (CardView) dialog.findViewById(R.id.text1CV);
        TextView text_label1 = (TextView) dialog.findViewById(R.id.text_label1);
        TextView text_data_label1 = (TextView) dialog.findViewById(R.id.text_data_label1);

        CardView text2CV = (CardView) dialog.findViewById(R.id.text2CV);
        TextView text_label2 = (TextView) dialog.findViewById(R.id.text_label2);
        TextView text_data_label2 = (TextView) dialog.findViewById(R.id.text_data_label2);

        TextInputLayout edit_textTIL1 = (TextInputLayout) dialog.findViewById(R.id.edit_textTIL1);
        TextInputEditText edit_textTIET1 = (TextInputEditText) dialog.findViewById(R.id.edit_textTIET1);

        TextInputLayout edit_textTIL2 = (TextInputLayout) dialog.findViewById(R.id.edit_textTIL2);
        TextInputEditText edit_textTIET2 = (TextInputEditText) dialog.findViewById(R.id.edit_textTIET2);

        TextInputLayout edit_textTIL3 = (TextInputLayout) dialog.findViewById(R.id.edit_textTIL3);
        TextInputEditText edit_textTIET3 = (TextInputEditText) dialog.findViewById(R.id.edit_textTIET3);

        TextInputLayout edit_textTIL4 = (TextInputLayout) dialog.findViewById(R.id.edit_textTIL4);
        TextInputEditText edit_textTIET4 = (TextInputEditText) dialog.findViewById(R.id.edit_textTIET4);

        LinearLayout added_receipt_date_LL = (LinearLayout) dialog.findViewById(R.id.added_receipt_date_LL);
        TextView added_receipt_date_TV = (TextView) dialog.findViewById(R.id.added_receipt_date_TV);
        ImageView add_receipt_calanderIV = (ImageView) dialog.findViewById(R.id.add_receipt_calanderIV);

        Spinner added_reciept_trans_mode = (Spinner) dialog.findViewById(R.id.added_reciept_trans_mode);

        /*cancelTV*/
        TextView cancelTV = (TextView) dialog.findViewById(R.id.cancelTV);
        TextView submitTV = (TextView) dialog.findViewById(R.id.submitTV);

        added_receipt_date_TV.setText("Select Date");

        added_receipt_date_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = openCalender();
                if (date != null || !date.equalsIgnoreCase("")) {
                    added_receipt_date_TV.setText(date);
                }
            }
        });

        ArrayAdapter<String> transactionModeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                , context.getResources().getStringArray(R.array.transaction_mode));
        added_reciept_trans_mode.setAutofillHints("Transaction Mode");
        added_reciept_trans_mode.setAdapter(transactionModeAdapter);
        added_reciept_trans_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transactionMode=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        switch (selectedRecieptTypeID) {

            case "1":
                switch (receiptSubTypeId){
                    case "1":
                        switch (receiptSubToSubTypeId){
                            case "2":
                                added_reciept_spinner3.setVisibility(View.GONE);
                                edit_textTIL2.setVisibility(View.GONE);
                                edit_textTIL3.setVisibility(View.GONE);
                                edit_textTIL4.setVisibility(View.GONE);
                                text1CV.setVisibility(View.GONE);
                                text2CV.setVisibility(View.GONE);

                                edit_textTIL1.setHint("Loan Disbursed Amount*");
                                added_receipt_date_TV.setText("Loan Disbursed Date");

                                ArrayAdapter<String> loanTypeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                                        , context.getResources().getStringArray(R.array.loan_bank_existing_loan_type));

                                ArrayAdapter<String> loanIdAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                                        , context.getResources().getStringArray(R.array.loan_bank_existing_loan_id));

                                added_reciept_spinner1.setAdapter(loanTypeAdapter);
                                added_reciept_spinner2.setAdapter(loanIdAdapter);

                                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        added_reciept_spinner1_Value=(String)parent.getItemAtPosition(position);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                                added_reciept_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        added_reciept_spinner2_Value=(String)parent.getItemAtPosition(position);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                                break;
                        }
                        break;
                    case "2":
                        switch (receiptSubToSubTypeId){

                            case "2":
                                text2CV.setVisibility(View.GONE);
                                edit_textTIL2.setVisibility(View.GONE);
                                edit_textTIL3.setVisibility(View.GONE);
                                edit_textTIL4.setVisibility(View.GONE);
                                added_reciept_spinner2.setVisibility(View.GONE);
                                added_reciept_spinner3.setVisibility(View.GONE);

                                edit_textTIET1.setHint("Loan Disbursed Amount");
                                text_data_label1.setText("CLF Name");
                                text_label1.setText("CLF Name");

                                ArrayAdapter<String> loanIdAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                                        , context.getResources().getStringArray(R.array.loan_clf_existing_loan_type));
                                added_reciept_spinner1.setAdapter(loanIdAdapter);

                                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        added_reciept_spinner1_Value=(String)parent.getItemAtPosition(position);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                                break;
                        }
                        break;
                    case "3":
                        switch (receiptSubToSubTypeId){

                            case "2":
/*start from here */
                                break;
                        }
                        break;
                    case "4":
                        switch (receiptSubToSubTypeId){

                            case "2":

                                break;
                        }
                        break;
                    case "5":
                        switch (receiptSubToSubTypeId){
                            case "2":

                                break;
                        }
                        break;
                }
                break;
            case "2":
                /*grands*/
                added_reciept_spinner3.setVisibility(View.GONE);
                edit_textTIL4.setVisibility(View.GONE);
                text1CV.setVisibility(View.GONE);
                text2CV.setVisibility(View.GONE);

                edit_textTIL1.setHint("Grant Number*");
                edit_textTIL2.setHint("Grant Agency Name*");
                edit_textTIL3.setHint("Amount Received*");
                added_receipt_date_TV.setText("Date Received");

                ArrayAdapter<String> gransTypeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.grand_type));

                ArrayAdapter<String> gransSourceAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.grand_source));

                added_reciept_spinner1.setAdapter(gransTypeAdapter);
                added_reciept_spinner2.setAdapter(gransSourceAdapter);

                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner1_Value= (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                added_reciept_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner2_Value = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {


                    }
                });

                break;
            case "3":
                /* fund received from mission*/
                added_reciept_spinner3.setVisibility(View.GONE);
                text1CV.setVisibility(View.GONE);
                text2CV.setVisibility(View.GONE);
                edit_textTIL3.setVisibility(View.GONE);
                edit_textTIL4.setVisibility(View.GONE);

                edit_textTIL1.setHint("Receipt Number");
                edit_textTIL2.setHint("Amount");
                added_receipt_date_TV.setText("Date");

                ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.fnd_rcvd_frm_msn_type));

                ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.fnd_rcvd_frm_msn_source));

                added_reciept_spinner1.setAdapter(typeAdapter);
                added_reciept_spinner2.setAdapter(sourceAdapter);

                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner1_Value= (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                added_reciept_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner2_Value= (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;
            case "4":
                /*others*/
                added_reciept_spinner2.setVisibility(View.GONE);
                added_reciept_spinner3.setVisibility(View.GONE);
                text1CV.setVisibility(View.GONE);
                text2CV.setVisibility(View.GONE);

                edit_textTIL1.setHint("Other Receipt Source*");
                edit_textTIL2.setHint("Other Receipt Number*");
                edit_textTIL3.setHint("Other Receipt Agency Name*");
                edit_textTIL4.setHint("Amount*");
                added_receipt_date_TV.setText("Date");

                ArrayAdapter<String> otherreceiptTypeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.other_receipt_type));
                added_reciept_spinner1.setAdapter(otherreceiptTypeAdapter);

                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner1_Value=(String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                break;
            case "5":
                break;
            case "6":
                /*Repayment loan by shg member*/
                edit_textTIL4.setVisibility(View.GONE);
                text1CV.setVisibility(View.GONE);
                text2CV.setVisibility(View.GONE);

                edit_textTIL1.setHint("Loan Agency Name*");
                edit_textTIL2.setHint("Due Amount*");
                edit_textTIL3.setHint("Loan Amount Re-paid*");
                added_receipt_date_TV.setText("Date of Repayment");

                ArrayAdapter<String> loanRepaymentType = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.loan_repayment_typeM));
                added_reciept_spinner1.setAdapter(loanRepaymentType);

                ArrayAdapter<String> loan_repayment_sourceAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.loan_repayment_sourceM));
                added_reciept_spinner2.setAdapter(loan_repayment_sourceAdapter);

                ArrayAdapter<String> loan_numberMAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.loan_numberM));
                added_reciept_spinner3.setAdapter(loan_numberMAdapter);

                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner1_Value=(String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                added_reciept_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner2_Value=(String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                added_reciept_spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner3_Value=(String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                break;
            case "7":
                /*Receipt from FD*/

                added_reciept_spinner3.setVisibility(View.GONE);
                edit_textTIL2.setVisibility(View.GONE);
                edit_textTIL3.setVisibility(View.GONE);
                edit_textTIL4.setVisibility(View.GONE);
                text1CV.setVisibility(View.GONE);
                text2CV.setVisibility(View.GONE);

                edit_textTIL1.setHint("Amount*");
                added_receipt_date_TV.setText("Date");


                ArrayAdapter<String> receiptTypeAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.receipt_type));
                added_reciept_spinner1.setAdapter(receiptTypeAdapter);

                ArrayAdapter<String> fdNumberAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.fd_number));
                added_reciept_spinner2.setAdapter(fdNumberAdapter);

                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner1_Value=(String)parent.getItemAtPosition(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                added_reciept_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner2_Value=(String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                break;
            case "8":
                break;
            case "9":
                /*amount withdrawal from bank */
                text2CV.setVisibility(View.GONE);
                added_reciept_spinner3.setVisibility(View.GONE);
                edit_textTIL2.setVisibility(View.GONE);
                edit_textTIL3.setVisibility(View.GONE);
                edit_textTIL4.setVisibility(View.GONE);

                text_label1.setText("IFSC code");
                edit_textTIL1.setHint("Amount*");
                added_receipt_date_TV.setText("Date");

                ArrayAdapter<String> amntWdrlFrmBnkAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.amnt_wdrl_frm_bnk));
                added_reciept_spinner1.setAdapter(amntWdrlFrmBnkAdapter);

                ArrayAdapter<String> amntWdrlFrmBnkBranchAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview
                        , context.getResources().getStringArray(R.array.amnt_wdrl_frm_bnk_branch));
                added_reciept_spinner2.setAdapter(amntWdrlFrmBnkBranchAdapter);

                added_reciept_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner1_Value=(String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                added_reciept_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        added_reciept_spinner2_Value=(String)parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;
        }


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER_VERTICAL;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();

        submitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*referesh the added receipt adapter in every condition */
                switch (selectedRecieptTypeID) {
                    case "1":
                        break;
                    case "2":
                        /*grands*/

                        String grantNumber =edit_textTIET1.getText().toString().trim();
                        String grantAgencyName =edit_textTIET2.getText().toString().trim();
                        String amountReceived =edit_textTIET3.getText().toString().trim();
                        String grandType= added_reciept_spinner1_Value;
                        String grandSource= added_reciept_spinner2_Value;
                        String selectedDate=date;
                        String selectedTranMode=transactionMode;

                        if (grantNumber==null && grantNumber.equalsIgnoreCase(""))
                            edit_textTIET1.setError(getString(R.string.error_grand_number));
                        else if (grantAgencyName==null && grantAgencyName.equalsIgnoreCase(""))
                            edit_textTIET2.setError(getString(R.string.error_grand_agency_name));
                        else if (amountReceived==null && amountReceived.equalsIgnoreCase(""))
                            edit_textTIET3.setError(getString(R.string.error_amount_received));
                        else if (grandType==null && grandType.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_grand_type),Toast.LENGTH_LONG).show();
                        else if (grandSource==null && grandSource.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_grand_source),Toast.LENGTH_LONG).show();
                        else if (selectedTranMode==null && selectedTranMode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_trans_mode),Toast.LENGTH_LONG).show();
                        else if (selectedDate==null && selectedDate.equalsIgnoreCase(""))
                            added_receipt_date_TV.setError(getString(R.string.error_select_date));
                        else {
                            /*save grand data*/
                            Toast.makeText(context, "Grand data Saved Succesfully", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                        break;
                    case "3":
                            /* Fund received from mission*/
                        String receiptNumber=edit_textTIET1.getText().toString().trim();
                        String amount=edit_textTIET2.getText().toString().trim();
                        String type=added_reciept_spinner1_Value;
                        String source=added_reciept_spinner2_Value;

                        if (receiptNumber==null && receiptNumber.equalsIgnoreCase(""))
                            edit_textTIET1.setError(getString(R.string.error_frfm_receipt_number));
                        else if (amount==null && amount.equalsIgnoreCase(""))
                            edit_textTIET2.setError(getString(R.string.error_frfm_amount));
                        else if (type==null && type.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_frfm_type),Toast.LENGTH_LONG).show();
                        else if (source==null && source.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_frfm_source),Toast.LENGTH_LONG).show();
                        else if (transactionMode==null && transactionMode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_trans_mode),Toast.LENGTH_LONG).show();
                        else if (date==null && date.equalsIgnoreCase(""))
                            added_receipt_date_TV.setError(getString(R.string.error_select_date));
                        else {
                            /*save grand data*/
                            Toast.makeText(context, "Fund received from mission Saved Succesfully", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                        break;
                    case "4":
                        /*other*/
                        String otherReceiptSource=edit_textTIET1.getText().toString().trim();
                        String otherReceiptNumber=edit_textTIET2.getText().toString().trim();
                        String otherReceiptAgencyName=edit_textTIET3.getText().toString().trim();
                        String otherReceiptAmount=edit_textTIET4.getText().toString().trim();
                        String otherReceiptType=added_reciept_spinner1_Value;

                        if (otherReceiptSource==null && otherReceiptSource.equalsIgnoreCase(""))
                            edit_textTIET1.setError(getString(R.string.error_other_receipt_source));
                        else if (otherReceiptNumber==null && otherReceiptNumber.equalsIgnoreCase(""))
                            edit_textTIET2.setError(getString(R.string.error_other_receipt_number));
                        else if (otherReceiptAgencyName==null && otherReceiptAgencyName.equalsIgnoreCase(""))
                            edit_textTIET3.setError(getString(R.string.error_other_receipt_agency_name));
                        else if (otherReceiptAmount==null && otherReceiptAmount.equalsIgnoreCase(""))
                            edit_textTIET4.setError(getString(R.string.error_other_receipt_amount));
                        else if (otherReceiptType==null && otherReceiptType.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_other_receipt_type),Toast.LENGTH_LONG).show();
                        else if (transactionMode==null && transactionMode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_trans_mode),Toast.LENGTH_LONG).show();
                        else if (date==null && date.equalsIgnoreCase(""))
                            added_receipt_date_TV.setError(getString(R.string.error_select_date));
                        else {
                            /*save other data*/
                            Toast.makeText(context, "Others Details  Saved Succesfully", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                        break;
                    case "5":

                        break;
                    case "6":
                            /*Repayment of loans by shg member*/

                        String loanAgencyName=edit_textTIET1.getText().toString().trim();
                        String dueAmount=edit_textTIET2.getText().toString().trim();
                        String loanAmountRepaid=edit_textTIET3.getText().toString().trim();
                        String loanRepaymentType=added_reciept_spinner1_Value;
                        String loanRepaymentSource=added_reciept_spinner2_Value;
                        String loanNumber=added_reciept_spinner3_Value;

                        if (loanAgencyName==null && loanAgencyName.equalsIgnoreCase(""))
                            edit_textTIET1.setError(getString(R.string.error_other_receipt_source));
                        else if (dueAmount==null && dueAmount.equalsIgnoreCase(""))
                            edit_textTIET2.setError(getString(R.string.error_other_receipt_number));
                        else if (loanAmountRepaid==null && loanAmountRepaid.equalsIgnoreCase(""))
                            edit_textTIET3.setError(getString(R.string.error_other_receipt_agency_name));

                        else if (loanRepaymentType==null && loanRepaymentType.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_other_receipt_type),Toast.LENGTH_LONG).show();
                        else if (loanRepaymentSource==null && loanRepaymentSource.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_other_receipt_type),Toast.LENGTH_LONG).show();
                        else if (loanNumber==null && loanNumber.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_other_receipt_type),Toast.LENGTH_LONG).show();

                        else if (transactionMode==null && transactionMode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_trans_mode),Toast.LENGTH_LONG).show();
                        else if (date==null && date.equalsIgnoreCase(""))
                            added_receipt_date_TV.setError(getString(R.string.error_select_date));
                        else {
                            /*save Repayment of loans by shg member data*/
                            Toast.makeText(context, "Repayment of loans by shg member  Saved Succesfully", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                        break;
                    case "7":
                        /*Receipt from FD*/

                        String receiptFdAmount=edit_textTIET1.getText().toString().trim();
                        String receiptType=added_reciept_spinner1_Value;
                        String fdNumber=added_reciept_spinner2_Value;

                        if (receiptFdAmount==null && receiptFdAmount.equalsIgnoreCase(""))
                            edit_textTIET1.setError(getString(R.string.error_receipt_fd_amount));

                        else if (receiptType==null && receiptType.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_receipt_fd_type),Toast.LENGTH_LONG).show();
                        else if (fdNumber==null && fdNumber.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_receipt_fd_number),Toast.LENGTH_LONG).show();

                        else if (transactionMode==null && transactionMode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_trans_mode),Toast.LENGTH_LONG).show();
                        else if (date==null && date.equalsIgnoreCase(""))
                            added_receipt_date_TV.setError(getString(R.string.error_select_date));
                        else {
                            /*save Receipt FD  data*/
                            Toast.makeText(context, "Receipt FD  Saved Succesfully", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }


                        break;
                    case "8":
                        break;
                    case "9":
                            /*Amount withdrawal from bank*/

                        String ifscCode=text_data_label1.getText().toString().trim();
                        String withdrawalAmount=edit_textTIET1.getText().toString().trim();
                        String 	bankCode=added_reciept_spinner1_Value;
                        String 	branchCode=added_reciept_spinner2_Value;

                        if (withdrawalAmount==null && withdrawalAmount.equalsIgnoreCase(""))
                            edit_textTIET1.setError(getString(R.string.error_amount_wdrl_amount));

                        else if (bankCode==null && bankCode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_amount_wdrl_bank),Toast.LENGTH_LONG).show();
                        else if (branchCode==null && branchCode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_amount_wdrl_branch),Toast.LENGTH_LONG).show();

                        else if (transactionMode==null && transactionMode.equalsIgnoreCase(""))
                            Toast.makeText(context,getString(R.string.error_trans_mode),Toast.LENGTH_LONG).show();
                        else if (date==null && date.equalsIgnoreCase(""))
                            added_receipt_date_TV.setError(getString(R.string.error_select_date));
                        else {
                            /*save Amount withdrawal from bank data*/
                            Toast.makeText(context, "Amount withdrawal from bank Saved Succesfully", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }


                        break;
                }
            }
        });

        cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public String openCalender() {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        //c.add(Calendar.YEAR, -18);

        DatePickerDialog datePickerDialog = new DatePickerDialog(GrandRecieptActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        String day = "";
                        if (dayOfMonth < 10)
                            day = "0" + dayOfMonth;
                        else day = String.valueOf(dayOfMonth);
                        AppUtils.getInstance().showLog("daywithformate" + day, GrandRecieptActivity.class);

                        String monthc = "";
                        if (month < 10)
                            monthc = "0" + (month + 1);
                        else monthc = String.valueOf(month);
                        AppUtils.getInstance().showLog("monthwithformate" + monthc, GrandRecieptActivity.class);
                        receivedDate = day + "-" + monthc + "-" + year;

                                /*   added_receipt_date_LL.setText(day + "-" + monthc + "-" + year);
                                   receivedDate = added_receipt_date_TV.getText().toString().trim();*/
                    }
                }, mYear - 18, mMonth, mDay);
                           /* c.set(mYear - 18, mMonth, mDay);
                            long value = c.getTimeInMillis();*/
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
        return receivedDate;
    }

    @Override
    public void onBackPressed() {
        AppUtils.getInstance().makeIntent(this, RecieptsActivity.class, true);
        super.onBackPressed();
    }
}