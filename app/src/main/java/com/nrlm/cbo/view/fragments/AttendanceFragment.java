package com.nrlm.cbo.view.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entity.Shg;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AttendanceFragment extends BaseFragment {

    @BindView(R.id.gpSpineer)
    MaterialBetterSpinner gpSpinner;
    @BindView(R.id.villageSpinner)
    MaterialBetterSpinner villageSpinner;
    @BindView(R.id.commonRecyclerview)
    RecyclerView shgRecyclerView;
    @BindView(R.id.commonTextview)
    TextView commonTextview;
    @BindView(R.id.selectall_RL)
    RelativeLayout relativeLayoutSelcetAll;
    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;
    List<String> assignedGp;
    List<String> assignedVillages;
    List<Shg> shgList;
    private String selectedGpCode, selectedVillagecode;
    public static AttendanceFragment newInstance() {
        AttendanceFragment attendanceFragment = new AttendanceFragment();
        return attendanceFragment;
    }
    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_attendance;
    }
    @Override
    public void onFragmentReady() {
        initializationOfList();
        assignedGp = AllDataList.getInstance(getContext()).getGpData(); //Filling the GP's array list
        gpAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, AllDataList.getInstance(getContext()).getGpData()); //Binding the Gps data with Gp spinner
        gpSpinner.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();
        relativeLayoutSelcetAll
                .setVisibility(View.GONE);
        gpSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Clicklistener over the selection of particular village
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGpCode = AllDataList.getInstance(getContext()).getGpList().get(position).getGpCode();
                villageSpinner.setFocusable(true);
                villageSpinner.setText("");
                villageSpinner.setText("");
                setVillagesOnSpinner();
            }
        });
    }

    private void setVillagesOnSpinner() {
        assignedVillages = AllDataList.getInstance(getContext()).getVillageData(selectedGpCode); //Filling the village array list towards the selection of GP
        villageAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, assignedVillages);   //Binding the villages data with village spinner
        villageSpinner.setAdapter(villageAdapter);
        villageAdapter.notifyDataSetChanged();
        villageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {      //Clicklistener over the selection of the particular village
                selectedVillagecode = AllDataList.getInstance(getContext()).getVillageList(selectedGpCode).get(position).vilageCode;
                AppUtils.getInstance().showLog("code" + selectedVillagecode, AttendanceFragment.class);
                shgList = AllDataList.getInstance(getContext()).getAllShg(selectedVillagecode);
                commonTextview.setText("SHG List");
                /*ShgAttendanceAdapter attendanceAdapter = new ShgAttendanceAdapter(getContext(), shgList);
                shgRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                shgRecyclerView.setAdapter(attendanceAdapter);
                attendanceAdapter.notifyDataSetChanged();*/    //hided because this fragment has made an Activity
            }
        });

    }

    private void initializationOfList() {
        shgList = new ArrayList<>();
        assignedGp = new ArrayList<>();
        assignedVillages = new ArrayList<>();
    }
}
