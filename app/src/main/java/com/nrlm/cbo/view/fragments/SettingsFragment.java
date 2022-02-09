package com.nrlm.cbo.view.fragments;


import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.view.Activities.HomeActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.DialogFactory;
import com.nrlm.cbo.view.adapters.ShgSavingAdapter;
import com.nrlm.cbo.database.room.entity.GpAssign;
import com.nrlm.cbo.database.room.entity.VillageAssign;
import com.nrlm.cbo.model.dummyModels.SavingPojo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * created by
 * lincon on
 * 20-10-2020
 */

public class SettingsFragment extends BaseFragment implements HomeActivity.OnBackPressedListener {
    @BindView(R.id.setFrequency)
    MaterialButton btnBack;

    @BindView(R.id.commonRecyclerview)
    RecyclerView savingRecyclerView;

    @BindView(R.id.savingCardView)
    CardView savingCardView;

    @BindView(R.id.commonTextview)
    TextView recyclerviewtitle;

    @BindView(R.id.gpSpineer)
    MaterialBetterSpinner gpSpineer;

    @BindView(R.id.villageSpinner)
    MaterialBetterSpinner villageSpinner;


    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;


    /***all dummy list*****/
    List<GpAssign> gpAssignDataItemList;
    List<VillageAssign> villageAssignDataItemList;
    List<String> savingListItem;
    List<SavingPojo> savingDummyList;
    String savingname = "";

    ShgSavingAdapter savingCustomAdapter;

    private String[] savingArray;
    int positionD;


    public static SettingsFragment newInstance() {
        SettingsFragment aadharAccountScanner = new SettingsFragment();
        return aadharAccountScanner;
    }


    @Override
    public int getFragmentLayout() {
        return R.layout.setting_fragment;
    }

    @Override
    public void onFragmentReady() {
        ((HomeActivity) getActivity()).setOnBackPressedListener(this);
        initiliazationList();

        gpAssignDataItemList = AllDataList.getInstance(getContext()).getGpList();
        gpAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, AllDataList.getInstance(getContext()).getGpData());
        gpSpineer.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();

        gpSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "hiii", Toast.LENGTH_LONG).show();
                String gpCode = gpAssignDataItemList.get(position).gpCode;

                villageAssignDataItemList = AllDataList.getInstance(getContext()).getVillageList(gpCode);
                villageAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, AllDataList.getInstance(getContext()).getVillageData(gpCode));
                villageSpinner.setAdapter(villageAdapter);
                villageAdapter.notifyDataSetChanged();
            }
        });
        savingListItem = new ArrayList<>();
        savingArray = getResources().getStringArray(R.array.savingType);
        for (int i = 0; i < savingArray.length; i++) {
            savingListItem.add(savingArray[i]);
        }


    }

    private void initiliazationList() {
        savingDummyList = new ArrayList<>();
    }

    @Override
    public void doBack() {

    }

    @OnClick(R.id.setFrequency)
    public void Setfrequency() {

        Dialog view = DialogFactory.getInstance().showCustomDialog(getContext(), R.layout.add_frequency_layout, false);
        view.show();
        ArrayAdapter<String> savingAdapter;
        MaterialBetterSpinner selectSaving = view.findViewById(R.id.selectSavingSpinner);
        MaterialButton addFrequencyBtn = view.findViewById(R.id.addFrequencyBtn);
        MaterialButton cancelBtn = view.findViewById(R.id.cancelBtn);
        TextInputEditText amount = view.findViewById(R.id.savingAmountEt);
        savingAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, savingListItem);
        selectSaving.setAdapter(savingAdapter);

        selectSaving.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionD = position;
                savingname = savingListItem.get(positionD);

            }
        });


        addFrequencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savingListItem.remove(positionD);
                view.dismiss();
                SavingPojo savingPojo = new SavingPojo();
                savingPojo.setSavingAmount(amount.getText().toString());
                savingPojo.setSavingName(savingname);
                savingDummyList.add(savingPojo);
                savingCardView.setVisibility(View.VISIBLE);
                recyclerviewtitle.setText("Saving For Member");
                savingCustomAdapter = new ShgSavingAdapter(getContext(), savingDummyList);
                savingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                savingRecyclerView.setAdapter(savingCustomAdapter);
                savingCustomAdapter.notifyDataSetChanged();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.dismiss();
            }
        });
    }
}
