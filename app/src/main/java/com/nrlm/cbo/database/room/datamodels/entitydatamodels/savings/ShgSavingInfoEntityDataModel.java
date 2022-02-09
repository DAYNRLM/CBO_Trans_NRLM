package com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings;

import androidx.room.Ignore;

public class ShgSavingInfoEntityDataModel {

    private String shgName,shgCode, lastMeetingDate,lastMeetingNo, entityCode;
    @Ignore
    private String cashBookNo;
    @Ignore
    private String cashPageBookNo;

    public String getShgCode() {
        return shgCode;
    }

    public void setShgCode(String shgCode) {
        this.shgCode = shgCode;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getShgName() {
        return shgName;
    }

    public void setShgName(String shgName) {
        this.shgName = shgName;
    }

    public String getLastMeetingDate() {
        return lastMeetingDate;
    }

    public void setLastMeetingDate(String lastMeetingDate) {
        this.lastMeetingDate = lastMeetingDate;
    }

    public String getLastMeetingNo() {
        return lastMeetingNo;
    }

    public void setLastMeetingNo(String lastMeetingNo) {
        this.lastMeetingNo = lastMeetingNo;
    }

    public String getCashBookNo() {
        return cashBookNo;
    }

    public void setCashBookNo(String cashBookNo) {
        this.cashBookNo = cashBookNo;
    }

    public String getCashPageBookNo() {
        return cashPageBookNo;
    }

    public void setCashPageBookNo(String cashPageBookNo) {
        this.cashPageBookNo = cashPageBookNo;
    }
}
