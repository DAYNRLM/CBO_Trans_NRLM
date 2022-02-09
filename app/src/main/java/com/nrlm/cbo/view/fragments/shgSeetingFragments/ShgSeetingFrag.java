package com.nrlm.cbo.view.fragments.shgSeetingFragments;


import android.content.DialogInterface;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.database.room.entities.MasterIsRfReturendEntity;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgSubActivityEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.adapters.savingAdapters.ShgAddSavingAdapter;
import com.nrlm.cbo.database.room.entity.MasterSavingEntity;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.entity.ShgSeetingSavings;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.nrlm.cbo.view.interfaces.OnButtonClickListner;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;

public class ShgSeetingFrag extends BaseFragment {

    /*************all text view*****************/
    @BindView(R.id.tbTitle)
    TextView tbTitle;

    @BindView(R.id.shgName_Tv)
    TextView shgName_Tv;

    @BindView(R.id.presidentFatherTv)
    TextView presidentFatherTv;

    @BindView(R.id.secretaryFatherTv)
    TextView secretaryFatherTv;

    @BindView(R.id.tressurerFatherTv)
    TextView tressurerFatherTv;

    @BindView(R.id.bookKeeperFatherTv)
    TextView bookKeeperFatherTv;

    /************all spinner***************/

    @BindView(R.id.meetingFrequencySpinner)
    MaterialBetterSpinner meetingFrequencySpinner;

    @BindView(R.id.cifReturnedSpinner)
    MaterialBetterSpinner cifReturnedSpinner;

    @BindView(R.id.rfReturnedSpinner)
    MaterialBetterSpinner rfReturnedSpinner;

    @BindView(R.id.presidentSpinner)
    MaterialBetterSpinner presidentSpinner;

    @BindView(R.id.secretarySpinner)
    MaterialBetterSpinner secretarySpinner;

    @BindView(R.id.tressurerSpinner)
    MaterialBetterSpinner tressurerSpinner;


    @BindView(R.id.bookKeeperSpinner)
    MaterialBetterSpinner bookKeeperSpinner;

   /* @BindView(R.id.categorySpinner)
    MaterialBetterSpinner categorySpinner;


    @BindView(R.id.subCategorySpinner)
    MaterialBetterSpinner subCategorySpinner;*/


    /***********all view and layout************/
    @BindView(R.id.commonRecyclerview)
    RecyclerView commonRecyclerview;


    /***********all btn**************/
    @BindView(R.id.addSavingBtn)
    MaterialButton addSavingBtn;

    @BindView(R.id.saveSettingBtn)
    MaterialButton saveSettingBtn;

    @BindView(R.id.closeSettingBtn)
    MaterialButton closeSettingBtn;

    @BindView(R.id.goToNominationBtn)
    MaterialButton goToNominationBtn;

    @BindView(R.id.rfradioGroup)
    RadioGroup rfradioGroup;

    /***************all edit text**********/

    @BindView(R.id.cashBookET)
    TextInputEditText cashBookET;

    @BindView(R.id.cashBookPageET)
    TextInputEditText cashBookPageET;




    /***************all TIL*************/
    @BindView(R.id.cashBookTTL)
    TextInputLayout cashBookTTL;

    @BindView(R.id.cashBookPageTTL)
    TextInputLayout cashBookPageTTL;




    /******************all instance classes**************/
    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    ShgmemberRepo shgmemberRepo;
    ShgDataRepo shgDataRepo;
    MasterDataRepo masterDataRepo;
    ShgSettingSavingFromMemberRepo shgSettingSavingFromMemberRepo;
    CustomProgressDialog customProgressDialog;


    /**************all list and adapters*******************/
    List<MeetingFrequencyEntity> meetingFrequencyDataList;
    List<MasterIsRfReturendEntity> rfReturendDataList;
    List<ShgMemberDataEntity> shgmemberdataList;
    /*List<MasterSeetingShgActivityEntity> activityDataList;
    List<MasterSeetingShgSubActivityEntity> subActivityDataList;*/
    List<ShgSettingSavingFromMemberEntity> shgSavingEntityDataList;
    List<MasterMemberSavingEntity> memberSavingDataList;

