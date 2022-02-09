package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterCutoffBankCompnyEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String bank_company_name;
    public String bank_company_value;
}
