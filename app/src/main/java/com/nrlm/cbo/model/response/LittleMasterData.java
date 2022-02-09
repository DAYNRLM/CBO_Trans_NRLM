package com.nrlm.cbo.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LittleMasterData implements Serializable {

    public static LittleMasterData jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, LittleMasterData.class);
    }

    @SerializedName("shg_member_cut_off")
    @Expose
    private List<ShgMemberCutOff> shgMemberCutOff = null;
    @SerializedName("trans_shg_receipt")
    @Expose
    private List<TransShgReceipt> transShgReceipt = null;
    @SerializedName("shg_setting")
    @Expose
    private List<ShgSetting> shgSetting = null;
    @SerializedName("shg_cut_off")
    @Expose
    private List<ShgCutOff> shgCutOff = null;
    @SerializedName("trans_shg_payment")
    @Expose
    private List<TransShgPayment> transShgPayment = null;

    public List<ShgMemberCutOff> getShgMemberCutOff() {
        return shgMemberCutOff;
    }

    public void setShgMemberCutOff(List<ShgMemberCutOff> shgMemberCutOff) {
        this.shgMemberCutOff = shgMemberCutOff;
    }

    public List<TransShgReceipt> getTransShgReceipt() {
        return transShgReceipt;
    }

    public void setTransShgReceipt(List<TransShgReceipt> transShgReceipt) {
        this.transShgReceipt = transShgReceipt;
    }

    public List<ShgSetting> getShgSetting() {
        return shgSetting;
    }

    public void setShgSetting(List<ShgSetting> shgSetting) {
        this.shgSetting = shgSetting;
    }

    public List<ShgCutOff> getShgCutOff() {
        return shgCutOff;
    }

    public void setShgCutOff(List<ShgCutOff> shgCutOff) {
        this.shgCutOff = shgCutOff;
    }

    public List<TransShgPayment> getTransShgPayment() {
        return transShgPayment;
    }

    public void setTransShgPayment(List<TransShgPayment> transShgPayment) {
        this.transShgPayment = transShgPayment;
    }


    public class ShgMemberCutOff implements Serializable {

        @SerializedName("mem_saving")
        @Expose
        private List<MemSaving> memSaving = null;
        @SerializedName("loan_source")
        @Expose
        private List<LoanSource> loanSource = null;
        @SerializedName("mem_purpose")
        @Expose
        private List<MemPurpose> memPurpose = null;

        public List<MemSaving> getMemSaving() {
            return memSaving;
        }

        public void setMemSaving(List<MemSaving> memSaving) {
            this.memSaving = memSaving;
        }

        public List<LoanSource> getLoanSource() {
            return loanSource;
        }

        public void setLoanSource(List<LoanSource> loanSource) {
            this.loanSource = loanSource;
        }

        public List<MemPurpose> getMemPurpose() {
            return memPurpose;
        }

        public void setMemPurpose(List<MemPurpose> memPurpose) {
            this.memPurpose = memPurpose;
        }

    }

    public class ShgSetting implements Serializable {

        @SerializedName("shg_activity_category")
        @Expose
        private List<ShgActivityCategory> shgActivityCategory = null;
        @SerializedName("meetingfreq")
        @Expose
        private List<Meetingfreq> meetingfreq = null;
        @SerializedName("saving_from_member")
        @Expose
        private List<SavingFromMember> savingFromMember = null;
        @SerializedName("isrfreturnto")
        @Expose
        private List<Isrfreturnto> isrfreturnto = null;
        @SerializedName("member_relation_nominee")
        @Expose
        private List<MemberRelationNominee> memberRelationNominee = null;

        public List<ShgActivityCategory> getShgActivityCategory() {
            return shgActivityCategory;
        }

        public void setShgActivityCategory(List<ShgActivityCategory> shgActivityCategory) {
            this.shgActivityCategory = shgActivityCategory;
        }

        public List<Meetingfreq> getMeetingfreq() {
            return meetingfreq;
        }

        public void setMeetingfreq(List<Meetingfreq> meetingfreq) {
            this.meetingfreq = meetingfreq;
        }

        public List<SavingFromMember> getSavingFromMember() {
            return savingFromMember;
        }

        public void setSavingFromMember(List<SavingFromMember> savingFromMember) {
            this.savingFromMember = savingFromMember;
        }

        public List<Isrfreturnto> getIsrfreturnto() {
            return isrfreturnto;
        }

        public void setIsrfreturnto(List<Isrfreturnto> isrfreturnto) {
            this.isrfreturnto = isrfreturnto;
        }

        public List<MemberRelationNominee> getMemberRelationNominee() {
            return memberRelationNominee;
        }

        public void setMemberRelationNominee(List<MemberRelationNominee> memberRelationNominee) {
            this.memberRelationNominee = memberRelationNominee;
        }

    }

    public class TransShgReceipt implements Serializable {

        @SerializedName("receipt_description")
        @Expose
        private String receiptDescription;
        @SerializedName("receipt_id")
        @Expose
        private String receiptId;
        @SerializedName("shg_receipt_sub_type_loan_source")
        @Expose
        private List<ShgReceiptSubTypeLoanSource> shgReceiptSubTypeLoanSource = null;

        public String getReceiptDescription() {
            return receiptDescription;
        }

        public void setReceiptDescription(String receiptDescription) {
            this.receiptDescription = receiptDescription;
        }

        public String getReceiptId() {
            return receiptId;
        }

        public void setReceiptId(String receiptId) {
            this.receiptId = receiptId;
        }

        public List<ShgReceiptSubTypeLoanSource> getShgReceiptSubTypeLoanSource() {
            return shgReceiptSubTypeLoanSource;
        }

        public void setShgReceiptSubTypeLoanSource(List<ShgReceiptSubTypeLoanSource> shgReceiptSubTypeLoanSource) {
            this.shgReceiptSubTypeLoanSource = shgReceiptSubTypeLoanSource;
        }

    }

    public class ShgCutOff implements Serializable {

        @SerializedName("bank_company")
        @Expose
        private List<BankCompany> bankCompany = null;
        @SerializedName("fixed_dep_inv")
        @Expose
        private List<FixedDepInv> fixedDepInv = null;

        public List<BankCompany> getBankCompany() {
            return bankCompany;
        }

        public void setBankCompany(List<BankCompany> bankCompany) {
            this.bankCompany = bankCompany;
        }

        public List<FixedDepInv> getFixedDepInv() {
            return fixedDepInv;
        }

        public void setFixedDepInv(List<FixedDepInv> fixedDepInv) {
            this.fixedDepInv = fixedDepInv;
        }

    }

    public class TransShgPayment implements Serializable {

        @SerializedName("shg_payment_sub_type")
        @Expose
        private List<ShgPaymentSubType> shgPaymentSubType = null;
        @SerializedName("payment_discription")
        @Expose
        private String paymentDiscription;
        @SerializedName("payments_type_id")
        @Expose
        private String paymentsTypeId;

        public List<ShgPaymentSubType> getShgPaymentSubType() {
            return shgPaymentSubType;
        }

        public void setShgPaymentSubType(List<ShgPaymentSubType> shgPaymentSubType) {
            this.shgPaymentSubType = shgPaymentSubType;
        }

        public String getPaymentDiscription() {
            return paymentDiscription;
        }

        public void setPaymentDiscription(String paymentDiscription) {
            this.paymentDiscription = paymentDiscription;
        }

        public String getPaymentsTypeId() {
            return paymentsTypeId;
        }

        public void setPaymentsTypeId(String paymentsTypeId) {
            this.paymentsTypeId = paymentsTypeId;
        }

    }

    public class ShgRecptLoan implements Serializable {

        @SerializedName("shg_recpt_loan_name")
        @Expose
        private String shgRecptLoanName;
        @SerializedName("shg_recpt_loan_value")
        @Expose
        private String shgRecptLoanValue;

        public String getShgRecptLoanName() {
            return shgRecptLoanName;
        }

        public void setShgRecptLoanName(String shgRecptLoanName) {
            this.shgRecptLoanName = shgRecptLoanName;
        }

        public String getShgRecptLoanValue() {
            return shgRecptLoanValue;
        }

        public void setShgRecptLoanValue(String shgRecptLoanValue) {
            this.shgRecptLoanValue = shgRecptLoanValue;
        }

    }

    public class BankCompany implements Serializable {

        @SerializedName("bank_company_name")
        @Expose
        private String bankCompanyName;
        @SerializedName("bank_company_value")
        @Expose
        private String bankCompanyValue;
        @SerializedName("bank_company_inv_value")
        @Expose
        private String bankCompanyInvValue;
        @SerializedName("company")
        @Expose
        private List<Company> company = null;

        public String getBankCompanyName() {
            return bankCompanyName;
        }

        public void setBankCompanyName(String bankCompanyName) {
            this.bankCompanyName = bankCompanyName;
        }

        public String getBankCompanyValue() {
            return bankCompanyValue;
        }

        public void setBankCompanyValue(String bankCompanyValue) {
            this.bankCompanyValue = bankCompanyValue;
        }

        public String getBankCompanyInvValue() {
            return bankCompanyInvValue;
        }

        public void setBankCompanyInvValue(String bankCompanyInvValue) {
            this.bankCompanyInvValue = bankCompanyInvValue;
        }

        public List<Company> getCompany() {
            return company;
        }

        public void setCompany(List<Company> company) {
            this.company = company;
        }

    }

    public class Company implements Serializable {

        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("company_code")
        @Expose
        private String companyCode;
        @SerializedName("company_branch")
        @Expose
        private List<CompanyBranch> companyBranch = null;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public List<CompanyBranch> getCompanyBranch() {
            return companyBranch;
        }

        public void setCompanyBranch(List<CompanyBranch> companyBranch) {
            this.companyBranch = companyBranch;
        }

    }

    public class CompanyBranch implements Serializable{

        @SerializedName("company_branch_name")
        @Expose
        private String companyBranchName;
        @SerializedName("company_branch_code")
        @Expose
        private String companyBranchCode;

        public String getCompanyBranchName() {
            return companyBranchName;
        }

        public void setCompanyBranchName(String companyBranchName) {
            this.companyBranchName = companyBranchName;
        }

        public String getCompanyBranchCode() {
            return companyBranchCode;
        }

        public void setCompanyBranchCode(String companyBranchCode) {
            this.companyBranchCode = companyBranchCode;
        }

    }

    public class FixedDepInv implements Serializable {

        @SerializedName("fixed_deposit_inv_value")
        @Expose
        private String fixedDepositInvValue;
        @SerializedName("fixed_deposit_inv_name")
        @Expose
        private String fixedDepositInvName;

        public String getFixedDepositInvValue() {
            return fixedDepositInvValue;
        }

        public void setFixedDepositInvValue(String fixedDepositInvValue) {
            this.fixedDepositInvValue = fixedDepositInvValue;
        }

        public String getFixedDepositInvName() {
            return fixedDepositInvName;
        }

        public void setFixedDepositInvName(String fixedDepositInvName) {
            this.fixedDepositInvName = fixedDepositInvName;
        }

    }

    public class Isrfreturnto implements Serializable {

        @SerializedName("rftobereturn_name")
        @Expose
        private String rftobereturnName;
        @SerializedName("rftobereturn_value")
        @Expose
        private String rftobereturnValue;

        public String getRftobereturnName() {
            return rftobereturnName;
        }

        public void setRftobereturnName(String rftobereturnName) {
            this.rftobereturnName = rftobereturnName;
        }

        public String getRftobereturnValue() {
            return rftobereturnValue;
        }

        public void setRftobereturnValue(String rftobereturnValue) {
            this.rftobereturnValue = rftobereturnValue;
        }

    }

    public class LoanSource implements Serializable {

        @SerializedName("loan_source_name")
        @Expose
        private String loanSourceName;
        @SerializedName("loan_source_value")
        @Expose
        private String loanSourceValue;

        public String getLoanSourceName() {
            return loanSourceName;
        }

        public void setLoanSourceName(String loanSourceName) {
            this.loanSourceName = loanSourceName;
        }

        public String getLoanSourceValue() {
            return loanSourceValue;
        }

        public void setLoanSourceValue(String loanSourceValue) {
            this.loanSourceValue = loanSourceValue;
        }

    }

    public class Meetingfreq implements Serializable {

        @SerializedName("meetingfrequencyname")
        @Expose
        private String meetingfrequencyname;
        @SerializedName("meetingfrequency")
        @Expose
        private String meetingfrequency;

        public String getMeetingfrequencyname() {
            return meetingfrequencyname;
        }

        public void setMeetingfrequencyname(String meetingfrequencyname) {
            this.meetingfrequencyname = meetingfrequencyname;
        }

        public String getMeetingfrequency() {
            return meetingfrequency;
        }

        public void setMeetingfrequency(String meetingfrequency) {
            this.meetingfrequency = meetingfrequency;
        }

    }

    public class MemPurpose implements Serializable {

        @SerializedName("discription")
        @Expose
        private String discription;
        @SerializedName("purpose_id")
        @Expose
        private String purposeId;

        public String getDiscription() {
            return discription;
        }

        public void setDiscription(String discription) {
            this.discription = discription;
        }

        public String getPurposeId() {
            return purposeId;
        }

        public void setPurposeId(String purposeId) {
            this.purposeId = purposeId;
        }

    }

    public class MemSaving implements Serializable {

        @SerializedName("saving_type_code")
        @Expose
        private String savingTypeCode;
        @SerializedName("saving_type_name")
        @Expose
        private String savingTypeName;

        public String getSavingTypeCode() {
            return savingTypeCode;
        }

        public void setSavingTypeCode(String savingTypeCode) {
            this.savingTypeCode = savingTypeCode;
        }

        public String getSavingTypeName() {
            return savingTypeName;
        }

        public void setSavingTypeName(String savingTypeName) {
            this.savingTypeName = savingTypeName;
        }

    }

    public class MemberRelationNominee implements Serializable {

        @SerializedName("relation_name")
        @Expose
        private String relationName;
        @SerializedName("relation_code")
        @Expose
        private String relationCode;

        public String getRelationName() {
            return relationName;
        }

        public void setRelationName(String relationName) {
            this.relationName = relationName;
        }

        public String getRelationCode() {
            return relationCode;
        }

        public void setRelationCode(String relationCode) {
            this.relationCode = relationCode;
        }

    }

    public class SavingFromMember implements Serializable {

        @SerializedName("saving_type_code")
        @Expose
        private String savingTypeCode;
        @SerializedName("saving_type_name")
        @Expose
        private String savingTypeName;

        public String getSavingTypeCode() {
            return savingTypeCode;
        }

        public void setSavingTypeCode(String savingTypeCode) {
            this.savingTypeCode = savingTypeCode;
        }

        public String getSavingTypeName() {
            return savingTypeName;
        }

        public void setSavingTypeName(String savingTypeName) {
            this.savingTypeName = savingTypeName;
        }

    }

    public class ShgActivityCategory implements Serializable {

        @SerializedName("category_name")
        @Expose
        private String categoryName;
        @SerializedName("category_value")
        @Expose
        private String categoryValue;
        @SerializedName("shg_sub_activity")
        @Expose
        private List<ShgSubActivity> shgSubActivity = null;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryValue() {
            return categoryValue;
        }

        public void setCategoryValue(String categoryValue) {
            this.categoryValue = categoryValue;
        }

        public List<ShgSubActivity> getShgSubActivity() {
            return shgSubActivity;
        }

        public void setShgSubActivity(List<ShgSubActivity> shgSubActivity) {
            this.shgSubActivity = shgSubActivity;
        }

    }

    public class ShgPaymentAgencyType implements Serializable {

        @SerializedName("payment_agency_type")
        @Expose
        private String paymentAgencyType;
        @SerializedName("payment_agency_id")
        @Expose
        private String paymentAgencyId;

        public String getPaymentAgencyType() {
            return paymentAgencyType;
        }

        public void setPaymentAgencyType(String paymentAgencyType) {
            this.paymentAgencyType = paymentAgencyType;
        }

        public String getPaymentAgencyId() {
            return paymentAgencyId;
        }

        public void setPaymentAgencyId(String paymentAgencyId) {
            this.paymentAgencyId = paymentAgencyId;
        }

    }

    public class ShgPaymentSubType implements Serializable {

        @SerializedName("shg_payment_agency_type")
        @Expose
        private List<ShgPaymentAgencyType> shgPaymentAgencyType = null;
        @SerializedName("payment_discription")
        @Expose
        private String paymentDiscription;
        @SerializedName("payments_type_id")
        @Expose
        private String paymentsTypeId;

        public List<ShgPaymentAgencyType> getShgPaymentAgencyType() {
            return shgPaymentAgencyType;
        }

        public void setShgPaymentAgencyType(List<ShgPaymentAgencyType> shgPaymentAgencyType) {
            this.shgPaymentAgencyType = shgPaymentAgencyType;
        }

        public String getPaymentDiscription() {
            return paymentDiscription;
        }

        public void setPaymentDiscription(String paymentDiscription) {
            this.paymentDiscription = paymentDiscription;
        }

        public String getPaymentsTypeId() {
            return paymentsTypeId;
        }

        public void setPaymentsTypeId(String paymentsTypeId) {
            this.paymentsTypeId = paymentsTypeId;
        }

    }

    public class ShgReceiptSubTypeLoanSource implements Serializable {

        @SerializedName("sub_receipt_description")
        @Expose
        private String subReceiptDescription;
        @SerializedName("shg_recpt_loan")
        @Expose
        private List<ShgRecptLoan> shgRecptLoan = null;
        @SerializedName("sub_receipt_id")
        @Expose
        private String subReceiptId;

        public String getSubReceiptDescription() {
            return subReceiptDescription;
        }

        public void setSubReceiptDescription(String subReceiptDescription) {
            this.subReceiptDescription = subReceiptDescription;
        }

        public List<ShgRecptLoan> getShgRecptLoan() {
            return shgRecptLoan;
        }

        public void setShgRecptLoan(List<ShgRecptLoan> shgRecptLoan) {
            this.shgRecptLoan = shgRecptLoan;
        }

        public String getSubReceiptId() {
            return subReceiptId;
        }

        public void setSubReceiptId(String subReceiptId) {
            this.subReceiptId = subReceiptId;
        }

    }

    public class ShgSubActivity implements Serializable {

        @SerializedName("sub_activity_id")
        @Expose
        private String subActivityId;
        @SerializedName("sub_activity_name")
        @Expose
        private String subActivityName;

        public String getSubActivityId() {
            return subActivityId;
        }

        public void setSubActivityId(String subActivityId) {
            this.subActivityId = subActivityId;
        }

        public String getSubActivityName() {
            return subActivityName;
        }

        public void setSubActivityName(String subActivityName) {
            this.subActivityName = subActivityName;
        }

    }

}
