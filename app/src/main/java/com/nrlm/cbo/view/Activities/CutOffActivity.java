package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.database.room.repositories.GpsRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.VillageRepo;
import com.nrlm.cbo.model.dummyModels.SavingPojo;
import com.nrlm.cbo.view.adapters.ShgCutOffAdapter;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.R;
import com.nrlm.cbo.view.interfaces.OnBackPressedListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CutOffActivity extends AppCompatActivity implements OnBackPressedListener  {


    @BindView(R.id.gpSpineer)
    MaterialBetterSpinner gpSpineer;

    @BindView(R.id.villageSpinner)
    MaterialBetterSpinner villageSpinner;

    @BindView(R.id.commonRecyclerview)
    RecyclerView cutOffShgListRV;

    @BindView(R.id.commonTextview)
    TextView commonTextview;

    @BindView(R.id.recyclerviewLayout)
    LinearLayout recyclerviewLayout;

    /***spinner Adapter***/
    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;

    /****recycler adapter***/
    ShgCutOffAdapter shgCutOffAdapter;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_off);
        ButterKnife.bind(this);

        initiliazationList();
        getGpData();

        gpSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                appUtils.showLog("click on gp spinner:",CutOffActivity.class);
                gpCode = gpDataList.get(position).gp_code;
                getVillagedata(gpCode);
            }
        });

        villageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String vilageCode = villageAssignDataItemList.get(position).getVilageCode();

               // villageCode = "0523002005004";
                villageCode = villageDataList.get(position).village_code;
                commonTextview.setText("SHG List");
                recyclerviewLayout.setVisibility(View.VISIBLE);
                appSharedPreferences.setVillageCodeSetting(villageCode);
                getShgList(villageCode);
            }
        });
    }

    private void initiliazationList() {
        gpsRepo = new GpsRepo(getApplication());
        villageRepo =new VillageRepo(getApplication());
        shgDataRepo =new ShgDataRepo(getApplication());
        appUtils =AppUtils.getInstance();
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(CutOffActivity.this);


        gpDataList = new ArrayList<>();
        villageDataList = new ArrayList<>();
        shgDataList = new ArrayList<>();
    }

    private void getShgList(String vilageCode) {
        shgDataList = shgDataRepo.getShgData(vilageCode);
        shgCutOffAdapter = new ShgCutOffAdapter(shgDataList,CutOffActivity.this,getApplication());
        cutOffShgListRV.setLayoutManager(new LinearLayoutManager(CutOffActivity.this));
        cutOffShgListRV.setAdapter(shgCutOffAdapter);
        shgCutOffAdapter.notifyDataSetChanged();
    }

    private void getVillagedata(String gpCode) {
        villageDataList = villageRepo.getVillageData(gpCode);
        List<String> assignedVillages = villageRepo.getVillageName(gpCode); //Filling the village array list towards the selection of GP
        villageAdapter = new ArrayAdapter<String>(CutOffActivity.this, R.layout.spinner_textview, assignedVillages);   //Binding the villages data with village spinner
        villageSpinner.setAdapter(villageAdapter);
        villageAdapter.notifyDataSetChanged();

    }
    private void getGpData() {
        gpDataList = gpsRepo.getAllGpData();
        gpAdapter = new ArrayAdapter<String>(CutOffActivity.this, R.layout.spinner_textview, gpsRepo.getGpNameData()); //Binding the Gps data with Gp spinner
        gpSpineer.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();
    }
    @Override
    public void doBack() {
        Toast.makeText(CutOffActivity.this, "Hello in frag", Toast.LENGTH_LONG).show();

    }
   @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else {
            super.onBackPressed();
        }
    }
}