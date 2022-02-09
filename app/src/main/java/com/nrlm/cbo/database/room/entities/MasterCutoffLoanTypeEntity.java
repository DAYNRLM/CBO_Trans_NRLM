package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterCutoffLoanTypeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public  String loan_type;
    public  String loan_type_id;
}
