package com.nrlm.cbo.view.adapters.savingAdapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppConstant;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.view.interfaces.OnButtonClickListner;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShgAddSavingAdapter extends RecyclerView.Adapter<ShgAddSavingAdapter.MyViewHolder> {
    List<ShgSettingSavingFromMemberEntity> shgSeetingSavingsDataList;
    Context context;
    MasterDataRepo masterDataRepo;
    OnButtonClickListner mClickListner = null;

    public ShgAddSavingAdapter(List<ShgSettingSavingFromMemberEntity> shgSeetingSavingsDataList, Context context, Application application, OnButtonClickListner mClickListner) {
        this.shgSeetingSavingsDataList = shgSeetingSavingsDataList;
        this.context = context;
        this.mClickListner =mClickListner;
        masterDataRepo =new MasterDataRepo(application);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.seeting_saving_custom_layout, parent, false);
        return new ShgAddSavingAdapter.MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.amountTv.setText(shgSeetingSavingsDataList.get(position).amount);
        holder.roiTv.setText(shgSeetingSavingsDataList.get(position).roi);


        try {
            String typeId  =shgSeetingSavingsDataList.get(position).saving_type;
            String savingName =  masterDataRepo.getSavingTypeName(typeId);
            holder.savingtypeTv.setText(savingName);
            if(typeId.equalsIgnoreCase("1")){
                holder.deleteBtn.setVisibility(View.GONE);
            }
            holder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListner.notifyDate(AppConstant.EDIT_TAG,"edit task done",shgSeetingSavingsDataList.get(position));
                }
            });
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListner.notifyDate(AppConstant.DELETE_TAG,"delete this done",shgSeetingSavingsDataList.get(position));
                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return shgSeetingSavingsDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView roiTv,amountTv,savingtypeTv;
        MaterialButton editBtn,deleteBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roiTv =itemView.findViewById(R.id.roiTv);
            amountTv =itemView.findViewById(R.id.amountTv);
            savingtypeTv =itemView.findViewById(R.id.savingtypeTv);
            editBtn =itemView.findViewById(R.id.editBtn);
            deleteBtn =itemView.findViewById(R.id.deleteBtn);
        }
    }
}
