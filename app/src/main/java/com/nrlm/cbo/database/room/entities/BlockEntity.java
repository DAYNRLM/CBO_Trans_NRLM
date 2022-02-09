package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BlockEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String block_name;
    public String block_code;
}
