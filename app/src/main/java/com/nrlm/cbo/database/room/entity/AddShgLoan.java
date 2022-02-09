package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "addShgRunningLoan")
public class AddShgLoan {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "loan_amount")
    public String loanAmount;

    @ColumnInfo(name = "shg_name")
    public String shgName;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getShgName() {
        return shgName;
    }

    public void setShgName(String shgName) {
        this.shgName = shgName;
    }
}
