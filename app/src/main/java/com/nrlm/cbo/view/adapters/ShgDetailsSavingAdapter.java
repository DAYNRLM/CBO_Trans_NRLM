package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.Utils.PreferenceFactory;
import com.nrlm.cbo.Utils.PrefrenceManager;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgEntityDataModel;
import com.nrlm.cbo.view.Activities.MemberSavingActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgDetailsSavingAdapter extends RecyclerView.Adapter<ShgDetailsSavingAdapter.ShgDetailsSavingHolder> implements Filterable {
    private Context context;
    private List<ShgEntityDataModel> shgInfoList;
    private List<ShgEntityDataModel> shgInfoListAll;

    public ShgDetailsSavingAdapter(Context context, List<ShgEntityDataModel> shgInfoList){
        this.context=context;
        this.shgInfoList=shgInfoList;
        this.shgInfoListAll=new ArrayList<>(shgInfoList);
    }

    @NonNull
    @Override
    public ShgDetailsSavingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reciepts_shg_details_item,parent,false);
        return new ShgDetailsSavingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShgDetailsSavingHolder holder, int position) {
        ViewPropertyAnimator viewPropertyAnimator= holder.itemView.animate();
        viewPropertyAnimator.setStartDelay(1000);
        viewPropertyAnimator.rotationXBy(7);
        holder.LocationTv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        holder.LocationTv.setText(shgInfoList.get(position).getEntityCode());
        holder.shgDetailsTV.setText(shgInfoList.get(position).getShgName()+" ("+shgInfoList.get(position).getShgCode()+")");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shgCode = shgInfoList.get(position).getShgCode();
                String entityCode = shgInfoList.get(position).getEntityCode();
                PreferenceFactory.getInstance().saveSharedPrefrecesData(PrefrenceManager.getPrefShgCode(),shgCode,context);
                PreferenceFactory.getInstance().saveSharedPrefrecesData(PrefrenceManager.getPrefEntityCode(),entityCode,context);
                AppUtils.getInstance().makeIntent(context, MemberSavingActivity.class,true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shgInfoList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ShgEntityDataModel> filteredList=new ArrayList<>();
            if (constraint.toString().trim().isEmpty()){
                filteredList.addAll(shgInfoListAll);
            }else {
                for (ShgEntityDataModel shgEntityDataModel:shgInfoListAll){
                    if (shgEntityDataModel.getShgName().trim().toLowerCase()
                            .contains(constraint.toString().trim().toLowerCase())){
                        filteredList.add(shgEntityDataModel);
                    }
                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            shgInfoList.clear();
            shgInfoList.addAll((Collection<? extends ShgEntityDataModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ShgDetailsSavingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.LocationTv)
        TextView LocationTv;

        @BindView(R.id.shgDetailsTV)
        TextView shgDetailsTV;

        public ShgDetailsSavingHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
