 package com.nrlm.cbo.view.fragments.verificationFragment;


import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.Activities.CutOffActivity;
import com.nrlm.cbo.view.Activities.HomeActivity;
import com.nrlm.cbo.view.Activities.ShgCutOffActivity;
import com.nrlm.cbo.view.adapters.ShgMemberVerificationAdapter;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.view.fragments.shgSeetingFragments.ShgSeetingFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/***created by
 * lincon bhalla
 * on
 * 16 dec 2020****/

public class ShgVerificationFragment extends BaseFragment {

    @BindView(R.id.commonRecyclerview)
    RecyclerView shgRecyclerView;

    @BindView(R.id.vrfiRejectBtn)
    MaterialButton vrfiRejectBtn;

    @BindView(R.id.vrfiAcceptBtn)
    MaterialButton vrfiAcceptBtn;


    //List<ShgMember> shgMemberDataList;
    List<ShgMemberDataEntity> shgMemberDataList;

    AllDataList allDataList;

    String shgCode="";

    ShgMemberVerificationAdapter shgMemberVerificationAdapter;

    ShgmemberRepo shgmemberRepo;
    ShgDataRepo shgDataRepo;
    AppUtils appUtils;
    AppSharedPreferences appSharedPreferences;
    CustomProgressDialog customProgressDialog;


    public static ShgVerificationFragment newInstance() {
        ShgVerificationFragment verificationFragment = new ShgVerificationFragment();
        return verificationFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_verification_frag_layout;
    }

    @Override
    public void onFragmentReady() {

        initializationAllList();
        shgCode =appSharedPreferences.getShgCodeForVerification();

        if(!shgCode.equalsIgnoreCase("")){
            //shgMemberDataList = allDataList.getmemberWithShg(shgCode);
            shgMemberDataList = shgmemberRepo.getAllMemberData(shgCode);
            showData();
        }else {
            Toast.makeText(getContext(),"shg code not found",Toast.LENGTH_SHORT).show();
        }
    }

    private void showData() {
        shgMemberVerificationAdapter = new ShgMemberVerificationAdapter(shgMemberDataList, getContext());
        shgRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shgRecyclerView.setAdapter(shgMemberVerificationAdapter);
        shgMemberVerificationAdapter.notifyDataSetChanged();
    }

    private void initializationAllList() {
        shgMemberDataList =new ArrayList<>();

        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(getContext());
        allDataList =AllDataList.getInstance(getContext());
        shgDataRepo = new ShgDataRepo(getActivity().getApplication());
        shgmemberRepo = new ShgmemberRepo(getActivity().getApplication());
        appUtils =  AppUtils.getInstance();
        customProgressDialog =CustomProgressDialog.newInstance(getContext());
    }

    @OnClick(R.id.vrfiRejectBtn)
    public void rejectBtn(){
        //show dialog for verification is not done
        appUtils.makeIntent(getContext(), HomeActivity.class, false);
    }

    @OnClick(R.id.vrfiAcceptBtn)
    public void acceptBtn() {
        //update shgverification status in  master table..
        //allDataList.updateVerifyStatus(shgCode, "1");
        shgDataRepo.updateVerificationStatus(shgCode,"1");
        Toast.makeText(getContext(), "Verified Done", Toast.LENGTH_SHORT).show();
        customProgressDialog.showProgress(AppConstant.DIALOG_MSG,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                customProgressDialog.hideProgress();
                /*appUtils.replaceFragment(getFragmentManager(),
                        ShgSeetingFrag.newInstance(), ShgSeetingFrag.class.getName(), true, R.id.seetingCommonFramContainer);*/
                appUtils.makeIntent(getContext(), HomeActivity.class, false);
            }
        },2000);



    }
}
