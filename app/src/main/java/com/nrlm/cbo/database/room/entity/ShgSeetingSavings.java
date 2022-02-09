package com.nrlm.cbo.database.room.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shg_seeting")
public class ShgSeetingSavings {

    @PrimaryKey(autoGenerate = true)
    public int savingid;

    @ColumnInfo(name="saving_shg_code")
    public String savingShgCode;

    @ColumnInfo(name="saving_shg_type")
    public String savingType;

    @ColumnInfo(name="saving_type_id")
    public String savingTypeId;

    @ColumnInfo(name="saving_amount")
    public String savingAmount;

    @ColumnInfo(name="sav_amount_roi")
    public String savingAmountROI;

    public String getSavingShgCode() {
        return savingShgCode;
    }

    public void setSavingShgCode(String savingShgCode) {
        this.savingShgCode = savingShgCode;
    }

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

    public String getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(String savingAmount) {
        this.savingAmount = savingAmount;
    }

    public String getSavingAmountROI() {
        return savingAmountROI;
    }

    public void setSavingAmountROI(String savingAmountROI) {
        this.savingAmountROI = savingAmountROI;
    }
}
