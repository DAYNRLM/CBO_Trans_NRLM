package com.nrlm.cbo.view.adapters.spinnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;

import java.util.List;

public class ReceiptTypeSpinnerAdapter extends ArrayAdapter<MasterShgReceiptEntity> {


    public ReceiptTypeSpinnerAdapter(@NonNull Context context, @NonNull  List<MasterShgReceiptEntity> masterShgReceiptEntityList) {
        super(context, 0, masterShgReceiptEntityList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Nullable
    private View initView(int position, @Nullable View convertView,
                          ViewGroup parent) {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_textview, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.spi);
        MasterShgReceiptEntity currentItem = (MasterShgReceiptEntity) getItem(position);
        String receiptTypeName=currentItem.receipt_description;
        AppUtils.getInstance().showLog("receiptTypeNameeeeeeeeeeeeeeeee"+receiptTypeName,ReceiptTypeSpinnerAdapter.class);

        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(""+receiptTypeName);
        }
        return convertView;
    }

}
