package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "blockAssign")
public class BlockAssign {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "block_name")
    public String blockName;

    @ColumnInfo(name = "block_code")
    public String blockCode;

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }
}
