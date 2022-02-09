package com.nrlm.cbo.database.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "shg_member")
public class ShgMember {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name="shg_code")
    public String shgCode;
    @ColumnInfo(name = "shg_member_code")
    public String shgMemberCode;
    @ColumnInfo(name = "shg_member_name")
    public String shgMemberName;

    @ColumnInfo(name = "member_verify_status")
    public String memberVerifyStatus;

    public String getMemberVerifyStatus() {
        return memberVerifyStatus;
    }

    public void setMemberVerifyStatus(String memberVerifyStatus) {
        this.memberVerifyStatus = memberVerifyStatus;
    }

    public String getShgCode() {
        return shgCode;
    }

    public void setShgCode(String shgCode) {
        this.shgCode = shgCode;
    }

    public String getShgMemberCode() {
        return shgMemberCode;
    }

    public void setShgMemberCode(String shgMemberCode) {
        this.shgMemberCode = shgMemberCode;
    }

    public String getShgMemberName() {
        return shgMemberName;
    }

    public void setShgMemberName(String shgMemberName) {
        this.shgMemberName = shgMemberName;
    }




}
