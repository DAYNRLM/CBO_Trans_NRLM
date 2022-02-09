package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterPaymentAgencyTypeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String payment_agency_id;
    public String payment_agency_type;
    public String payment_type_id;
    public String payment_sub_type_id;
}
