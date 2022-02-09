package com.nrlm.cbo.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.view.Activities.AttendanceSelectLocationActivity;
import com.nrlm.cbo.view.Activities.HomeActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.view.adapters.ShgMemberPojoAdapter;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.model.dummyModels.ShgMemberPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MemberAttendanceFragment extends BaseFragment implements HomeActivity.OnBackPressedListener {

    @BindView(R.id.commonRecyclerview)
    RecyclerView shgRecyclerView;
    @BindView(R.id.commonTextview)
    TextView commonTextview;
    @BindView(R.id.select_all_CB)
    CheckBox selectAll;


    ShgMemberPojoAdapter memberAdapterForAttedance;
    AppSharedPreferences appSharedPreferences;
    public static MemberAttendanceFragment newInstance() {
        MemberAttendanceFragment attendanceFragment = new MemberAttendanceFragment();
        return attendanceFragment;
    }

    @Override
    public int getFragmentLayout() {

        return R.layout.member_attendance_fragment;
    }

    @Override
    public void onFragmentReady() {
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(getContext());
        String shgCode =appSharedPreferences.getShgCode();
        commonTextview.setText("SHG Member List");
        List<ShgMember> shgmemberdataList= AllDataList.getInstance(getContext()).getmemberWithShg(shgCode);
int count=0;
List<ShgMemberPojo> shgMemberPojos=new ArrayList<>();
        for(ShgMember shgMember:shgmemberdataList)
        {
            ShgMemberPojo shgMemberPojo=new ShgMemberPojo();

            shgMemberPojo.setShgCode(shgMember.getShgCode());
            shgMemberPojo.setShgMemberCode(shgMember.getShgMemberCode());
            shgMemberPojo.setShgMemberName(shgMember.getShgMemberName());
            shgMemberPojo.setPosition(count);
            count++;
            shgMemberPojos.add(shgMemberPojo);
        }
  /*      boolean[] shgMemberDataValue=new boolean[shgmemberdataList.size()];

        for (int i=0;i<shgMemberDataValue.length;i++){

            shgMemberDataValue[i]=true;
        }*/
       // memberAdapterForAttedance = new ShgMemberPojoAdapter(getContext(), shgMemberPojos,shgCode,selectAll);
        shgRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shgRecyclerView.setAdapter(memberAdapterForAttedance);
        memberAdapterForAttedance.notifyDataSetChanged();

    }
    @Override  //not working
    public void doBack() {
        Intent i = new Intent(getActivity(), AttendanceSelectLocationActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }
}
