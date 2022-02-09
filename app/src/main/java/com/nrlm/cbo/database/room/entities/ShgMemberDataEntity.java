package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class ShgMemberDataEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String shgCode;
    public String leader;
    public String belongingName;
    public String shgMemberCode;
    public String aplbpl;
    public String gender;
    public String disability;
    public String birthDate;
    public String pipCategory;
    public String memberName;
    public String entityCode;
    public String religon;
    public String socialCategory;


    public String aadhaar;
    public String mobile_number;
    public String active_details;
    public String mem_bank_code;
    public String mem_branch_code;
    public String ac_no;
    public String aadhar_seeded_sb_ac;
    public String adhar_valid;
    public String book_keeper_member;
    public String education;
    public String other_education;
    public String enroll_in_pmjy;
    public String enroll_in_pmsby;
    public String enroll_in_lic;
    public String enroll_in_hic;

}
