package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.Utils.PreferenceFactory;
import com.nrlm.cbo.Utils.PrefrenceManager;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgEntityDataModel;
import com.nrlm.cbo.view.Activities.GrandRecieptActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entity.Shg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgDetailsReceiptsAdapter extends RecyclerView.Adapter<ShgDetailsReceiptsAdapter.ShgDetailsHolder>
        implements Filterable {

    private List<ShgEntityDataModel> shgDetailsList;
    private List<ShgEntityDataModel> shgDetailsListAll;
    private Context context;

    public ShgDetailsReceiptsAdapter(Context context, List<ShgEntityDataModel> shgDetailsList){
        this.context=context;
        this.shgDetailsList=shgDetailsList;
        this.shgDetailsListAll=new ArrayList<>(shgDetailsList);
    }

    @NonNull
    @Override
    public ShgDetailsReceiptsAdapter.ShgDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View selectShgListView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reciepts_shg_details_item, parent, false);
        return new ShgDetailsReceiptsAdapter.ShgDetailsHolder(selectShgListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShgDetailsReceiptsAdapter.ShgDetailsHolder holder, int position) {
        holder.LocationTv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        holder.LocationTv.setText(shgDetailsList.get(position).getEntityCode());
        holder.shgDetailsTV.setText(shgDetailsList.get(position).getShgName()+" ("+shgDetailsList.get(position).getShgCode()+")");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedEntityCode=shgDetailsList.get(position).getEntityCode();
                String selectedShgCode=shgDetailsList.get(position).getShgCode();
                PreferenceFactory.getInstance().saveSharedPrefrecesData(PrefrenceManager.getPrefShgCode(),selectedShgCode,context);
                PreferenceFactory.getInstance().saveSharedPrefrecesData(PrefrenceManager.getPrefEntityCode(),selectedEntityCode,context);
               AppUtils.getInstance().showLog("shgDetailsList.size"+ shgDetailsList.size(), ShgDetailsReceiptsAdapter.class);
               AppUtils.getInstance().makeIntent(context, GrandRecieptActivity.class,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shgDetailsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ShgEntityDataModel> filteredList=new ArrayList<>();
            if (constraint.toString().trim().isEmpty()){
                filteredList.addAll(shgDetailsListAll);
            }else {
                 for(ShgEntityDataModel shg:shgDetailsListAll){
                     if (shg.getShgName().toLowerCase().contains(constraint.toString().trim().toLowerCase())){
                         filteredList.add(shg);
                     }
                 }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }
        // run on ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            shgDetailsList.clear();
            shgDetailsList.addAll((Collection<? extends ShgEntityDataModel>) results.values);
            notifyDataSetChanged();

        }
    };

    public class ShgDetailsHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.LocationTv)
        TextView LocationTv;

        @BindView(R.id.shgDetailsTV)
        TextView shgDetailsTV;

        public ShgDetailsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
