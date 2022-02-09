package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class ShgDataEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String promotedBy;
    public String shgCode;
    public String bankCode;
    public String lastMeetingNo;
    public String shgType;
    public String cuttOffStatus;
    public String verificationStatus;
    public String branchCode;
    public String settingStatus;
    public String entityCode;
    public String lastMeetingDate;
    public String groupFormationDate;
    public String shgName;
    public String accOpeningDate;
    public String bankAccNo;

    public String promoter_name;
    public String loan_running;
    public String shg_category;
    public String shg_active;
    public String cashbook_no;
    public String cashbook_page;
    public String meeting_frequency;


}
