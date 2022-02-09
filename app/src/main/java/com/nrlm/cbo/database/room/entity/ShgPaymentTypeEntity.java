package com.nrlm.cbo.database.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity (tableName = "shg_payment_type",primaryKeys = "payment_type_id")
public class ShgPaymentTypeEntity {

    @NonNull
    @ColumnInfo(name = "payment_type_id")
    private String paymentTypeId;

    @NonNull
    @ColumnInfo(name = "payment_type")
    private String paymentType;

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(@NonNull String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NonNull String paymentType) {
        this.paymentType = paymentType;
    }
}
