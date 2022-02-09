package com.nrlm.cbo.view.adapters.shgCutoffAdapters;

import android.app.Application;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.repositories.ShgCutOffRepo;
import com.nrlm.cbo.view.interfaces.OnButtonClickListner;
import com.nrlm.cbo.view.interfaces.OnClickListnerForRunningInsurance;

import java.util.List;

public class ShgCutoffRunningInsuranceAdapter extends RecyclerView.Adapter<ShgCutoffRunningInsuranceAdapter.MyViewHolder> {
    List<ShgCutOffRunningInsuranceEntity> runnningInsuranceDataList;
    Context context;
    AppUtils appUtils;
    AppSharedPreferences appSharedPreferences;
    ShgCutOffRepo shgCutOffRepo;

    OnClickListnerForRunningInsurance mClickListner = null;
    public ShgCutoffRunningInsuranceAdapter(List<ShgCutOffRunningInsuranceEntity> runnningInsuranceDataList, Context context, Application application, OnClickListnerForRunningInsurance mClickListner ) {
        this.runnningInsuranceDataList = runnningInsuranceDataList;
        this.context = context;
        this.mClickListner =mClickListner;
        appUtils =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
        shgCutOffRepo =new ShgCutOffRepo(application);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_cutoff_running_insurance_adapter_view,parent,false);
        return new ShgCutoffRunningInsuranceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String finalData= "<b> Policy Number:-:</b>  "+
                runnningInsuranceDataList.get(position).policyNumber+
                "<br />"+
                "<b> Policy Name-:</b>  "+
                runnningInsuranceDataList.get(position).policyName+
                "<br />"+
                "<b>Policy Premium Amount-:</b>  "+
                runnningInsuranceDataList.get(position).policyPremiumAmount+
                "<span style=\"color:#5898ad\"> &#8377; </span>"+
                "<br />"+
                "<b>Policy Risk cover-:</b>  "+
                runnningInsuranceDataList.get(position).policyRiskCover+
                "<span style=\"color:#5898ad\"> &#8377; </span>"+
                "<br />"+
                "<b>Policy Start Date-:</b>  "+
                runnningInsuranceDataList.get(position).policyStartDate+
                "<br />"+
                "<b>Policy End Date-:</b>  "+
                runnningInsuranceDataList.get(position).policyEndDate;

       // <span>&#8377;</span>

               /* " Member Code ("+"<b>"+shgMembersDataListItem.get(position).shgMemberCode+"</b>"+")"  +"<br />"+
                "<u>"+"<span style=\"color:#4fc3f7\">WO/DO-: </span>"+"</u>"+"Sumit Sing phogat";*/
        holder.insuranceDetailTv.setText(Html.fromHtml(finalData));
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListner.notifyDate("edit",runnningInsuranceDataList.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return runnningInsuranceDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView insuranceDetailTv;
        MaterialButton deleteBtn,editBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBtn=itemView.findViewById(R.id.deleteBtn);
            editBtn=itemView.findViewById(R.id.editBtn);
            insuranceDetailTv=itemView.findViewById(R.id.insuranceDetailTv);
        }
    }
}
