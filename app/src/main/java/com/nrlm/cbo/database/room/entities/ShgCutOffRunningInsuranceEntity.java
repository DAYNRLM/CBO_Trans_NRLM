package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShgCutOffRunningInsuranceEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String insuranceId;
    public String shgCode;
    public String policyNumber;
    public String policyName;
    public String policyPremiumAmount;
    public String policyRiskCover;
    public String policyStartDate;
    public String policyEndDate;
    public String insuranceSyncStatus;
}
