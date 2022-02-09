package com.nrlm.cbo.view.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberInfoAndSavingsEntityDataModel;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberSavingTypeEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.view.adapters.savingAdapters.MemberSavingTypeAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberSavingDetailsAdapter extends RecyclerView.Adapter<MemberSavingDetailsAdapter.MemberSavingDetailsHolder> implements Filterable {
    private Context context;
    private Application application;
    private String selectedShgCode, cashBookPageNo;
    private List<MemberInfoAndSavingsEntityDataModel> memberDetailsList;
    private List<MemberInfoAndSavingsEntityDataModel> getMemberDetailsListAll;

    public MemberSavingDetailsAdapter(Context context, Application application, String selectedShgCode,String cashBookPageNo,List<MemberInfoAndSavingsEntityDataModel> memberDetailsList) {
        this.context = context;
        this.application=application;
        this.cashBookPageNo=cashBookPageNo;
        this.selectedShgCode=selectedShgCode;
        this.memberDetailsList = memberDetailsList;
        this.getMemberDetailsListAll = new ArrayList<MemberInfoAndSavingsEntityDataModel>(memberDetailsList);
    }

    @NonNull
    @Override
    public MemberSavingDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.member_details_saving_item, parent, false);
        return new MemberSavingDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberSavingDetailsHolder holder, int position) {
        try {
        /*holder.itemView.setElevation(10.00f);
        holder.itemView.setTranslationZ(10.00f);*/
        /* ViewPropertyAnimator rotation */
       /* ViewPropertyAnimator viewPropertyAnimator= holder.itemView.animate();
        viewPropertyAnimator.setStartDelay(1500);
        viewPropertyAnimator.rotationYBy(2);*/
        holder.LocationTv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        holder.LocationTv.setText(memberDetailsList.get(position).getMemberName() + " (" + memberDetailsList.get(position).getShgMemberCode() + ")");
        holder.father_nameTV.setText(memberDetailsList.get(position).getBelongingName());
        holder.cash_book_pagenoTV.setText(cashBookPageNo);

        List<MemberSavingTypeEntityDataModel> savingTypeAndSavingsList=new ShgSettingSavingFromMemberRepo(application).getSettingSavingType(selectedShgCode);
        /*here pass the join result of saving type and savings which are saved by the user at the time of member cutt off*/
            AppUtils.getInstance().showLog("savingTypeAndSavingsList"+savingTypeAndSavingsList.size(),MemberSavingDetailsAdapter.class);

            if (savingTypeAndSavingsList.size()>0){
                holder.no_saving_foundTV.setVisibility(View.GONE);
                holder.saving_typesRV.setVisibility(View.VISIBLE);
                MemberSavingTypeAdapter memberSavingTypeAdapter=new MemberSavingTypeAdapter(context,application,savingTypeAndSavingsList);
                holder.saving_typesRV.setLayoutManager(new LinearLayoutManager(context));
                holder.saving_typesRV.setAdapter(memberSavingTypeAdapter);
            }else {
                holder.saving_typesRV.setVisibility(View.GONE);
                holder.no_saving_foundTV.setVisibility(View.VISIBLE);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    /*    holder.compulsory_savingET.setText("10");
        holder.food_savingET.setText("10");
        holder.health_savingET.setText("10");
        holder.optional_savingET.setText("10");*/
    }

    @Override
    public int getItemCount() {
        return memberDetailsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MemberInfoAndSavingsEntityDataModel> filteredList = new ArrayList<>();
            if (constraint.toString().trim().toLowerCase().isEmpty()) {
                filteredList.addAll(getMemberDetailsListAll);
            } else {
                for (MemberInfoAndSavingsEntityDataModel shg : getMemberDetailsListAll) {
                    if (shg.getMemberName().trim().toLowerCase()
                            .contains(constraint.toString().trim().toLowerCase())) {
                        filteredList.add(shg);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            memberDetailsList.clear();
            memberDetailsList.addAll((Collection<? extends MemberInfoAndSavingsEntityDataModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class MemberSavingDetailsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.LocationTv)
        TextView LocationTv;
        @BindView(R.id.father_nameTV)
        TextView father_nameTV;

        @BindView(R.id.cash_book_pagenoTV)
        TextView cash_book_pagenoTV;

        @BindView(R.id.no_saving_foundTV)
        TextView no_saving_foundTV;




        @BindView(R.id.saving_typesRV)
        RecyclerView saving_typesRV;


      /*  @BindView(R.id.compulsory_savingET)
        EditText compulsory_savingET;
        @BindView(R.id.food_savingET)
        EditText food_savingET;
        @BindView(R.id.health_savingET)
        EditText health_savingET;
        @BindView(R.id.optional_savingET)
        EditText optional_savingET;*/
        public MemberSavingDetailsHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        }
    }
}
