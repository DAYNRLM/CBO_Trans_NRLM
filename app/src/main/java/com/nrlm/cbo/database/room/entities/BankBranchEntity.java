package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BankBranchEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String bank_code, branch_code, branch_name, ifsc_code;
}
