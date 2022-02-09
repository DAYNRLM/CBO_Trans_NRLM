package com.nrlm.cbo.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MicroServiceMasterREsponse {

    public static MicroServiceMasterREsponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, MicroServiceMasterREsponse.class);
    }

    @SerializedName("shg_member_cut_off")
    @Expose
    private List<ShgMemberCutOff> shgMemberCutOff = null;
    @SerializedName("trans_shg_payment")
    @Expose
    private List<TransShgPayment> transShgPayment = null;
    @SerializedName("trans_shg_receipt")
    @Expose
    private List<TransShgReceipt> transShgReceipt = null;
    @SerializedName("shg_setting")
    @Expose
    private List<ShgSetting> shgSetting = null;
    @SerializedName("shg_cut_off")
    @Expose
    private List<ShgCutOff> shgCutOff = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("error")
    @Expose
    private Error error;

    public List<ShgMemberCutOff> getShgMemberCutOff() {
        return shgMemberCutOff;
    }

    public void setShgMemberCutOff(List<ShgMemberCutOff> shgMemberCutOff) {
        this.shgMemberCutOff = shgMemberCutOff;
    }

    public List<TransShgPayment> getTransShgPayment() {
        return transShgPayment;
    }

    public void setTransShgPayment(List<TransShgPayment> transShgPayment) {
        this.transShgPayment = transShgPayment;
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

    public class BankCompany {

        @SerializedName("bank_company_name")
        @Expose
        private String bankCompanyName;
        @SerializedName("bank_company_value")
        @Expose
        private String bankCompanyValue;

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

    }

    public class Company {

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

    public class CompanyBranch {

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

    public class FixedDepInv {

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


    public class Isrfreturnto {

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

    public class LoanFrom {

        @SerializedName("loan_from_name")
        @Expose
        private String loanFromName;
        @SerializedName("loan_from_id")
        @Expose
        private String loanFromId;

        public String getLoanFromName() {
            return loanFromName;
        }

        public void setLoanFromName(String loanFromName) {
            this.loanFromName = loanFromName;
        }

        public String getLoanFromId() {
            return loanFromId;
        }

        public void setLoanFromId(String loanFromId) {
            this.loanFromId = loanFromId;
        }

    }

    public class LoanSource {

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

    public class LoanType {

        @SerializedName("loan_type_name")
        @Expose
        private String loanTypeName;
        @SerializedName("loan_type_id")
        @Expose
        private String loanTypeId;

        public String getLoanTypeName() {
            return loanTypeName;
        }

        public void setLoanTypeName(String loanTypeName) {
            this.loanTypeName = loanTypeName;
        }

        public String getLoanTypeId() {
            return loanTypeId;
        }

        public void setLoanTypeId(String loanTypeId) {
            this.loanTypeId = loanTypeId;
        }

    }

    public class Meetingfreq {

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

    public class MemPurpose {

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

    public class MemSaving {

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

    public class MemberRelationNominee {

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

    public class SavingFromMember {

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

    public class ShgActivityCategory {

        @SerializedName("category_name")
        @Expose
        private String categoryName;
        @SerializedName("category_value")
        @Expose
        private String categoryValue;
        @SerializedName("shgSubActivityList")
        @Expose
        private List<ShgSubActivity> shgSubActivityList = null;

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

        public List<ShgSubActivity> getShgSubActivityList() {
            return shgSubActivityList;
        }

        public void setShgSubActivityList(List<ShgSubActivity> shgSubActivityList) {
            this.shgSubActivityList = shgSubActivityList;
        }

    }

    public class ShgCutOff {

        @SerializedName("bank_company")
        @Expose
        private List<BankCompany> bankCompany = null;
        @SerializedName("company")
        @Expose
        private List<Company> company = null;
        @SerializedName("loan_from")
        @Expose
        private List<LoanFrom> loanFrom = null;
        @SerializedName("loan_type")
        @Expose
        private List<LoanType> loanType = null;
        @SerializedName("fixed_dep_inv")
        @Expose
        private List<FixedDepInv> fixedDepInv = null;

        public List<BankCompany> getBankCompany() {
            return bankCompany;
        }

        public void setBankCompany(List<BankCompany> bankCompany) {
            this.bankCompany = bankCompany;
        }

        public List<Company> getCompany() {
            return company;
        }

        public void setCompany(List<Company> company) {
            this.company = company;
        }

        public List<LoanFrom> getLoanFrom() {
            return loanFrom;
        }

        public void setLoanFrom(List<LoanFrom> loanFrom) {
            this.loanFrom = loanFrom;
        }

        public List<LoanType> getLoanType() {
            return loanType;
        }

        public void setLoanType(List<LoanType> loanType) {
            this.loanType = loanType;
        }

        public List<FixedDepInv> getFixedDepInv() {
            return fixedDepInv;
        }

        public void setFixedDepInv(List<FixedDepInv> fixedDepInv) {
            this.fixedDepInv = fixedDepInv;
        }

    }

    public class ShgMemberCutOff {

        @SerializedName("mem_saving")
        @Expose
        private List<MemSaving> memSaving = null;
        @SerializedName("mem_purpose")
        @Expose
        private List<MemPurpose> memPurpose = null;
        @SerializedName("loan_source")
        @Expose
        private List<LoanSource> loanSource = null;

        public List<MemSaving> getMemSaving() {
            return memSaving;
        }

        public void setMemSaving(List<MemSaving> memSaving) {
            this.memSaving = memSaving;
        }

        public List<MemPurpose> getMemPurpose() {
            return memPurpose;
        }

        public void setMemPurpose(List<MemPurpose> memPurpose) {
            this.memPurpose = memPurpose;
        }

        public List<LoanSource> getLoanSource() {
            return loanSource;
        }

        public void setLoanSource(List<LoanSource> loanSource) {
            this.loanSource = loanSource;
        }

    }

    public class ShgPaymentAgencyType {

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

    public class ShgPaymentSubType {

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

    public class ShgReceiptSubTypeLoanSource {

        @SerializedName("sub_receipt_description")
        @Expose
        private String subReceiptDescription;
        @SerializedName("sub_receipt_id")
        @Expose
        private String subReceiptId;
        @SerializedName("shg_recpt_loan")
        @Expose
        private Object shgRecptLoan;

        public String getSubReceiptDescription() {
            return subReceiptDescription;
        }

        public void setSubReceiptDescription(String subReceiptDescription) {
            this.subReceiptDescription = subReceiptDescription;
        }

        public String getSubReceiptId() {
            return subReceiptId;
        }

        public void setSubReceiptId(String subReceiptId) {
            this.subReceiptId = subReceiptId;
        }

        public Object getShgRecptLoan() {
            return shgRecptLoan;
        }

        public void setShgRecptLoan(Object shgRecptLoan) {
            this.shgRecptLoan = shgRecptLoan;
        }

    }

    public class ShgSetting {

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

    public class ShgSubActivity {

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

    public class TransShgPayment {

        @SerializedName("payment_discription")
        @Expose
        private String paymentDiscription;
        @SerializedName("payments_type_id")
        @Expose
        private String paymentsTypeId;
        @SerializedName("shg_payment_sub_type")
        @Expose
        private List<ShgPaymentSubType> shgPaymentSubType = null;

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

        public List<ShgPaymentSubType> getShgPaymentSubType() {
            return shgPaymentSubType;
        }

        public void setShgPaymentSubType(List<ShgPaymentSubType> shgPaymentSubType) {
            this.shgPaymentSubType = shgPaymentSubType;
        }

    }

    public class TransShgReceipt {

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

}
