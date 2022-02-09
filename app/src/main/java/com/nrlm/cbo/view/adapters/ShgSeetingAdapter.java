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

import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.Activities.ShgSeetingCommonActivity;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.DialogFactory;
import com.nrlm.cbo.database.room.entity.Shg;
import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.view.Activities.ShgVerificationActivity;

import java.util.List;

public class ShgSeetingAdapter extends RecyclerView.Adapter<ShgSeetingAdapter.MyViewHolder> {
    List<ShgDataEntity> shgDataList;
    Context context;
    AppSharedPreferences appSharedPreferences;
    ShgmemberRepo shgmemberRepo;
    Application application;


    public ShgSeetingAdapter(List<ShgDataEntity> shgDataList, Context context, Application application) {
        this.shgDataList = shgDataList;
        this.context = context;
        this.application = application;
        shgmemberRepo =new ShgmemberRepo(application);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_seeting_custom_layout, parent, false);
        return new ShgSeetingAdapter.MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
        holder.shgName_Tv.setText(shgDataList.get(position).shgName);
        holder.shgCode_Tv.setText("SHG Code: "+shgDataList.get(position).shgCode);
        holder.totalMember_Tv.setText("Total Member: "+shgmemberRepo.getTotalMember(shgDataList.get(position).shgCode));

        String verifyStatus=shgDataList.get(position).verificationStatus;
        String shgSettingStatus =shgDataList.get(position).settingStatus;

        if(shgSettingStatus.equalsIgnoreCase("1")){
            holder.setting_Btn.setText("Edit");
            holder.setting_Btn.setBackgroundColor(context.getResources().getColor(R.color.button_light_red));
            holder.setting_Btn.setTextColor(context.getResources().getColor(R.color.shrine_red));
        }

        holder.setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyStatus.equalsIgnoreCase("1")){

                    appSharedPreferences.setCodeForVerification(shgDataList.get(position).shgCode);
                    Intent intent =new Intent(context, ShgSeetingCommonActivity.class);
                    context.startActivity(intent);

                }else {
                    //show dialog for verification
                    DialogFactory.getInstance().showAlertDialog(context,  R.drawable.ic_attendance,"SHG Not verified", "first verify this shg for seeting", "Go To Verify", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                           /* AppUtils.getInstance().replaceFragment(((AppCompatActivity)context).getSupportFragmentManager(),
                                    ShgVerificationFragment.newInstance(), ShgVerificationFragment.class.getName(), true, R.id.seetingCommonFramContainer);*/

                           /* appSharedPreferences.setCodeForVerification(shgDataList.get(position).shgCode);
                            Intent intent =new Intent(context, ShgSeetingCommonActivity.class);
                            context.startActivity(intent);*/

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
}
