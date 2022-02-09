package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterTransShgPaymentSubTypeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String payments_type_id;
    public String payment_sub_discription;
    public String payments_Sub_type_id;

}
