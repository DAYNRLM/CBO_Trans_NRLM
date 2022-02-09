package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.repositories.GpsRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.VillageRepo;
import com.nrlm.cbo.view.adapters.ShgAttendanceAdapter;
import com.nrlm.cbo.database.room.entity.Shg;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceSelectLocationActivity extends AppCompatActivity {
    @BindView(R.id.gpSpineer)
    MaterialBetterSpinner gpSpinner;
  /*  @BindView(R.id.gpSpineer)
    MaterialBetterSpinner gpSpinner;*/

    @BindView(R.id.villageSpinner)
    MaterialBetterSpinner villageSpinner;

    @BindView(R.id.commonRecyclerview)
    RecyclerView shgRecyclerView;

    @BindView(R.id.commonTextview)
    TextView commonTextview;

 /*   @BindView(R.id.selectall_RL)
    RelativeLayout relativeLayoutSelcetAll;*/
    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;
    List<String> assignedGp;
    List<String> assignedVillages;
    List<ShgDataEntity> shgList;
    private String selectedGpCode, selectedVillagecode;

    LinearLayout locationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_select_location);
        ButterKnife.bind(this);
        locationLayout=findViewById(R.id.locationlayout);
        initializationOfList();
        assignedGp = new GpsRepo(getApplication()).getGpNameData(); //Filling the GP's array list
        gpAdapter = new ArrayAdapter<String>(AttendanceSelectLocationActivity.this, R.layout.spinner_textview, assignedGp); //Binding the Gps data with Gp spinner
        gpSpinner.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();
       // relativeLayoutSelcetAll
               // .setVisibility(View.GONE);
              gpSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Clicklistener over the selection of particular village
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           //     selectedGpCode = AllDataList.getInstance(AttendanceSelectLocationActivity.this).getGpList().get(position).getGpCode();
                selectedGpCode = new GpsRepo(getApplication()).getAllGpData().get(position).gp_code;
                villageSpinner.setFocusable(true);
                villageSpinner.setText("");
                villageSpinner.setText("");
                setVillagesOnSpinner();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationLayout.setVisibility(View.VISIBLE);

    }

    private void setVillagesOnSpinner() {
        assignedVillages = new VillageRepo(getApplication()).getVillageName(selectedGpCode); //Filling the village array list towards the selection of GP
        villageAdapter = new ArrayAdapter<String>(AttendanceSelectLocationActivity.this, R.layout.spinner_textview, assignedVillages);   //Binding the villages data with village spinner
        villageSpinner.setAdapter(villageAdapter);
        villageAdapter.notifyDataSetChanged();
        villageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {      //Clicklistener over the selection of the particular village
              //  selectedVillagecode = AllDataList.getInstance(AttendanceSelectLocationActivity.this).getVillageList(selectedGpCode).get(position).vilageCode;
               // selectedVillagecode = new VillageRepo(getApplication()).getVillageData(selectedGpCode).get(position).village_code;
                selectedVillagecode = "0523002005004";
                AppUtils.getInstance().showLog("code" + selectedVillagecode, AttendanceSelectLocationActivity.class);
               // shgList = AllDataList.getInstance(AttendanceSelectLocationActivity.this).getAllShg(selectedVillagecode);
                shgList = new ShgDataRepo(getApplication()).getShgData(selectedVillagecode);
                commonTextview.setText("SHG List");
                ShgAttendanceAdapter attendanceAdapter = new ShgAttendanceAdapter(AttendanceSelectLocationActivity.this, shgList,locationLayout);
                shgRecyclerView.setLayoutManager(new LinearLayoutManager(AttendanceSelectLocationActivity.this));
                shgRecyclerView.setAdapter(attendanceAdapter);
                attendanceAdapter.notifyDataSetChanged();
            }
        });
    }
    private void initializationOfList() {
        shgList = new ArrayList<>();
        assignedGp = new ArrayList<>();
        assignedVillages = new ArrayList<>();
    }
}