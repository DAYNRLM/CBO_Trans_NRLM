package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nrlm.cbo.R;
import com.nrlm.cbo.database.room.entity.AddShgLoan;

import java.util.List;

public class RunningLoanAdapter extends BaseAdapter {
    TextView name, code;

    public RunningLoanAdapter(Context context, List<AddShgLoan> addShgLoanList) {
        super(context);
        dataList = addShgLoanList;
        layout_id = R.layout.shg_running_loan_custom_layout;
    }

    @Override
    public View getView(View view) {
        name = view.findViewById(R.id.name);
        code = view.findViewById(R.id.code);
        return view;
    }

    @Override
    public void onBindViewHold(int position, Object itemView) {
        AddShgLoan ad = (AddShgLoan) itemView;
        name.setText(ad.getShgName());
        code.setText(ad.getLoanAmount());
    }

    @Override
    public void onBindViewWithHolder(int position, Object itemView, @NonNull MyViewHolder viewHolder) {

    }
}
