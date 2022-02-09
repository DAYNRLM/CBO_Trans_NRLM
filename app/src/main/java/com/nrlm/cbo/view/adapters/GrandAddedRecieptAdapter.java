package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.database.room.entity.Shg;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrandAddedRecieptAdapter extends RecyclerView.Adapter<GrandAddedRecieptAdapter.GrandAddedRecieptHolder> {
    private Context context;
    private List<?> receiptTypeList;
    private Class classType;

    public GrandAddedRecieptAdapter(Context context,List<?> receiptTypeList,Class classType){
        this.context=context;
        this.receiptTypeList=receiptTypeList;
        this.classType=classType;
    }

    @NonNull
    @Override
    public GrandAddedRecieptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.added_receipts_rv_item,parent,false);
        return new GrandAddedRecieptHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrandAddedRecieptHolder holder, int position) {
        if (classType== Shg.class){
            List<Shg> shgList = (List<Shg>) receiptTypeList;
            holder.item_view_LL1.setVisibility(View.VISIBLE);
            holder.item_view_LL2.setVisibility(View.VISIBLE);
            holder.item_view_LL3.setVisibility(View.VISIBLE);
            holder.data_label1.setText(shgList.get(position).getShgName());
            holder.data_label2.setText(shgList.get(position).getVillageCode());
            holder.data_label3.setText(shgList.get(position).getShgCode());
        }
    }

    @Override
    public int getItemCount() {
        return receiptTypeList.size();
    }

    public class GrandAddedRecieptHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_view_LL1)
        LinearLayout item_view_LL1;

        @BindView(R.id.item_view_LL2)
        LinearLayout item_view_LL2;

        @BindView(R.id.item_view_LL3)
        LinearLayout item_view_LL3;

        @BindView(R.id.item_view_LL4)
        LinearLayout item_view_LL4;

        @BindView(R.id.item_view_LL5)
        LinearLayout item_view_LL5;

        @BindView(R.id.item_view_LL6)
        LinearLayout item_view_LL6;

        @BindView(R.id.name_label1)
        TextView name_label1;

        @BindView(R.id.name_label2)
        TextView name_label2;

        @BindView(R.id.name_label3)
        TextView name_label3;

        @BindView(R.id.name_label4)
        TextView name_label4;

        @BindView(R.id.name_label5)
        TextView name_label5;

        @BindView(R.id.name_label6)
        TextView name_label6;

        @BindView(R.id.data_label1)
        TextView data_label1;

        @BindView(R.id.data_label2)
        TextView data_label2;

        @BindView(R.id.data_label3)
        TextView data_label3;

        @BindView(R.id.data_label4)
        TextView data_label4;

        @BindView(R.id.data_label5)
        TextView data_label5;

        @BindView(R.id.data_label6)
        TextView data_label6;

        @BindView(R.id.delete_added_receiptIV)
        ImageView delete_added_receiptIV;

        public GrandAddedRecieptHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
