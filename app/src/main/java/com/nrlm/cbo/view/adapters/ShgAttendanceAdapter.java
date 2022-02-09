package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.view.Activities.AttendanceSelectMemberActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entity.Shg;

import java.util.List;

public class ShgAttendanceAdapter extends BaseAdapter{
    private TextView shgNameTV,totalShgMemberTV,shgcodeTV;
    Context context;
    AppSharedPreferences appSharedPreferences;
    LinearLayout locationLayout;
    public ShgAttendanceAdapter(Context context, List<ShgDataEntity> shgData, LinearLayout locationLayout) {
        super(context);
        this.context=context;
        this.locationLayout=locationLayout;
        dataList =shgData;
        layout_id=R.layout.shg_common_custom_layout;
        }
    @Override
    public View getView(View view) {
        shgNameTV=view.findViewById(R.id.shgnameTv);
        totalShgMemberTV=view.findViewById(R.id.totalMemberTv);
        shgcodeTV=view.findViewById(R.id.shgCodeTv);
        return view;
    }
    @Override
    public void onBindViewHold(int position, Object itemView) {

    }
    @Override
    public void onBindViewWithHolder(int position, Object itemView, @NonNull MyViewHolder viewHolder) {   //Rendering SHG details over the recyclerview
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
        ShgDataEntity ShgInfo=(ShgDataEntity) itemView;
        shgNameTV.setText(ShgInfo.shgName);
        shgcodeTV.setText(ShgInfo.shgCode);
       // totalShgMemberTV.setText(AllDataList.getInstance(context).getMemberCount(ShgInfo.getShgCode()));
       // totalShgMemberTV.setText(new ShgDataRepo(context).);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appSharedPreferences.setShgCode(ShgInfo.shgCode);

                AppUtils.getInstance().makeIntent(context, AttendanceSelectMemberActivity.class,false);

              /*  AppUtils.getInstance().replaceFragment( ((AppCompatActivity)context).getSupportFragmentManager(),
                MemberAttendanceFragment.newInstance(),
                MemberAttendanceFragment.class.getName(), true,  R.id.fragmentContainer);*/
            }
        });
    }
}
