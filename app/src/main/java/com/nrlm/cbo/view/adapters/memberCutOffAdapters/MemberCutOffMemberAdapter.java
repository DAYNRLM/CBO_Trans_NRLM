package com.nrlm.cbo.view.adapters.memberCutOffAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.view.Activities.MemberCutOffLayActivity;
import com.nrlm.cbo.view.fragments.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberCutOffMemberAdapter extends RecyclerView.Adapter<MemberCutOffMemberAdapter.MyViewHolder> {

    Context context;
    List<ShgMemberDataEntity> shgMemberData;
    AppSharedPreferences appSharedPreferences;

    public MemberCutOffMemberAdapter(Context context, List<ShgMemberDataEntity> shgMembersData)
    {
        this.context=context;
        this.shgMemberData=shgMembersData;
        appSharedPreferences=new AppSharedPreferences(context);

    }



    @NonNull
    @Override
    public MemberCutOffMemberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shgmember_cutoff_member_custom_lay,parent,false);
        return new MemberCutOffMemberAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberCutOffMemberAdapter.MyViewHolder holder, int position) {
holder.memberName.setText(shgMemberData.get(position).memberName+"("+shgMemberData.get(position).shgMemberCode+")");
holder.belongingName.setText(shgMemberData.get(position).belongingName);
holder.Dob.setText(shgMemberData.get(position).birthDate);
holder.updateBt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        appSharedPreferences.setMemberCutOffSelectMemberCode(shgMemberData.get(position).shgMemberCode);

        AppUtils.getInstance().makeIntent(context, MemberCutOffLayActivity.class,false);

    }
});
}
    @Override
    public int getItemCount() {
        return shgMemberData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.memberNameTv)
        TextView memberName;
        @BindView(R.id.belongingNameTv)
        TextView belongingName;
@BindView(R.id.dobTv)
TextView Dob;
@BindView(R.id.updateBt)
        MaterialButton updateBt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
