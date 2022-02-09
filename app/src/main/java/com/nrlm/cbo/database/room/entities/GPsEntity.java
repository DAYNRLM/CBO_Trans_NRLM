package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GPsEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String gp_code;
    public String gp_name;
    public String block_code;
}
