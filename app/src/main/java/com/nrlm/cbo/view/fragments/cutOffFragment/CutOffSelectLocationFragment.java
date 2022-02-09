/*
package com.example.shgtransactions.view.fragments.cutOffFragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.List;

import butterknife.BindView;




public class CutOffSelectLocationFragment extends BaseFragment implements OnBackPressedListener {
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
**spinner Adapter**

    ArrayAdapter<String> gpAdapter;
    ArrayAdapter<String> villageAdapter;
***recycler adapter**

    ShgAdapter shgAdapter;
**all dummy list****

    List<GpAssign> gpAssignDataItemList;
    List<VillageAssign> villageAssignDataItemList;
    List<Shg> shgDataListItem;
    OnBackPressedListener onBackPressedListener;
    public static CutOffSelectLocationFragment newInstance() {
        CutOffSelectLocationFragment shgCutOffFrag = new CutOffSelectLocationFragment();
        return shgCutOffFrag;
    }
    @Override
    public int getFragmentLayout() {
        return R.layout.cutoff_select_location_layout;
    }
    @Override
    public void onFragmentReady() {
        new CutOffActivity().setOnBackPressedListener(this::onFragmentReady);
        initiliazationList();
        getGpData();
        gpSpineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "hiii", Toast.LENGTH_LONG).show();
                String gpCode = gpAssignDataItemList.get(position).gpCode;
                getVillagedata(gpCode);
            }
        });
       villageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String vilageCode = villageAssignDataItemList.get(position).getVilageCode();
                commonTextview.setText("SHG List");
                recyclerviewLayout.setVisibility(View.VISIBLE);
                getShgList(vilageCode);
            }
        });

    }
    private void getShgList(String vilageCode) {
        shgDataListItem = AllDataList.getInstance(getContext()).getAllShg(vilageCode);
        shgAdapter = new ShgAdapter(getContext(), shgDataListItem);
        cutOffShgListRV.setLayoutManager(new LinearLayoutManager(getContext()));
        cutOffShgListRV.setAdapter(shgAdapter);
        shgAdapter.notifyDataSetChanged();
    }
    private void getVillagedata(String gpCode) {
        villageAssignDataItemList = AllDataList.getInstance(getContext()).getVillageList(gpCode);
        villageAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, AllDataList.getInstance(getContext()).getVillageData(gpCode));
        villageSpinner.setAdapter(villageAdapter);
        villageAdapter.notifyDataSetChanged();
    }
    private void getGpData() {
        gpAssignDataItemList = AllDataList.getInstance(getContext()).getGpList();
        gpAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textview, AllDataList.getInstance(getContext()).getGpData());
        gpSpineer.setAdapter(gpAdapter);
        gpAdapter.notifyDataSetChanged();
    }
    private void initiliazationList() {
    }
    @Override
    public void doBack() {
        Toast.makeText(getContext(), "Hello in frag", Toast.LENGTH_LONG).show();
    }
}
*/
