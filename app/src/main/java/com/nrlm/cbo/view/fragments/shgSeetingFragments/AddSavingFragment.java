package com.nrlm.cbo.view.fragments.shgSeetingFragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.Activities.ShgCutOffActivity;
import com.nrlm.cbo.view.Activities.ShgSeetingCommonActivity;
import com.nrlm.cbo.view.adapters.BaseAdapter;
import com.nrlm.cbo.view.adapters.savingAdapters.ShgAddSavingAdapter;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.nrlm.cbo.view.interfaces.OnButtonClickListner;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;

public class AddSavingFragment extends BaseFragment {

    @BindView(R.id.settingSavingCloseBtn)
    MaterialButton settingSavingCloseBtn;

    @BindView(R.id.settingAddMoreBtn)
    MaterialButton settingAddMoreBtn;

    @BindView(R.id.commonRecyclerview)
    RecyclerView commonRecyclerview;

    /******************all instance classes**************/
    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    ShgmemberRepo shgmemberRepo;
    ShgDataRepo shgDataRepo;
    MasterDataRepo masterDataRepo;
    ShgSettingSavingFromMemberRepo shgSettingSavingFromMemberRepo;
    CustomProgressDialog customProgressDialog;


    /**************all list and adapters*******************/
    List<ShgSettingSavingFromMemberEntity> shgSavingEntityDataList;
    List<MasterMemberSavingEntity> memberSavingDataList;


    /***************all variables*******************/
    String shgCode ="";
    String savingType = "";
    String savingId = "";
    String compulsoryAmount = "";
    String compulsoryROI = "";
    int savingpos;

    ArrayAdapter<String> savingAdp;



    ShgAddSavingAdapter shgAddSavingAdapter;
    OnButtonClickListner onButtonClickListner;


    public static AddSavingFragment newInstance() {
        AddSavingFragment addSavingFragment = new AddSavingFragment();
        return addSavingFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_setting_saving_frgment_layout;
    }

    @Override
    public void onFragmentReady() {

        getAllInstance();
        shgCode = appSharedPreferences.getShgCodeForVerification();
        loadSavingData();

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

                               // shgSavingEntityDataList.clear();
                                shgSettingSavingFromMemberRepo.deleteSavingType(shgCode, typeId);

                               /* shgSavingEntityDataList = shgSettingSavingFromMemberRepo.getAllSavingData(shgCode);
                                shgAddSavingAdapter = new ShgAddSavingAdapter(shgSavingEntityDataList, getContext(), getActivity().getApplication(), onButtonClickListner);
                                commonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                                commonRecyclerview.setAdapter(shgAddSavingAdapter);
                                shgAddSavingAdapter.notifyDataSetChanged();*/


                                customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        customProgressDialog.hideProgress();
                                        dialog.dismiss();
                                        AppUtils.getInstance().replaceFragment(getFragmentManager(), AddSavingFragment.newInstance(),AddSavingFragment.class.getName(),false,R.id.settingCommonFramLayout);
                                    }
                                }, 1000);


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
                        TextView dialogTitleTv;
                        MaterialButton closeBtn, okBtn;
                        TextInputEditText enterSavingAmountET,enterROIET;
                        TextInputLayout enterROITTL,enterSavingAmountTTL;
                        MaterialAlertDialogBuilder editDialogBuilder = new MaterialAlertDialogBuilder(getContext());
                        View customLayout = getLayoutInflater().inflate(R.layout.shg_setting_saving_edit_dialog_layout, null);
                        editDialogBuilder.setView(customLayout);
                        editDialogBuilder.setCancelable(false);
                        AlertDialog dialog = editDialogBuilder.show();

                        dialogTitleTv = customLayout.findViewById(R.id.dialogTitleTv);
                        closeBtn = customLayout.findViewById(R.id.closeBtn);
                        okBtn = customLayout.findViewById(R.id.okBtn);
                        enterSavingAmountET = customLayout.findViewById(R.id.enterSavingAmountET);
                        enterROIET = customLayout.findViewById(R.id.enterROIET);
                        enterROITTL = customLayout.findViewById(R.id.enterROITTL);
                        enterSavingAmountTTL = customLayout.findViewById(R.id.enterSavingAmountTTL);
                        String savingName="";
                        String typeId = savingObject.saving_type;
                        try {
                            savingName  = masterDataRepo.getSavingTypeName(typeId);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        dialogTitleTv.setText(savingName+" Saving");

                        enterROIET.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                enterROITTL.setError(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        enterSavingAmountET.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                enterSavingAmountTTL.setError(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                        closeBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String getROI = enterROIET.getText().toString();
                                String getAmount = enterSavingAmountET.getText().toString();
                                if(getAmount.isEmpty()){
                                    enterSavingAmountTTL.setError("Enter saving Amount");
                                }else if(getROI.isEmpty()) {
                                    enterROITTL.setError("Enter ROI(%)");
                                }else {
                                    String shgCode =savingObject.shg_code;
                                    shgSettingSavingFromMemberRepo.updateShgSaving(shgCode,typeId,getAmount,getROI);
                                    customProgressDialog.showProgress(AppConstant.DIALOG_MSG, false);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            customProgressDialog.hideProgress();
                                            dialog.dismiss();
                                            AppUtils.getInstance().replaceFragment(getFragmentManager(), AddSavingFragment.newInstance(),AddSavingFragment.class.getName(),false,R.id.settingCommonFramLayout);
                                        }
                                    }, 1000);
                                }
                            }
                        });

                        break;
                }
            }
        };

        if (memberSavingDataList.isEmpty()) {
            settingAddMoreBtn.setVisibility(View.GONE);
        }

        shgAddSavingAdapter = new ShgAddSavingAdapter(shgSavingEntityDataList, getContext(), getActivity().getApplication(), onButtonClickListner);
        commonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        commonRecyclerview.setAdapter(shgAddSavingAdapter);
        shgAddSavingAdapter.notifyDataSetChanged();

    }


    private void getAllInstance() {

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
                if(shgSavingEntityDataList.get(i).saving_type.equalsIgnoreCase("1")){
                    compulsoryAmount =shgSavingEntityDataList.get(i).amount;
                    compulsoryROI=shgSavingEntityDataList.get(i).roi;
                }
            }
            //savingList.addAll(Arrays.asList(getResources().getStringArray(R.array.savingType)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.settingAddMoreBtn)
    public void addMoreSaving(){


        if(compulsoryAmount.isEmpty()){
            Toast.makeText(getContext(),"First Edit the compulsory saving and enter Amount",Toast.LENGTH_SHORT).show();
        }else if(compulsoryROI.isEmpty()){
            Toast.makeText(getContext(),"First Edit the compulsory saving and enter ROT(%)",Toast.LENGTH_SHORT).show();
        }else {
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
                                AppUtils.getInstance().replaceFragment(getFragmentManager(), AddSavingFragment.newInstance(),AddSavingFragment.class.getName(),false,R.id.settingCommonFramLayout);
                            }
                        }, 1000);

                        if (memberSavingDataList.isEmpty()) {
                            settingAddMoreBtn.setVisibility(View.GONE);
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

    }

    @OnClick(R.id.settingSavingCloseBtn)
    public void goToShgSettingScreen(){
        Intent intent =new Intent(getContext(), ShgSeetingCommonActivity.class);
        getContext().startActivity(intent);
    }


}
