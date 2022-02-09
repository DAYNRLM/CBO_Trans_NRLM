package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shg")
public class Shg {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "village_code")
    public String villageCode;
    @ColumnInfo(name = "shg_code")
    public String shgCode;
    @ColumnInfo(name = "shg_name")
    public String shgName;

    @ColumnInfo(name = "shg_verify_status")
    public String shgVerifyStatus;

    public String getShgVerifyStatus() {
        return shgVerifyStatus;
    }

    public void setShgVerifyStatus(String shgVerifyStatus) {
        this.shgVerifyStatus = shgVerifyStatus;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getShgCode() {
        return shgCode;
    }

    public void setShgCode(String shgCode) {
        this.shgCode = shgCode;
    }

    public String getShgName() {
        return shgName;
    }

    public void setShgName(String shgName) {
        this.shgName = shgName;
    }


}
