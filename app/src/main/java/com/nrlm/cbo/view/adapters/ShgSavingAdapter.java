package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nrlm.cbo.R;
import com.nrlm.cbo.model.dummyModels.SavingPojo;

import java.util.List;

public class ShgSavingAdapter extends BaseAdapter {
    TextView savingTypeTv, amountTv;


    public ShgSavingAdapter(Context context, List<SavingPojo> savingDummyList) {
        super(context);
        dataList = savingDummyList;
        layout_id = R.layout.shg_saving_custom_layout;
    }

    @Override
    public View getView(View view) {
        savingTypeTv = view.findViewById(R.id.savingTypeTv);
        amountTv = view.findViewById(R.id.amountTv);
        return view;
    }

    @Override
    public void onBindViewHold(int position, Object itemView) {
        SavingPojo ad = (SavingPojo) itemView;
        savingTypeTv.setText(ad.getSavingName());
        amountTv.setText(ad.getSavingAmount());
    }

    @Override
    public void onBindViewWithHolder(int position, Object itemView, @NonNull MyViewHolder viewHolder) {

    }
}
