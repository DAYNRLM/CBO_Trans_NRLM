package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterCutoffCompanyNameEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String company_name;
    public String company_name_id;
    public String syncStatus;
}
