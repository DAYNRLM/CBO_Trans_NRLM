package com.nrlm.cbo.view.adapters.memberCutOffAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.view.Activities.ShgCutOffActivity;
import com.nrlm.cbo.view.fragments.cutOffFragment.memberCuttOff.MemberCutOffMemberFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgNameCutOffAdapter extends RecyclerView.Adapter<ShgNameCutOffAdapter.MyViewHolder> {
    Context context;
    List<ShgDataEntity> shgData;
    AppSharedPreferences appSharedPreferences;
    public ShgNameCutOffAdapter(Context context,List<ShgDataEntity> shgData)
    {
        this.context=context;
        this.shgData=shgData;
    }
    @NonNull
    @Override
    public ShgNameCutOffAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.member_cutoff_shg_custom_layout,parent,false);
        return new ShgNameCutOffAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ShgNameCutOffAdapter.MyViewHolder holder, int position) {
        holder.shgname.setText(shgData.get(position).shgName);
        holder.shgCode.setText(shgData.get(position).shgCode);
        if(shgData.get(position).cuttOffStatus.equalsIgnoreCase("1"))
        {
            holder.cardView.setBackgroundColor(context.getColor(R.color.light_green));
        }else {
            holder.cardView.setBackgroundColor(context.getColor(R.color.light_red));
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shgData.get(position).cuttOffStatus.equalsIgnoreCase("1")){
                appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
                appSharedPreferences.setSelectedShgCutOffSc(shgData.get(position).shgCode);
                AppUtils.getInstance().replaceFragment(((AppCompatActivity)context).getSupportFragmentManager(), MemberCutOffMemberFragment.newInstance(),MemberCutOffMemberFragment.class.getName(),true,R.id.frame_layout_member_cut_off);
            }
                else {
                    Dialog();
                }
            }
        });
       //holder.totalmember.setText();
    }
    public void Dialog() {
        TextView dialogTv, dialogCheckFieldTv;
        MaterialButton closeBtn, okBtn;
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        View customLayout = LayoutInflater.from(context).inflate(R.layout.common_custom_right_wrong_dialog, null);
        materialAlertDialogBuilder.setView(customLayout);
        materialAlertDialogBuilder.setCancelable(false);
        AlertDialog dialog = materialAlertDialogBuilder.show();
        dialogTv = customLayout.findViewById(R.id.dialogTv);
        dialogCheckFieldTv = customLayout.findViewById(R.id.dialogCheckFieldTv);
        closeBtn = customLayout.findViewById(R.id.closeBtn);
        okBtn = customLayout.findViewById(R.id.okBtn);
        dialogTv.setText("Alert!");
        dialogCheckFieldTv.setText("Access denied, SHG cutOff is yet to be completed.");
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dialog.dismiss();
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public int getItemCount() {
        return shgData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_viewBt)
        CardView cardView;
        @BindView(R.id.shg_nameTV)
        TextView shgname;
        @BindView(R.id.shg_codeTV)
        TextView shgCode;
        @BindView(R.id.totalMember_Tv)
        TextView totalmember;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
