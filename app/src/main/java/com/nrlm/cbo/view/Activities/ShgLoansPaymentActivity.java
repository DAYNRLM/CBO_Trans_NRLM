package com.nrlm.cbo.view.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.nrlm.cbo.Utils.AppConditionalConstant;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.PreferenceFactory;
import com.nrlm.cbo.Utils.PrefrenceManager;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgSavingInfoEntityDataModel;
import com.nrlm.cbo.database.room.entities.MasterPaymentAgencyTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentEntity;
import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentSubTypeEntity;
import com.nrlm.cbo.database.room.entity.ShgPaymentTypeEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repository.ShgPaymentTypeRepo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nrlm.cbo.R;
import com.google.android.material.textfield.TextInputEditText;
import com.nrlm.cbo.view.adapters.spinnerAdapter.PaymentAgencyTypeSpinnerAdapter;
import com.nrlm.cbo.view.adapters.spinnerAdapter.PaymentSubTypeSpinnerAdapter;
import com.nrlm.cbo.view.adapters.spinnerAdapter.PaymentTypeSpinnerAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgLoansPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title)
    TextView title_activityTV;

    @BindView(R.id.shg_nameTV)
    TextView shg_nameTV;

    @BindView(R.id.shg_codeTV)
    TextView shg_codeTV;

    @BindView(R.id.meeting_dateTV)
    TextView meeting_dateTV;

    @BindView(R.id.meeting_noTV)
    TextView meeting_noTV;

    @BindView(R.id.shg_payment_typeMBS)
    Spinner shg_payment_typeMBS;

    @BindView(R.id.shg_payment_sub_MBS)
    Spinner shg_payment_sub_MBS;

    @BindView(R.id.payment_agency_typeMBS)
    Spinner payment_agency_typeMBS;

    /**/

    @BindView(R.id.shg_payment_type_viewTV)
    TextView shg_payment_type_viewTV;

    @BindView(R.id.vender_nameMBS)
    MaterialBetterSpinner vender_nameMBS;

    @BindView(R.id.date_paidTV)
    TextView date_paidTV;

    @BindView(R.id.calanderIV)
    ImageView calanderIV;

    private Context context;
    private String shgPaymentType, shgPaymentSubType, paymentAgencyType;
    private String[] resShgPmntTypeArray, resShgSubPmntTypeArray, resAgencyPmntTypeArray;

    private ShgDataRepo shgDataRepo;
    private MasterDataRepo masterDataRepo;
    private String selectedShgCode, selectedEntityCode, selectedPaymentTypeId;
    private ShgSavingInfoEntityDataModel shgSavingInfoEntityDataModel;

    private List<MasterTransShgPaymentEntity> masterPaymentTypeList = new ArrayList<>();
    private List<MasterTransShgPaymentSubTypeEntity> masterPaymentSubTypeList = new ArrayList<>();
    private List<MasterPaymentAgencyTypeEntity> paymentAgencyTypeEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_loans_payment);
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

        date_paidTV.setOnClickListener(this);
        calanderIV.setOnClickListener(this);

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
        });

        try {
            shgSavingInfoEntityDataModel = shgDataRepo.getShgSavingInfo(selectedEntityCode, selectedShgCode);

            masterPaymentTypeList = masterDataRepo.getAllPaymentType();

            if (masterPaymentTypeList != null && masterPaymentTypeList.size() > 0) {

                MasterTransShgPaymentEntity masterTransShgPaymentEntity = new MasterTransShgPaymentEntity();
                masterTransShgPaymentEntity.id = 0;
                masterTransShgPaymentEntity.payment_discription = AppConstant.SELECT_PAYMENT_TYPE;
                masterTransShgPaymentEntity.payments_type_id = "0";
                masterPaymentTypeList.add(0, masterTransShgPaymentEntity);

                PaymentTypeSpinnerAdapter paymentTypeSpinnerAdapter = new PaymentTypeSpinnerAdapter(context, masterPaymentTypeList);
                shg_payment_typeMBS.setAdapter(paymentTypeSpinnerAdapter);

                shg_payment_typeMBS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MasterTransShgPaymentEntity masterTransShgPaymentEntity1 = (MasterTransShgPaymentEntity) parent.getItemAtPosition(position);
                        selectedPaymentTypeId = masterTransShgPaymentEntity1.payments_type_id;
                        masterPaymentSubTypeList.clear();
                        shg_payment_sub_MBS.setVisibility(View.GONE);
                        payment_agency_typeMBS.setVisibility(View.GONE);
                        try {
                            masterPaymentSubTypeList = masterDataRepo
                                    .getPaymentSubTypes(selectedPaymentTypeId);
                            if (masterPaymentSubTypeList != null && masterPaymentSubTypeList.size() > 0) {

                                MasterTransShgPaymentSubTypeEntity masterTransShgPaymentSubTypeEntity = new MasterTransShgPaymentSubTypeEntity();
                                masterTransShgPaymentSubTypeEntity.id = 0;
                                masterTransShgPaymentSubTypeEntity.payments_type_id = "0";
                                masterTransShgPaymentSubTypeEntity.payments_Sub_type_id = "0";
                                masterTransShgPaymentSubTypeEntity.payment_sub_discription = AppConstant.SELECT_PAYMENT_SUB_TYPE;
                                masterPaymentSubTypeList.add(0, masterTransShgPaymentSubTypeEntity);

                                PaymentSubTypeSpinnerAdapter paymentSubTypeSpinnerAdapter = new PaymentSubTypeSpinnerAdapter(context, masterPaymentSubTypeList);
                                shg_payment_sub_MBS.setAdapter(paymentSubTypeSpinnerAdapter);
                                shg_payment_sub_MBS.setVisibility(View.VISIBLE);
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
            }
            shg_payment_sub_MBS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MasterTransShgPaymentSubTypeEntity masterTransShgPaymentSubTypeEntity = (MasterTransShgPaymentSubTypeEntity)
                            parent.getItemAtPosition(position);
                    String selectedPaymentSubTypeId = masterTransShgPaymentSubTypeEntity.payments_Sub_type_id;
                    paymentAgencyTypeEntityList.clear();
                    payment_agency_typeMBS.setVisibility(View.GONE);
                    try {
                        paymentAgencyTypeEntityList = masterDataRepo.getPaymentAgencyTypes(selectedPaymentTypeId, selectedPaymentSubTypeId);
                        if (paymentAgencyTypeEntityList != null && paymentAgencyTypeEntityList.size() > 0) {

                            MasterPaymentAgencyTypeEntity masterPaymentAgencyTypeEntity = new MasterPaymentAgencyTypeEntity();
                            masterPaymentAgencyTypeEntity.id = 0;
                            masterPaymentAgencyTypeEntity.payment_type_id = "0";
                            masterPaymentAgencyTypeEntity.payment_sub_type_id = "0";
                            masterPaymentAgencyTypeEntity.payment_agency_id = "0";
                            masterPaymentAgencyTypeEntity.payment_agency_type = AppConstant.SELECT_PAYMENT_AGENCY_TYPE;

                            paymentAgencyTypeEntityList.add(0, masterPaymentAgencyTypeEntity);

                            PaymentAgencyTypeSpinnerAdapter paymentAgencyTypeSpinnerAdapter = new PaymentAgencyTypeSpinnerAdapter(context, paymentAgencyTypeEntityList);
                            payment_agency_typeMBS.setAdapter(paymentAgencyTypeSpinnerAdapter);
                            payment_agency_typeMBS.setVisibility(View.GONE);
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


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        payment_agency_typeMBS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MasterPaymentAgencyTypeEntity masterPaymentAgencyTypeEntity = (MasterPaymentAgencyTypeEntity) parent.getItemAtPosition(position);
                String paymentAgencyTypeId = masterPaymentAgencyTypeEntity.payment_agency_id;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        vender_nameMBS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String vendorName = vender_nameMBS.getText().toString().trim();

                if (vendorName.trim().toLowerCase().equalsIgnoreCase(AppConditionalConstant.ADD_NEW_VENDOR)) {

                    View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_vendor_details, null, false);
                    TextInputEditText vendor_nameTIET = dialogView.findViewById(R.id.vendor_nameTIET);
                    TextInputEditText vendor_tinNumberTIET = dialogView.findViewById(R.id.vendor_tinNumberTIET);
                    TextInputEditText phone_numberTIET = dialogView.findViewById(R.id.phone_numberTIET);
                    TextInputEditText addressTIET = dialogView.findViewById(R.id.addressTIET);
                    TextView saveTV = dialogView.findViewById(R.id.saveTV);
                    TextView backTV = dialogView.findViewById(R.id.backTV);

                    MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
                    materialAlertDialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = materialAlertDialogBuilder.show();
                    saveTV.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String vendorName = vendor_nameTIET.getText().toString().trim().toUpperCase();
                            String vendorTinNumber = vendor_tinNumberTIET.getText().toString().trim();
                            String phone_number = phone_numberTIET.getText().toString().trim();
                            String vendorAddress = addressTIET.getText().toString().trim();

                            if (vendorName.isEmpty() || vendorName.length() < 3) {
                                vendor_nameTIET.setError(getString(R.string.error_vendor_name));
                            } else if (vendorTinNumber.isEmpty() || vendorTinNumber.length() < 3) {
                                vendor_tinNumberTIET.setError(getString(R.string.error_tin_number));
                            } else if (phone_number.isEmpty() || phone_number.length() < 10) {
                                phone_numberTIET.setError(getString(R.string.error_vendor_phone));
                            } else if (vendorAddress.isEmpty() || vendorAddress.length() < 3) {
                                addressTIET.setError(getString(R.string.error_address));
                            } else {
                                Toast.makeText(context, "Vendor data saved", Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                            }

                        }
                    });

                    backTV.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });


     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_paidTV:
                setDateOnView(context, date_paidTV);
                break;
            case R.id.calanderIV:
                setDateOnView(context, date_paidTV);
                break;
            default:
                break;
        }
    }

    public String setDateOnView(Context context, TextView textView) {
        String date = "";
        int mYear, mMonth, mDay;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        return date;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppUtils.getInstance().makeIntent(this, PaymentActivity.class, true);
    }


}