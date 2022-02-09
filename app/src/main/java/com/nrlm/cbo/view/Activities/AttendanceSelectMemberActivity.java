package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.adapters.ShgMemberPojoAdapter;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.model.dummyModels.ShgMemberPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceSelectMemberActivity extends AppCompatActivity {
    @BindView(R.id.commonRecyclerview)
    RecyclerView shgRecyclerView;
    @BindView(R.id.commonTextview)
    TextView commonTextview;
    @BindView(R.id.select_all_CB)
    CheckBox selectAll;
    @BindView(R.id.count)
    TextView countTv;

    ShgMemberPojoAdapter memberAdapterForAttedance;
    AppSharedPreferences appSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_select_member);
        ButterKnife.bind(this);
       ;
      //  AppUtils.getInstance().replaceFragment( getSupportFragmentManager(),
       // MemberAttendanceFragment.newInstance(),
       // MemberAttendanceFragment.class.getName(), false,  R.id.fragmentContainer);
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(AttendanceSelectMemberActivity.this);
        String shgCode =appSharedPreferences.getShgCode();
        commonTextview.setText("SHG Member List");
       // List<ShgMember> shgmemberdataList= AllDataList.getInstance(AttendanceSelectMemberActivity.this).getmemberWithShg(shgCode);
        List<ShgMemberDataEntity> shgmemberdataList= new ShgmemberRepo(getApplication()).getAllMemberData(shgCode);
        int count=0;
        List<ShgMemberPojo> shgMemberPojos=new ArrayList<>();
        for(ShgMemberDataEntity shgMember:shgmemberdataList)
        {
            ShgMemberPojo shgMemberPojo=new ShgMemberPojo();
            shgMemberPojo.setShgCode(shgMember.shgCode);
            shgMemberPojo.setShgMemberCode(shgMember.shgMemberCode);
            shgMemberPojo.setShgMemberName(shgMember.memberName);
            shgMemberPojo.setPosition(count);
            count++;
            shgMemberPojos.add(shgMemberPojo);
        }
  /*      boolean[] shgMemberDataValue=new boolean[shgmemberdataList.size()];

        for (int i=0;i<shgMemberDataValue.length;i++){

            shgMemberDataValue[i]=true;
        }*/
        memberAdapterForAttedance = new ShgMemberPojoAdapter(AttendanceSelectMemberActivity.this, shgMemberPojos,shgCode,selectAll,countTv,findViewById(R.id.submitBtn));
        shgRecyclerView.setLayoutManager(new LinearLayoutManager(AttendanceSelectMemberActivity.this));
        shgRecyclerView.setAdapter(memberAdapterForAttedance);
        memberAdapterForAttedance.notifyDataSetChanged();
    }

}