    ArrayAdapter<String> frequencyAdapter;
    ArrayAdapter<String> rfAdapter;
    ArrayAdapter<String> cifAdapter;
    ArrayAdapter<String> presidentAdapter;
    ArrayAdapter<String> secretaryAdapter;
    ArrayAdapter<String> tressureryAdapter;
    ArrayAdapter<String> bookKeeperAdapter;
    /*ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> subCategoryAdapter;*/
    ArrayAdapter<String> savingAdp;


    /**********all global variable************/
    String meetingTypeId = "";
    String rfReturnedId = "";
    String cifReturnedId = "";
    String rfReturnedRadioStatus = "yes";
    String cashBookNo = "";
    String cashBookPageNo = "";
    String shgLeaderCode = "";
    String shgSecretaryCode = "";
    String shgTressureCode = "";
    String shgBookkeeperCode = "";


    String shgCode = "";
    String shgName = "";
    String catValue = "";
    String savingType = "";
    String savingId = "";
    int savingpos;


    ShgAddSavingAdapter shgAddSavingAdapter;


    OnButtonClickListner onButtonClickListner;


    public static ShgSeetingFrag newInstance() {
        ShgSeetingFrag seetingFragment = new ShgSeetingFrag();
        return seetingFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_seeting_frag_layout;
    }

