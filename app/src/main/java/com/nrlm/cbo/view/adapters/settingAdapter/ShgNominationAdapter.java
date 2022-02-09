package com.nrlm.cbo.view.adapters.settingAdapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.MasterNomineeRelationEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.model.settingsModels.NomineeModel;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShgNominationAdapter extends RecyclerView.Adapter<ShgNominationAdapter.MyViewHolder> {
    List<ShgMemberDataEntity> shgMemberList;
    Context context;
    MasterDataRepo masterDataRepo;
    AppUtils appUtils;
    AppSharedPreferences appSharedPreferences;
    ArrayAdapter<String> relationAdapter;
    List<NomineeModel> nomineeLocalDataList;

    public ShgNominationAdapter(List<ShgMemberDataEntity> shgList, Context context, Application application) {
        this.shgMemberList = shgList;
        this.context = context;
        masterDataRepo =new MasterDataRepo(application);
        appUtils = AppUtils.getInstance();
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(context);
        nomineeLocalDataList = new ArrayList<>();
        for(int i=0;i<shgList.size();i++){
            NomineeModel nomineeModel = new NomineeModel();
            nomineeModel.setNomineeName("");
            nomineeModel.setPosition("");
            nomineeModel.setRelationCode("");
            nomineeModel.setRelationName("");
            nomineeModel.setShgActivityindividual("");
            nomineeModel.setShgActivityMember("");
            nomineeLocalDataList.add(nomineeModel);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_nomination_custom_layout,parent,false);
        return new ShgNominationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(shgMemberList.get(position).memberName);
        List<MasterNomineeRelationEntity> masterNomineeRelationEntities = masterDataRepo.getRelationData();
        relationAdapter = new ArrayAdapter<String>(context, R.layout.spinner_textview, masterDataRepo.getRelationName());
        holder.relationSpinner.setAdapter(relationAdapter);
        relationAdapter.notifyDataSetChanged();


        /***********nominee edit text **************************************/
        if(!nomineeLocalDataList.get(position).getNomineeName().equalsIgnoreCase("")){
            holder.nomineeNameEt.setText(nomineeLocalDataList.get(position).getNomineeName());
        }else {
            holder.nomineeNameEt.setText("");
        }
        holder.nomineeNameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String name =  holder.nomineeNameEt.getText().toString();
                    nomineeLocalDataList.get(position).setNomineeName(name);
                }
            }
        });
        /************************************************************************/


        /************************spinner ****************************************/
        if(!nomineeLocalDataList.get(position).getRelationCode().equalsIgnoreCase("")){
            holder.relationSpinner.setText(nomineeLocalDataList.get(position).getRelationName());
        }else {
            holder.relationSpinner.setText("");
        }

        holder.relationSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                nomineeLocalDataList.get(position).setRelationName(masterNomineeRelationEntities.get(spinnerPosition).relation_name);
                nomineeLocalDataList.get(position).setRelationCode(masterNomineeRelationEntities.get(spinnerPosition).relation_code);
            }
        });
        /***********************************************************************/
    }

    @Override
    public int getItemCount() {
        return shgMemberList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        MaterialBetterSpinner relationSpinner;
        TextInputEditText nomineeNameEt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.shgMemberNameTv);
            relationSpinner =itemView.findViewById(R.id.relationSpinner);
            nomineeNameEt =itemView.findViewById(R.id.nomineeNameEt);
        }
    }

    public  List<NomineeModel> getNomineeList(){
        return nomineeLocalDataList;
    }
}
