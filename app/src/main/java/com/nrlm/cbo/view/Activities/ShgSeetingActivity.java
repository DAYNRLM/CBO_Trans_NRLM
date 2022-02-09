package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.database.room.repositories.GpsRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.VillageRepo;
import com.nrlm.cbo.view.adapters.ShgSeetingAdapter;
import com.nrlm.cbo.database.room.entity.GpAssign;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.entity.VillageAssign;
import com.nrlm.cbo.view.interfaces.OnBackPressedListener;
import com.nrlm.cbo.model.dummyModels.SavingPojo;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

/***created by
 * lincon bhalla
 * on
 * 16 dec 2020****/
public class ShgSeetingActivity extends AppCompatActivity {
    OnBackPressedListener onBackPressedListener;

    @BindView(R.id.gpSpineer)
    MaterialBetterSpinner gpSpineer;

    @BindView(R.id.villageSpinner)
    MaterialBetterSpinner villageSpinner;

    @BindView(R.id.tbTitle)
    TextView tbTitle;

    @BindView(R.id.commonRecyclerview)
    RecyclerView shgRecyclerView;


    /****adapter*****/
    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;
    ShgSeetingAdapter shgSeetingAdapter;

    /***all dummy list*****/
    List<GpAssign> gpAssignDataItemList;
    List<VillageAssign> villageAssignDataItemList;
    List<String> savingListItem;
    List<SavingPojo> savingDummyList;
    List<Shg> shgList;

    List<GPsEntity> gpDataList;
    List<VillageEntity> villageDataList;
    List<ShgDataEntity> shgDataList;

    String gpCode = "";
    String villageCode = "";

    AppSharedPreferences appSharedPreferences;
    GpsRepo gpsRepo;
    VillageRepo villageRepo;
    ShgDataRepo shgDataRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_seeting);
        ButterKnife.bind(this);
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ShgSeetingActivity.this);
        tbTitle.setText("SHG SETTINGS");
        initiliazationList();
        bindGpdata();
        setSpinnerListner();
    }

    private void bindGpdata() {

        gpDataList = gpsRepo.getAllGpData();
        gpAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, gpsRepo.getGpNameData()); //Binding the Gps data with Gp spinner
        gpSpineer.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();
    }

    private void setSpinnerListner() {
        gpSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gpCode = gpDataList.get(position).gp_code;
                villageDataList = villageRepo.getVillageData(gpCode);
                List<String> assignedVillages = villageRepo.getVillageName(gpCode); //Filling the village array list towards the selection of GP
                villageAdapter = new ArrayAdapter<String>(ShgSeetingActivity.this, R.layout.spinner_textview, assignedVillages);   //Binding the villages data with village spinner
                villageSpinner.setAdapter(villageAdapter);
                villageAdapter.notifyDataSetChanged();
            }
        });

        villageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                villageCode = villageDataList.get(position).village_code;
                //villageCode = "0523002005004";
                appSharedPreferences.setVillageCodeSetting(villageCode);
                shgDataList = shgDataRepo.getShgData(villageCode);
                shgSeetingAdapter = new ShgSeetingAdapter(shgDataList, ShgSeetingActivity.this,getApplication());
                shgRecyclerView.setLayoutManager(new LinearLayoutManager(ShgSeetingActivity.this));
                shgRecyclerView.setAdapter(shgSeetingAdapter);
                shgSeetingAdapter.notifyDataSetChanged();

            }
        });
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    private void initiliazationList() {
        gpsRepo = new GpsRepo(getApplication());
        villageRepo =new VillageRepo(getApplication());
        shgDataRepo =new ShgDataRepo(getApplication());
        gpDataList = new ArrayList<>();
        villageDataList = new ArrayList<>();
        shgDataList = new ArrayList<>();

        gpAssignDataItemList = new ArrayList<>();
        villageAssignDataItemList = new ArrayList<>();
        savingListItem = new ArrayList<>();
        shgList = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else {
            super.onBackPressed();
        }
    }

    public void clearFocus(int id) {

    }

}