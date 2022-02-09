package com.nrlm.cbo.view.fragments.cutOffFragment.memberCuttOff;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.repositories.GpsRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.VillageRepo;
import com.nrlm.cbo.model.dummyModels.SavingPojo;
import com.nrlm.cbo.view.Activities.CutOffActivity;
import com.nrlm.cbo.view.adapters.memberCutOffAdapters.ShgNameCutOffAdapter;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.nrlm.cbo.view.fragments.cutOffFragment.ShgRunningLoanFragment;
import com.nrlm.cbo.view.interfaces.OnBackPressedListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MemberCutOffFragment extends BaseFragment implements OnBackPressedListener{
    @BindView(R.id.gpSpineer)
    AutoCompleteTextView gpSpineer;
    @BindView(R.id.villageSpinner)
    AutoCompleteTextView villageSpinner;
    @BindView(R.id.commonRecyclerview)
    RecyclerView cutOffShgListRV;
    @BindView(R.id.commonTextview)
    TextView commonTextview;
    @BindView(R.id.recyclerviewLayout)
    LinearLayout recyclerviewLayout;
    @BindView(R.id.tbTitle)
    TextView tbTittle;
    /***spinner Adapter***/
    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;
    /****recycler adapter***/
    ShgNameCutOffAdapter shgNameCutOffAdapter;
    /***all dummy list*****/
    List<String> savingListItem;
    List<SavingPojo> savingDummyList;
    List<Shg> shgList;
    List<GPsEntity> gpDataList;
    List<VillageEntity> villageDataList;
    List<ShgDataEntity> shgDataList;
    OnBackPressedListener onBackPressedListener;
    /***********utils classes*********/
    AppSharedPreferences appSharedPreferences;
    GpsRepo gpsRepo;
    VillageRepo villageRepo;
    ShgDataRepo shgDataRepo;
    AppUtils appUtils;
    /**********local strings**********/
    String gpCode = "";
    String villageCode = "";
        public static MemberCutOffFragment newInstance() {
        MemberCutOffFragment memberCutOffFragment = new MemberCutOffFragment();
        return memberCutOffFragment;
    }
    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_member_cutoff_selectlocation;
    }

    @Override
    public void onFragmentReady() {
        tbTittle.setText("Member CutOff");
        initiliazationList();
        getGpData();
        gpSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                appUtils.showLog("click on gp spinner:", CutOffActivity.class);
                gpCode = gpDataList.get(position).gp_code;
                getVillagedata(gpCode);
            }
        });
        villageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String villageCode = villageDataList.get(position).village_code;
              //  villageCode = "0523002005004";
                commonTextview.setText("SHG List");
                recyclerviewLayout.setVisibility(View.VISIBLE);
                appSharedPreferences.setVillageCodeSetting(villageCode);
                getShgList(villageCode);
            }
        });
    }
    private void initiliazationList() {
        gpsRepo = new GpsRepo(((Activity)getContext()).getApplication());
        villageRepo =new VillageRepo(((Activity)getContext()).getApplication());
        shgDataRepo =new ShgDataRepo(((Activity)getContext()).getApplication());
        appUtils = AppUtils.getInstance();
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(getContext());
        gpDataList = new ArrayList<>();
        villageDataList = new ArrayList<>();
        shgDataList = new ArrayList<>();
    }
    private void getShgList(String vilageCode) {
       shgDataList = shgDataRepo.getShgData(vilageCode);
        //shgDataList=shgDataRepo.getShgData(villageCode,"1");
        shgNameCutOffAdapter = new ShgNameCutOffAdapter(getContext(),shgDataList);
        cutOffShgListRV.setLayoutManager(new LinearLayoutManager(getContext()));
        cutOffShgListRV.setAdapter(shgNameCutOffAdapter);
        shgNameCutOffAdapter.notifyDataSetChanged();
    }
    private void getVillagedata(String gpCode) {
        villageDataList = villageRepo.getVillageData(gpCode);
        List<String> assignedVillages = villageRepo.getVillageName(gpCode); //Filling the village array list towards the selection of GP
        villageAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, assignedVillages);   //Binding the villages data with village spinner
        villageSpinner.setAdapter(villageAdapter);
        villageAdapter.notifyDataSetChanged();
    }
    private void getGpData() {
        gpDataList = gpsRepo.getAllGpData();
        gpAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, gpsRepo.getGpNameData()); //Binding the Gps data with Gp spinner
        gpSpineer.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();
    }
    @Override
    public void doBack() {
    }
}
