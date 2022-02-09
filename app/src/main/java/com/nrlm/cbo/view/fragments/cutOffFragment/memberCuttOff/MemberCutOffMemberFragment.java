package com.nrlm.cbo.view.fragments.cutOffFragment.memberCuttOff;

import android.app.Activity;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.adapters.memberCutOffAdapters.MemberCutOffMemberAdapter;
import com.nrlm.cbo.view.fragments.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class MemberCutOffMemberFragment extends BaseFragment {
    @BindView(R.id.subtitle)
    TextView titleName;
    @BindView(R.id.cut_offRecyclerview)
    RecyclerView cutOffReclr;
    @BindView(R.id.tbTitle)
    TextView tbTitle;
    ShgmemberRepo shgmemberRepo;
    AppSharedPreferences appSharedPreferences;


    public static MemberCutOffMemberFragment newInstance()
    {
        MemberCutOffMemberFragment memberCutOffMemberFragment=new MemberCutOffMemberFragment();
        return memberCutOffMemberFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_member_cutoff_member;
    }

    @Override
    public void onFragmentReady() {
        tbTitle.setText("Member CutOff");
        titleName.setText("Member List");

    Initialization();
   List<ShgMemberDataEntity> shgMemberData =shgmemberRepo.getAllMemberData(appSharedPreferences.getSelectedShgCutOffSc());
        MemberCutOffMemberAdapter memberCutOffMemberAdapter=new MemberCutOffMemberAdapter(getContext(),shgMemberData);
        cutOffReclr.setLayoutManager(new LinearLayoutManager(getContext()));
        cutOffReclr.setAdapter(memberCutOffMemberAdapter);
        memberCutOffMemberAdapter.notifyDataSetChanged();
    }
    void Initialization()
    {
        shgmemberRepo=new ShgmemberRepo(((Activity)getContext()).getApplication());
        appSharedPreferences=new AppSharedPreferences(getContext());
    }
}
