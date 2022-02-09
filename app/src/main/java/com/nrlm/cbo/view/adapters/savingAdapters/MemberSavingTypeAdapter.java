package com.nrlm.cbo.view.adapters.savingAdapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberSavingTypeEntityDataModel;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberSavingTypeAdapter extends RecyclerView.Adapter<MemberSavingTypeAdapter.MemberSavingTypeHolder> {
    private Context context;
    private Application application;
    private List<MemberSavingTypeEntityDataModel> memberSavingTypeList;

    public MemberSavingTypeAdapter(Context context, Application application, List<MemberSavingTypeEntityDataModel> memberSavingTypeList ){
        this.context=context;
        this.application=application;
        this.memberSavingTypeList=memberSavingTypeList;
    }


    @NonNull
    @Override
    public MemberSavingTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.member_saving_saving_type_item,parent,false);
        return new MemberSavingTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberSavingTypeHolder holder, int position) {
        String savingTypeId=memberSavingTypeList.get(position).getSaving_type();
        AppUtils.getInstance().showLog("savingTypeId"+savingTypeId,MemberSavingTypeAdapter.class);
        if (savingTypeId!=null || !savingTypeId.isEmpty()){
            try {
                String savingTypename=getSavingTypeName(savingTypeId);
                if (savingTypename!=null ||!savingTypename.isEmpty())
                    holder.saving_typeTV.setText(savingTypename);

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSavingTypeName(String savingTypeId) throws ExecutionException, InterruptedException {
        MasterDataRepo masterDataRepo=new MasterDataRepo(application);
        return masterDataRepo.getSavingTypeName(savingTypeId);
    }

    @Override
    public int getItemCount() {
        return memberSavingTypeList.size();
    }

    public class MemberSavingTypeHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.saving_typeTV)
        TextView saving_typeTV;

        @BindView(R.id.entered_saving_amount)
        EditText entered_saving_amount;



        public MemberSavingTypeHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
