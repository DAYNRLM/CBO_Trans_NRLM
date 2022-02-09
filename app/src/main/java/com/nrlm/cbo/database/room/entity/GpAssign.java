package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gpAssign")
public class GpAssign {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "block_code")
    public String blockCode;

    @ColumnInfo(name = "gp_code")
    public String gpCode;

    @ColumnInfo(name = "gp_name")
    public String gpName;

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

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }
}
