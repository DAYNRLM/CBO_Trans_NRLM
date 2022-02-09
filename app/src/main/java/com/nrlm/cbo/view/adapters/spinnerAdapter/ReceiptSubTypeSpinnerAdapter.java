package com.nrlm.cbo.view.adapters.spinnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nrlm.cbo.R;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptSubTypeLoanSourceEntity;

import java.util.List;

public class ReceiptSubTypeSpinnerAdapter extends ArrayAdapter<MasterShgReceiptSubTypeLoanSourceEntity> {

    public ReceiptSubTypeSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<MasterShgReceiptSubTypeLoanSourceEntity> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

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
        MasterShgReceiptSubTypeLoanSourceEntity currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.sub_receipt_description);
        }
        return convertView;
    }


}
