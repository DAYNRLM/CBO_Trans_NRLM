package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterTransShgPaymentEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String payments_type_id;
    public String payment_discription;
}
