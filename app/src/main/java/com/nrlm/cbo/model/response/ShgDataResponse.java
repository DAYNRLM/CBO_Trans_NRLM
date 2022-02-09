package com.nrlm.cbo.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShgDataResponse implements Serializable {

    public static ShgDataResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, ShgDataResponse.class);
    }

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("data")
    @Expose
    private List<ShgData> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<ShgData> getData() {
        return data;
    }

    public void setData(List<ShgData> data) {
        this.data = data;
    }

    public class Error {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("message")
        @Expose
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public class ShgData {

        @SerializedName("shg_code")
        @Expose
        private String shgCode;
        @SerializedName("promoted_by")
        @Expose
        private String promotedBy;
        @SerializedName("promoter_name")
        @Expose
        private String promoterName;
        @SerializedName("bank_code")
        @Expose
        private Object bankCode;
        @SerializedName("bank_branch_code")
        @Expose
        private Object bankBranchCode;
        @SerializedName("bank_account_no")
        @Expose
        private Object bankAccountNo;
        @SerializedName("op_date_of_account")
        @Expose
        private Object opDateOfAccount;
        @SerializedName("loan_running")
        @Expose
        private Integer loanRunning;
        @SerializedName("shg_category")
        @Expose
        private String shgCategory;
        @SerializedName("shg_active")
        @Expose
        private String shgActive;
        @SerializedName("shg_type")
        @Expose
        private String shgType;
        @SerializedName("entity_code")
        @Expose
        private String entityCode;
        @SerializedName("shg_name")
        @Expose
        private String shgName;
        @SerializedName("last_meeting_date")
        @Expose
        private String lastMeetingDate;
        @SerializedName("last_meeting_no")
        @Expose
        private String lastMeetingNo;
        @SerializedName("verification_status")
        @Expose
        private String verificationStatus;
        @SerializedName("setting_status")
        @Expose
        private String settingStatus;
        @SerializedName("cutt_off_status")
        @Expose
        private String cuttOffStatus;
        @SerializedName("cashbook_no")
        @Expose
        private String cashbookNo;
        @SerializedName("cashbook_page")
        @Expose
        private String cashbookPage;
        @SerializedName("group_formation_date")
        @Expose
        private String groupFormationDate;
        @SerializedName("meeting_frequency")
        @Expose
        private String meetingFrequency;

        public String getShgCode() {
            return shgCode;
        }

        public void setShgCode(String shgCode) {
            this.shgCode = shgCode;
        }

        public String getPromotedBy() {
            return promotedBy;
        }

        public void setPromotedBy(String promotedBy) {
            this.promotedBy = promotedBy;
        }

        public String getPromoterName() {
            return promoterName;
        }

        public void setPromoterName(String promoterName) {
            this.promoterName = promoterName;
        }

        public Object getBankCode() {
            return bankCode;
        }

        public void setBankCode(Object bankCode) {
            this.bankCode = bankCode;
        }

        public Object getBankBranchCode() {
            return bankBranchCode;
        }

        public void setBankBranchCode(Object bankBranchCode) {
            this.bankBranchCode = bankBranchCode;
        }

        public Object getBankAccountNo() {
            return bankAccountNo;
        }

        public void setBankAccountNo(Object bankAccountNo) {
            this.bankAccountNo = bankAccountNo;
        }

        public Object getOpDateOfAccount() {
            return opDateOfAccount;
        }

        public void setOpDateOfAccount(Object opDateOfAccount) {
            this.opDateOfAccount = opDateOfAccount;
        }

        public Integer getLoanRunning() {
            return loanRunning;
        }

        public void setLoanRunning(Integer loanRunning) {
            this.loanRunning = loanRunning;
        }

        public String getShgCategory() {
            return shgCategory;
        }

        public void setShgCategory(String shgCategory) {
            this.shgCategory = shgCategory;
        }

        public String getShgActive() {
            return shgActive;
        }

        public void setShgActive(String shgActive) {
            this.shgActive = shgActive;
        }

        public String getShgType() {
            return shgType;
        }

        public void setShgType(String shgType) {
            this.shgType = shgType;
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

        public String getCashbookNo() {
            return cashbookNo;
        }

        public void setCashbookNo(String cashbookNo) {
            this.cashbookNo = cashbookNo;
        }

        public String getCashbookPage() {
            return cashbookPage;
        }

        public void setCashbookPage(String cashbookPage) {
            this.cashbookPage = cashbookPage;
        }

        public String getGroupFormationDate() {
            return groupFormationDate;
        }

        public void setGroupFormationDate(String groupFormationDate) {
            this.groupFormationDate = groupFormationDate;
        }

        public String getMeetingFrequency() {
            return meetingFrequency;
        }

        public void setMeetingFrequency(String meetingFrequency) {
            this.meetingFrequency = meetingFrequency;
        }

    }


   /* private Data data;
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable {

        private List<ShgDetail> shg_details = null;

        public List<ShgDetail> getShgDetails() {
            return shg_details;
        }

        public void setShgDetails(List<ShgDetail> shgDetails) {
            this.shg_details = shgDetails;
        }

    }

    public class ShgDetail implements Serializable {

        private String promoted_by;
        private String shg_code;
        private String bank_code;
        private String last_meeting_no;
        private String shg_type;
        private String cutt_off_status;
        private String verification_status;
        private String branch_code;
        private String setting_status;
        private String entity_code;
        private String last_meeting_date;
        private String group_formation_date;
        private String shg_name;
        private String acc_opening_date;
        private String bank_acc_no;


        public String getPromoted_by() {
            return promoted_by;
        }

        public void setPromoted_by(String promoted_by) {
            this.promoted_by = promoted_by;
        }

        public String getShg_code() {
            return shg_code;
        }

        public void setShg_code(String shg_code) {
            this.shg_code = shg_code;
        }

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public String getLast_meeting_no() {
            return last_meeting_no;
        }

        public void setLast_meeting_no(String last_meeting_no) {
            this.last_meeting_no = last_meeting_no;
        }

        public String getShg_type() {
            return shg_type;
        }

        public void setShg_type(String shg_type) {
            this.shg_type = shg_type;
        }

        public String getCutt_off_status() {
            return cutt_off_status;
        }

        public void setCutt_off_status(String cutt_off_status) {
            this.cutt_off_status = cutt_off_status;
        }

        public String getVerification_status() {
            return verification_status;
        }

        public void setVerification_status(String verification_status) {
            this.verification_status = verification_status;
        }

        public String getBranch_code() {
            return branch_code;
        }

        public void setBranch_code(String branch_code) {
            this.branch_code = branch_code;
        }

        public String getSetting_status() {
            return setting_status;
        }

        public void setSetting_status(String setting_status) {
            this.setting_status = setting_status;
        }

        public String getEntity_code() {
            return entity_code;
        }

        public void setEntity_code(String entity_code) {
            this.entity_code = entity_code;
        }

        public String getLast_meeting_date() {
            return last_meeting_date;
        }

        public void setLast_meeting_date(String last_meeting_date) {
            this.last_meeting_date = last_meeting_date;
        }

        public String getGroup_formation_date() {
            return group_formation_date;
        }

        public void setGroup_formation_date(String group_formation_date) {
            this.group_formation_date = group_formation_date;
        }

        public String getShg_name() {
            return shg_name;
        }

        public void setShg_name(String shg_name) {
            this.shg_name = shg_name;
        }

        public String getAcc_opening_date() {
            return acc_opening_date;
        }

        public void setAcc_opening_date(String acc_opening_date) {
            this.acc_opening_date = acc_opening_date;
        }

        public String getBank_acc_no() {
            return bank_acc_no;
        }

        public void setBank_acc_no(String bank_acc_no) {
            this.bank_acc_no = bank_acc_no;
        }
    }*/




}