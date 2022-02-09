package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterCutoffFixedDepositEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String fixed_deposit_inv_name;
    public String fixed_deposit_inv_value;
}
