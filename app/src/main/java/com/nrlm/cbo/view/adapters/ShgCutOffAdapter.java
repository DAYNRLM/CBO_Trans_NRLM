package com.nrlm.cbo.view.adapters;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.Utils.DialogFactory;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.model.response.LittleMasterData;
import com.nrlm.cbo.view.Activities.ShgCutOffActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.view.Activities.ShgSeetingCommonActivity;
import com.nrlm.cbo.view.Activities.ShgVerificationActivity;

import java.util.List;

public class   ShgCutOffAdapter extends RecyclerView.Adapter<ShgCutOffAdapter.MyViewHolder> {
    List<ShgDataEntity> shgDataList;
    Context context;
    AppSharedPreferences appSharedPreferences;
    ShgmemberRepo shgmemberRepo;
    Application application;
    AppUtils appUtils;

    public ShgCutOffAdapter(List<ShgDataEntity> shgDataList, Context context, Application application) {
        this.shgDataList = shgDataList;
        this.context = context;
        this.application = application;
        shgmemberRepo =new ShgmemberRepo(application);
        appUtils =AppUtils.getInstance();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_cutoff_location_custom_layout,parent,false);
        return new ShgCutOffAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
        holder.shgName_Tv.setText(shgDataList.get(position).shgName);
        holder.shgCode_Tv.setText("SHG Code: "+shgDataList.get(position).shgCode);
        holder.totalMember_Tv.setText("Total Member: "+shgmemberRepo.getTotalMember(shgDataList.get(position).shgCode));

        String verifyStatus=shgDataList.get(position).verificationStatus;
        String cutoffStatus =shgDataList.get(position).cuttOffStatus;
        if(cutoffStatus.equalsIgnoreCase("1")){
            holder.setting_Btn.setText("Edit");
            holder.setting_Btn.setBackgroundColor(context.getResources().getColor(R.color.button_light_red));
            holder.setting_Btn.setTextColor(context.getResources().getColor(R.color.shrine_red));
        }

        holder.setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyStatus.equalsIgnoreCase("1")){
                    //go to shg cut-off module
                    appSharedPreferences.setCodeForVerification(shgDataList.get(position).shgCode);
                    appUtils.makeIntent(context, ShgCutOffActivity.class,false);

                }else {
                    //show dialog for verification
                    DialogFactory.getInstance().showAlertDialog(context,  R.drawable.ic_attendance,"SHG Not verified", "first verify this shg for seeting", "Go To Verify", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            appSharedPreferences.setCodeForVerification(shgDataList.get(position).shgCode);
                            Intent intent =new Intent(context, ShgVerificationActivity.class);
                            context.startActivity(intent);
                        }
                    }, false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
            return shgDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shgName_Tv,shgCode_Tv,totalMember_Tv;
        MaterialButton setting_Btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shgName_Tv = itemView.findViewById(R.id.shgName_Tv);
            shgCode_Tv = itemView.findViewById(R.id.shgCode_Tv);
            totalMember_Tv = itemView.findViewById(R.id.totalMember_Tv);
            setting_Btn = itemView.findViewById(R.id.setting_Btn);
        }
    }


/*
    public ShgCutOffAdapter(Context context, List<ShgDataEntity> shgDataListItem, Application application) {
        super(context);
        dataList = shgDataListItem;
        layout_id = R.layout.shg_common_custom_layout;
        this.context = context;
    }

    @Override
    public View getView(View view) {
        shgnameTv = view.findViewById(R.id.shgnameTv);
        totalMemberTv = view.findViewById(R.id.totalMemberTv);

        return view;
    }

    @Override
    public void onBindViewHold(int position, Object itemView) {


    }

    @Override
    public void onBindViewWithHolder(int position, Object itemView, @NonNull MyViewHolder viewHolder) {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(context);
        ShgDataEntity ad = (ShgDataEntity) itemView;
        //String shgCode = ad.shgCode;
        shgnameTv.setText(ad.shgName);
        //totalMemberTv.setText(AllDataList.getInstance(context).getMemberCount(shgCode));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appSharedPreferences.setShgCode(ad.shgCode);
                AppUtils.getInstance().makeIntent(context, ShgCutOffActivity.class,false);
               *//* AppUtils.getInstance().replaceFragment(((AppCompatActivity) context).getSupportFragmentManager(),
                        SHGCutOffFragment.newInstance(), SHGCutOffFragment.class.getName(),
                        true, R.id.cutoffFramLayout);*//*
            }
        });
    }*/
}
