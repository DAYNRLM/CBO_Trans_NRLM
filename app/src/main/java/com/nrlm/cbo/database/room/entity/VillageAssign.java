package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "villageAssign")
public class VillageAssign {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "block_code")
    public String blockCode;

    @ColumnInfo(name = "gp_code")
    public String gpCode;

    @ColumnInfo(name = "vilage_code")
    public String vilageCode;

    @ColumnInfo(name = "village_name")
    public String vilageName;


    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getGpCode() {
        return gpCode;
    }

    public void setGpCode(String gpCode) {
        this.gpCode = gpCode;
    }

    public String getVilageCode() {
        return vilageCode;
    }

    public void setVilageCode(String vilageCode) {
        this.vilageCode = vilageCode;
    }

    public String getVilageName() {
        return vilageName;
    }

    public void setVilageName(String vilageName) {
        this.vilageName = vilageName;
    }
}
