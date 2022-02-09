package com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings;

import androidx.room.ColumnInfo;

public class MemberInfoAndSavingsEntityDataModel {
    @ColumnInfo(name = "memberName")
    private String memberName;
    @ColumnInfo(name = "shgMemberCode")
    private String shgMemberCode;
    @ColumnInfo(name = "belongingName")
    private String belongingName;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBelongingName() {
        return belongingName;
    }

    public void setBelongingName(String belongingName) {
        this.belongingName = belongingName;
    }

    public String getShgMemberCode() {
        return shgMemberCode;
    }

    public void setShgMemberCode(String shgMemberCode) {
        this.shgMemberCode = shgMemberCode;
    }
}
