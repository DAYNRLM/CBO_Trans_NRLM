package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterCuttOffLoanSourceEntity {
    @PrimaryKey(autoGenerate = true)
   public int id;
    public  String loan_source_name;
    public  String loan_source_value;



}
