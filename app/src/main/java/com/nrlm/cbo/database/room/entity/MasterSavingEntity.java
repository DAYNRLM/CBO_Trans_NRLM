package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "master_saving")
public class MasterSavingEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "saving_type_name")
    public String savingType;

    @ColumnInfo(name = "saving_type_id")
    public String savingTypeId;

    public String getSavingType() {
        return savingType;
    }

    public void setSavingType(String savingType) {
        this.savingType = savingType;
    }

    public String getSavingTypeId() {
        return savingTypeId;
    }

    public void setSavingTypeId(String savingTypeId) {
        this.savingTypeId = savingTypeId;
    }
}
