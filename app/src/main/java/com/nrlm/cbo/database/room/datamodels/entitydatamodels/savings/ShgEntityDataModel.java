package com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings;

public class ShgEntityDataModel {

    private String shgCode,shgName,entityCode,verificationStatus,settingStatus,cuttOffStatus;

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

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(String settingStatus) {
        this.settingStatus = settingStatus;
    }

    public String getCuttOffStatus() {
        return cuttOffStatus;
    }

    public void setCuttOffStatus(String cuttOffStatus) {
        this.cuttOffStatus = cuttOffStatus;
    }
}