    @Override
    public void onFragmentReady() {
        initialiazation();
        shgCode = appSharedPreferences.getShgCodeForVerification();
        // shgName = allDataList.getShgUniqName(ShgCode);
        shgName = shgDataRepo.getshgName(shgCode);
        tbTitle.setText("SHG SETTINGS");
        shgName_Tv.setText(shgName + " SHG" + " (" + shgCode + ")");


        loadMasterData();

        loadMemberData();

        setClickListnerOnSpinner();

        loadSavingData();


        if (memberSavingDataList.isEmpty()) {
            addSavingBtn.setVisibility(View.GONE);
        }

        onButtonClickListner = new OnButtonClickListner() {
            @Override
            public void notifyDate(String Type, String date, ShgSettingSavingFromMemberEntity savingObject) {
                Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();

                switch (Type) {
                    case AppConstant.DELETE_TAG:
                        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
                        materialAlertDialogBuilder.setTitle("Delete Saving");
                        materialAlertDialogBuilder.setMessage("DO you want to delete this saving type");
                        materialAlertDialogBuilder.setCancelable(false);
                        materialAlertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String typeId = savingObject.saving_type;

                                shgSavingEntityDataList.clear();
                                shgSettingSavingFromMemberRepo.deleteSavingType(shgCode, typeId);

                                shgSavingEntityDataList = shgSettingSavingFromMemberRepo.getAllSavingData(shgCode);
                                shgAddSavingAdapter = new ShgAddSavingAdapter(shgSavingEntityDataList, getContext(), getActivity().getApplication(), onButtonClickListner);
                                commonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                                commonRecyclerview.setAdapter(shgAddSavingAdapter);
                                shgAddSavingAdapter.notifyDataSetChanged();


                                customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        customProgressDialog.hideProgress();

                                        dialog.dismiss();
                                    }
                                }, 2000);


                            }
                        });
                        materialAlertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        materialAlertDialogBuilder.show();
                        break;
                    case AppConstant.EDIT_TAG:
                        addSavings();

                        break;
                }
            }
        };

        shgAddSavingAdapter = new ShgAddSavingAdapter(shgSavingEntityDataList, getContext(), getActivity().getApplication(), onButtonClickListner);
        commonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        commonRecyclerview.setAdapter(shgAddSavingAdapter);
        shgAddSavingAdapter.notifyDataSetChanged();



        rfradioGroup.check(R.id.yesRadioBtn);

        rfradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.yesRadioBtn:
                        clearFocus(1);
                        rfReturnedRadioStatus = "yes";

                        break;
                    case R.id.noRadioBtn:
                        clearFocus(2);
                        rfReturnedRadioStatus = "no";


                        break;
                }
            }
        });

        meetingFrequencySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meetingTypeId = meetingFrequencyDataList.get(position).meeting_frequency;
            }
        });

        rfReturnedSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rfReturnedId =  rfReturendDataList.get(position).rf_to_be_return_value;
            }
        });

        cifReturnedSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cifReturnedId = rfReturendDataList.get(position).rf_to_be_return_value;
            }
        });


    }

    private void setClickListnerOnSpinner() {

        for (int i = 0; i < shgmemberdataList.size(); i++) {
            if (shgmemberdataList.get(i).leader.equalsIgnoreCase("President")) {
                presidentSpinner.setText(shgmemberdataList.get(i).memberName);
                presidentFatherTv.setText(shgmemberdataList.get(i).belongingName);
                shgLeaderCode=shgmemberdataList.get(i).shgCode;
            }
        }

        for (int i = 0; i < shgmemberdataList.size(); i++) {
            if (shgmemberdataList.get(i).leader.equalsIgnoreCase("Secretary")) {
                secretarySpinner.setText(shgmemberdataList.get(i).memberName);
                secretaryFatherTv.setText(shgmemberdataList.get(i).belongingName);
                shgSecretaryCode=shgmemberdataList.get(i).shgCode;
            }
        }

        for (int i = 0; i < shgmemberdataList.size(); i++) {
            if (shgmemberdataList.get(i).leader.equalsIgnoreCase("Treasurer")) {
                tressurerSpinner.setText(shgmemberdataList.get(i).memberName);
                tressurerFatherTv.setText(shgmemberdataList.get(i).belongingName);
                shgTressureCode=shgmemberdataList.get(i).shgCode;
            }
        }


        presidentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgLeaderCode =shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                presidentFatherTv.setText(fName);

            }
        });


        secretarySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgSecretaryCode =shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                secretaryFatherTv.setText(fName);

            }
        });

        tressurerSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgTressureCode =shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                tressurerFatherTv.setText(fName);

            }
        });

        bookKeeperSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgBookkeeperCode =shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                bookKeeperFatherTv.setText(fName);

            }
        });



        /*categorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                catValue = activityDataList.get(position).category_value;

                subActivityDataList = masterDataRepo.getShgSubActivityData(catValue);

                subCategoryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getSubActivityName(catValue));
                subCategorySpinner.setAdapter(subCategoryAdapter);
                subCategoryAdapter.notifyDataSetChanged();

            }
        });

        subCategorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/


    }

    private void loadMemberData() {
        shgmemberdataList = shgmemberRepo.getAllMemberData(shgCode);

        ArrayList<String> memberNameList = new ArrayList<>();
        for (int i = 0; i < shgmemberdataList.size(); i++) {
            memberNameList.add(shgmemberdataList.get(i).memberName);
        }

        /********president Spinner**********/
        presidentAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, memberNameList);
        presidentSpinner.setAdapter(presidentAdapter);
        presidentAdapter.notifyDataSetChanged();

        /*presidentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgLeaderCode =shgmemberdataList.get(position).shgCode;
            }
        });*/


        secretaryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, memberNameList);
        secretarySpinner.setAdapter(secretaryAdapter);
        secretaryAdapter.notifyDataSetChanged();

       /* secretarySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgSecretaryCode =shgmemberdataList.get(position).shgCode;
            }
        });*/

        tressureryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, memberNameList);
        tressurerSpinner.setAdapter(tressureryAdapter);
        tressureryAdapter.notifyDataSetChanged();

       /* tressurerSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgTressureCode =shgmemberdataList.get(position).shgCode;
            }
        });*/


        bookKeeperAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, memberNameList);
        bookKeeperSpinner.setAdapter(bookKeeperAdapter);
        bookKeeperAdapter.notifyDataSetChanged();
        bookKeeperFatherTv.setText("");

       /* bookKeeperSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgBookkeeperCode =shgmemberdataList.get(position).shgCode;
            }
        });*/


    }

    private void loadMasterData() {
        meetingFrequencyDataList = masterDataRepo.getMettingFreqData();





        frequencyAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getFrequencyName());
        meetingFrequencySpinner.setAdapter(frequencyAdapter);
        frequencyAdapter.notifyDataSetChanged();

        rfReturendDataList = masterDataRepo.getRFReturenedData();

        rfAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getRfName());
        rfReturnedSpinner.setAdapter(rfAdapter);
        rfAdapter.notifyDataSetChanged();

        cifAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getRfName());
        cifReturnedSpinner.setAdapter(cifAdapter);
        cifAdapter.notifyDataSetChanged();

       /* activityDataList = masterDataRepo.getShgActivityData();
        categoryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getActivityName());
        categorySpinner.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();*/

    }


    private void loadSavingData() {
        try {

            shgSavingEntityDataList = shgSettingSavingFromMemberRepo.getAllSavingData(shgCode);
            memberSavingDataList = masterDataRepo.getAllMasterSaving();

            for (int i = 0; i < shgSavingEntityDataList.size(); i++) {
                String typeId = shgSavingEntityDataList.get(i).saving_type;// chang in entity is saving_type_id
                for (int k = 0; k < memberSavingDataList.size(); k++) {
                    if (typeId.equalsIgnoreCase(memberSavingDataList.get(k).saving_type_code)) {
                        memberSavingDataList.remove(k);
                    }
                }
            }
            //savingList.addAll(Arrays.asList(getResources().getStringArray(R.array.savingType)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initialiazation() {
        meetingFrequencyDataList = new ArrayList<>();
        rfReturendDataList = new ArrayList<>();
        shgmemberdataList = new ArrayList<>();
       /* activityDataList = new ArrayList<>();
        subActivityDataList = new ArrayList<>();*/
        shgSavingEntityDataList = new ArrayList<>();
        memberSavingDataList = new ArrayList<>();

        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(getContext());
        allDataList = AllDataList.getInstance(getContext());
        shgDataRepo = new ShgDataRepo(getActivity().getApplication());
        shgmemberRepo = new ShgmemberRepo(getActivity().getApplication());
        masterDataRepo = new MasterDataRepo(getActivity().getApplication());
        shgSettingSavingFromMemberRepo = new ShgSettingSavingFromMemberRepo(getActivity().getApplication());
        customProgressDialog = CustomProgressDialog.newInstance(getContext());
    }

    @OnClick(R.id.addSavingBtn)
    public void addSavings() {
        MaterialButton addBtn, cancelBtn;
        MaterialBetterSpinner savingSpinner;
        TextInputEditText amount, roi;
        TextInputLayout amountInputLayout, roiInputlayout;

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
        View customLayout = getLayoutInflater().inflate(R.layout.shg_saving_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog d = materialAlertDialogBuilder.show();

        addBtn = customLayout.findViewById(R.id.dialogAddBtn);
        cancelBtn = customLayout.findViewById(R.id.dialogCancelBtn);
        savingSpinner = customLayout.findViewById(R.id.savingSpinner);
        amount = customLayout.findViewById(R.id.amountEt);
        roi = customLayout.findViewById(R.id.roiEt);
        amountInputLayout = customLayout.findViewById(R.id.amountInputLayout);
        roiInputlayout = customLayout.findViewById(R.id.roiInputlayout);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                amountInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        roi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                roiInputlayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        List<String> savList = new ArrayList<>();
        for (int i = 0; i < memberSavingDataList.size(); i++) {
            savList.add(memberSavingDataList.get(i).saving_type_name);
        }

        savingAdp = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, savList);
        savingSpinner.setAdapter(savingAdp);
        savingAdp.notifyDataSetChanged();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amt = amount.getText().toString();
                String rate = roi.getText().toString();
                if (savingId.equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Please Select Saving type first...", Toast.LENGTH_SHORT).show();
                } else if (amt.isEmpty()) {
                    amountInputLayout.setError("Enter Amount");
                } else if (rate.isEmpty()) {
                    roiInputlayout.setError("Enter Rate of Intrest");
                } else {
                    ShgSettingSavingFromMemberEntity shgSettingSavingFromMemberEntity = new ShgSettingSavingFromMemberEntity();
                    shgSettingSavingFromMemberEntity.shg_code = shgCode;
                    shgSettingSavingFromMemberEntity.saving_type = savingId;
                    shgSettingSavingFromMemberEntity.amount = amt;
                    shgSettingSavingFromMemberEntity.roi = rate;
                    new ShgSettingSavingFromMemberRepo(getActivity().getApplication()).insert(shgSettingSavingFromMemberEntity);

                    memberSavingDataList.remove(savingpos);

                    shgSavingEntityDataList.clear();
                    shgSavingEntityDataList = shgSettingSavingFromMemberRepo.getAllSavingData(shgCode);
                    shgAddSavingAdapter = new ShgAddSavingAdapter(shgSavingEntityDataList, getContext(), getActivity().getApplication(), onButtonClickListner);
                    commonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                    commonRecyclerview.setAdapter(shgAddSavingAdapter);

                    customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shgAddSavingAdapter.notifyDataSetChanged();
                            customProgressDialog.hideProgress();
                            d.dismiss();
                        }
                    }, 2000);

                    if (memberSavingDataList.isEmpty()) {
                        addSavingBtn.setVisibility(View.GONE);
                    }
                }
            }
        });
        savingSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savingType = memberSavingDataList.get(position).saving_type_name;
                savingId = memberSavingDataList.get(position).saving_type_code;
                savingpos = position;
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
    }

    @OnClick(R.id.goToNominationBtn)
    public void gotoNomination() {
        AppUtils.getInstance().replaceFragment(getFragmentManager(),
                ShgNominationFragment.newInstance(), ShgNominationFragment.class.getName(), true, R.id.seetingCommonFramContainer);
    }

    public void clearFocus(int id) {
        switch (id) {
            case 1:
                 rfReturnedId = "";
                 cifReturnedId = "";

                cifReturnedSpinner.setVisibility(View.VISIBLE);
                rfReturnedSpinner.setVisibility(View.VISIBLE);

                cifReturnedSpinner.setText(null);
                rfReturnedSpinner.setText(null);

                cifReturnedSpinner.setFocusable(false);
                rfReturnedSpinner.setFocusable(false);


                rfReturendDataList.clear();
                rfReturendDataList = masterDataRepo.getRFReturenedData();
                rfAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getRfName());
                rfReturnedSpinner.setAdapter(rfAdapter);
                rfAdapter.notifyDataSetChanged();

                cifAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getRfName());
                cifReturnedSpinner.setAdapter(cifAdapter);
                cifAdapter.notifyDataSetChanged();



                break;
            case 2:
                 rfReturnedId = "";
                 cifReturnedId = "";

                cifReturnedSpinner.setVisibility(View.VISIBLE);
                rfReturnedSpinner.setVisibility(View.GONE);

                cifReturnedSpinner.setText(null);
                rfReturnedSpinner.setText(null);

                cifReturnedSpinner.setFocusable(false);
                rfReturnedSpinner.setFocusable(false);


                rfReturendDataList.clear();
                rfReturendDataList = masterDataRepo.getRFReturenedData();
                rfAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getRfName());
                rfReturnedSpinner.setAdapter(rfAdapter);
                rfAdapter.notifyDataSetChanged();

                cifAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, masterDataRepo.getRfName());
                cifReturnedSpinner.setAdapter(cifAdapter);
                cifAdapter.notifyDataSetChanged();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    public void valiDateAllData() {
       /* cashBookNo=cashBookET.getText().toString();
        cashBookPageNo= cashBookPageET.getText().toString();*/


        if (meetingTypeId.equalsIgnoreCase("")) {
            Toast.makeText(getContext(), "Select Meeting Frequency first....", Toast.LENGTH_SHORT).show();
        }else {
            if(rfReturnedRadioStatus.equalsIgnoreCase("yes")){
                if(rfReturnedId.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Select RF returned yes/no..", Toast.LENGTH_SHORT).show();

                }else if(cifReturnedId.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Select CIF returned yes/no..", Toast.LENGTH_SHORT).show();
                }else {
                    commonValidation();
                }
            }else {
                if(cifReturnedId.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Select CIF returned yes/no..", Toast.LENGTH_SHORT).show();
                }else {
                    commonValidation();
                }
            }
        }
    }

    public void commonValidation(){
        /*if(cashBookNo.isEmpty()){
            Toast.makeText(getContext(), "Enter cash Book Number ", Toast.LENGTH_SHORT).show();
        }else if(cashBookPageNo.isEmpty()){
            Toast.makeText(getContext(), "Enter cash Book Page Number ", Toast.LENGTH_SHORT).show();
        }*/

        if(shgLeaderCode.isEmpty()){
            Toast.makeText(getContext(), "Select President first.. ", Toast.LENGTH_SHORT).show();
        }else if(shgSecretaryCode.isEmpty()){
            Toast.makeText(getContext(), "Select Secretary first", Toast.LENGTH_SHORT).show();
        }else if(shgTressureCode.isEmpty()){
            Toast.makeText(getContext(), "Select Tressure first", Toast.LENGTH_SHORT).show();
        }else if(shgBookkeeperCode.isEmpty()){
            Toast.makeText(getContext(), "Select Book keeper first", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Data Save....", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.saveSettingBtn)
    public void saveAllShgSetting(){
        valiDateAllData();
    }

}
