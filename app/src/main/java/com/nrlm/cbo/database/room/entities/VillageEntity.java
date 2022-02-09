package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VillageEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String village_code;
    public String village_name;
    public String gp_code;
    public String block_code;

}
