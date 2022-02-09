package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterIsRfReturendEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String rf_to_be_return_name;
    public String rf_to_be_return_value;
}
