package com.nrlm.cbo.view.adapters.receiptAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.AddedReceiptEntityDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddedReceiptAdapter extends RecyclerView.Adapter<AddedReceiptAdapter.AddedReceiptViewholder> {
    private Context context;
    private String receiptTypeId, receiptSubTypeId,receiptSubToSubTypeId;
    List<AddedReceiptEntityDataModel> addedReceiptEntityDataModelList;

    public AddedReceiptAdapter(Context context, String receiptTypeId, List<AddedReceiptEntityDataModel> addedReceiptEntityDataModelList) {
        this.context = context;
        this.receiptTypeId = receiptTypeId;
        this.addedReceiptEntityDataModelList = addedReceiptEntityDataModelList;
    }

    public AddedReceiptAdapter(Context context, String receiptTypeId, String receiptSubTypeId
            , List<AddedReceiptEntityDataModel> addedReceiptEntityDataModelList) {
        this.context = context;
        this.receiptTypeId = receiptTypeId;
        this.receiptSubTypeId = receiptSubTypeId;
        this.addedReceiptEntityDataModelList = addedReceiptEntityDataModelList;

    }

    public AddedReceiptAdapter(Context context, String receiptTypeId, String receiptSubTypeId, String receiptSubToSubTypeId
            , List<AddedReceiptEntityDataModel> addedReceiptEntityDataModelList) {
        this.context = context;
        this.receiptTypeId = receiptTypeId;
        this.receiptSubTypeId = receiptSubTypeId;
        this.receiptSubToSubTypeId = receiptSubToSubTypeId;
        this.addedReceiptEntityDataModelList = addedReceiptEntityDataModelList;

    }

    @NonNull
    @Override
    public AddedReceiptViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.added_receipt_item, parent, false);
        return new AddedReceiptViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedReceiptViewholder holder, int position) {

        holder.text_label1.setText(context.getResources().getString(R.string.cashbook_page_no));
        holder.text_data_label1.setText("10");


        switch (receiptTypeId) {
            case "1":
                break;

            case "2":
                holder.text_label1.setText(context.getResources().getString(R.string.cashbook_page_no));
                holder.text_data_label1.setText("10");

                holder.text_label2.setText(context.getResources().getString(R.string.grant_type));
                holder.text_data_label2.setText("Revolving Fund");

                holder.text_label3.setText(context.getResources().getString(R.string.grant_source));
                holder.text_data_label3.setText("Grant Source");

                holder.text_label4.setText(context.getResources().getString(R.string.grant_number));
                holder.text_data_label4.setText("Grant Number");

                holder.text_label5.setText(context.getResources().getString(R.string.grant_agency_name));
                holder.text_data_label5.setText("Grant Agency Name");

                holder.text_label6.setText(context.getResources().getString(R.string.amount_received));
                holder.text_data_label6.setText("Amount Received");

                holder.text_label7.setText(context.getResources().getString(R.string.date_received));
                holder.text_data_label7.setText(" Date Received");

                holder.text_label8.setText(context.getResources().getString(R.string.transaction_mode));
                holder.text_data_label8.setText(" Transaction Mode");

                break;

            case "3":
                break;

            case "4":
                break;

            case "5":
                break;

            case "6":
                break;

        }

        holder.edit_itemIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItemInfo(position);
            }
        });

        holder.delete_itemIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteItemInfo(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addedReceiptEntityDataModelList.size();
    }

    private void editItemInfo(int itemPosition){
        switch (receiptTypeId) {
            case "1":
                break;

            case "2":
                break;

            case "3":
                break;

            case "4":
                break;

            case "5":
                break;

            case "6":
                break;

        }

    }

    private void deleteItemInfo(int itemPosition){
        switch (receiptTypeId) {
            case "1":
                break;

            case "2":
                Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_LONG).show();
                break;

            case "3":
                break;

            case "4":
                break;

            case "5":
                break;

            case "6":
                break;

        }

    }

    public class AddedReceiptViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.added_receipt_item_CV1)
        CardView added_receipt_item_CV1;

        @BindView(R.id.added_receipt_item_CV2)
        CardView added_receipt_item_CV2;

        @BindView(R.id.added_receipt_item_CV3)
        CardView added_receipt_item_CV3;

        @BindView(R.id.added_receipt_item_CV4)
        CardView added_receipt_item_CV4;

        @BindView(R.id.added_receipt_item_CV5)
        CardView added_receipt_item_CV5;

        @BindView(R.id.added_receipt_item_CV6)
        CardView added_receipt_item_CV6;

        @BindView(R.id.added_receipt_item_CV7)
        CardView added_receipt_item_CV7;

        @BindView(R.id.added_receipt_item_CV8)
        CardView added_receipt_item_CV8;

        @BindView(R.id.added_receipt_item_CV9)
        CardView added_receipt_item_CV9;

        @BindView(R.id.added_receipt_item_CV10)
        CardView added_receipt_item_CV10;


        @BindView(R.id.text_label1)
        TextView text_label1;

        @BindView(R.id.text_label2)
        TextView text_label2;

        @BindView(R.id.text_label3)
        TextView text_label3;

        @BindView(R.id.text_label4)
        TextView text_label4;

        @BindView(R.id.text_label5)
        TextView text_label5;

        @BindView(R.id.text_label6)
        TextView text_label6;

        @BindView(R.id.text_label7)
        TextView text_label7;

        @BindView(R.id.text_label8)
        TextView text_label8;

        @BindView(R.id.text_label9)
        TextView text_label9;

        @BindView(R.id.text_label10)
        TextView text_label10;


        @BindView(R.id.text_data_label1)
        TextView text_data_label1;

        @BindView(R.id.text_data_label2)
        TextView text_data_label2;

        @BindView(R.id.text_data_label3)
        TextView text_data_label3;

        @BindView(R.id.text_data_label4)
        TextView text_data_label4;

        @BindView(R.id.text_data_label5)
        TextView text_data_label5;

        @BindView(R.id.text_data_label6)
        TextView text_data_label6;

        @BindView(R.id.text_data_label7)
        TextView text_data_label7;

        @BindView(R.id.text_data_label8)
        TextView text_data_label8;

        @BindView(R.id.text_data_label9)
        TextView text_data_label9;

        @BindView(R.id.text_data_label10)
        TextView text_data_label10;

        @BindView(R.id.delete_itemIV)
        ImageView delete_itemIV;

        @BindView(R.id.edit_itemIV)
        ImageView edit_itemIV;


        public AddedReceiptViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
