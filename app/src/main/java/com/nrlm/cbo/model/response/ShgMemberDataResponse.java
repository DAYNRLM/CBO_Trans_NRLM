package com.nrlm.cbo.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShgMemberDataResponse implements Serializable {

    public static ShgMemberDataResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, ShgMemberDataResponse.class);
    }


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("data")
    @Expose
    private List<MemberData> data = null;

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

    public List<MemberData> getData() {
        return data;
    }

    public void setData(List<MemberData> data) {
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

    public class MemberData {

        @SerializedName("shg_member_code")
        @Expose
        private String shgMemberCode;
        @SerializedName("shg_code")
        @Expose
        private String shgCode;
        @SerializedName("entity_code")
        @Expose
        private String entityCode;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("belonging_name")
        @Expose
        private String belongingName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("social_category")
        @Expose
        private String socialCategory;
        @SerializedName("disability")
        @Expose
        private String disability;
        @SerializedName("religion")
        @Expose
        private String religion;
        @SerializedName("aplbpl")
        @Expose
        private String aplbpl;
        @SerializedName("pip_category")
        @Expose
        private String pipCategory;
        @SerializedName("leader")
        @Expose
        private String leader;
        @SerializedName("aadhaar")
        @Expose
        private Object aadhaar;
        @SerializedName("mobile_number")
        @Expose
        private String mobileNumber;
        @SerializedName("active_details")
        @Expose
        private Object activeDetails;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("mem_bank_code")
        @Expose
        private Object memBankCode;
        @SerializedName("mem_branch_code")
        @Expose
        private Object memBranchCode;
        @SerializedName("ac_no")
        @Expose
        private Object acNo;
        @SerializedName("aadhar_seeded_sb_ac")
        @Expose
        private Object aadharSeededSbAc;
        @SerializedName("adhar_valid")
        @Expose
        private Object adharValid;
        @SerializedName("book_keeper_member")
        @Expose
        private Object bookKeeperMember;
        @SerializedName("education")
        @Expose
        private String education;
        @SerializedName("other_education")
        @Expose
        private String otherEducation;
        @SerializedName("enroll_in_pmjy")
        @Expose
        private String enrollInPmjy;
        @SerializedName("enroll_in_pmsby")
        @Expose
        private String enrollInPmsby;
        @SerializedName("enroll_in_lic")
        @Expose
        private String enrollInLic;
        @SerializedName("enroll_in_hic")
        @Expose
        private String enrollInHic;

        public String getShgMemberCode() {
            return shgMemberCode;
        }

        public void setShgMemberCode(String shgMemberCode) {
            this.shgMemberCode = shgMemberCode;
        }

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSocialCategory() {
            return socialCategory;
        }

        public void setSocialCategory(String socialCategory) {
            this.socialCategory = socialCategory;
        }

        public String getDisability() {
            return disability;
        }

        public void setDisability(String disability) {
            this.disability = disability;
        }

        public String getReligion() {
            return religion;
        }

        public void setReligion(String religion) {
            this.religion = religion;
        }

        public String getAplbpl() {
            return aplbpl;
        }

        public void setAplbpl(String aplbpl) {
            this.aplbpl = aplbpl;
        }

        public String getPipCategory() {
            return pipCategory;
        }

        public void setPipCategory(String pipCategory) {
            this.pipCategory = pipCategory;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
        }

        public Object getAadhaar() {
            return aadhaar;
        }

        public void setAadhaar(Object aadhaar) {
            this.aadhaar = aadhaar;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public Object getActiveDetails() {
            return activeDetails;
        }

        public void setActiveDetails(Object activeDetails) {
            this.activeDetails = activeDetails;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Object getMemBankCode() {
            return memBankCode;
        }

        public void setMemBankCode(Object memBankCode) {
            this.memBankCode = memBankCode;
        }

        public Object getMemBranchCode() {
            return memBranchCode;
        }

        public void setMemBranchCode(Object memBranchCode) {
            this.memBranchCode = memBranchCode;
        }

        public Object getAcNo() {
            return acNo;
        }

        public void setAcNo(Object acNo) {
            this.acNo = acNo;
        }

        public Object getAadharSeededSbAc() {
            return aadharSeededSbAc;
        }

        public void setAadharSeededSbAc(Object aadharSeededSbAc) {
            this.aadharSeededSbAc = aadharSeededSbAc;
        }

        public Object getAdharValid() {
            return adharValid;
        }

        public void setAdharValid(Object adharValid) {
            this.adharValid = adharValid;
        }

        public Object getBookKeeperMember() {
            return bookKeeperMember;
        }

        public void setBookKeeperMember(Object bookKeeperMember) {
            this.bookKeeperMember = bookKeeperMember;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getOtherEducation() {
            return otherEducation;
        }

        public void setOtherEducation(String otherEducation) {
            this.otherEducation = otherEducation;
        }

        public String getEnrollInPmjy() {
            return enrollInPmjy;
        }

        public void setEnrollInPmjy(String enrollInPmjy) {
            this.enrollInPmjy = enrollInPmjy;
        }

        public String getEnrollInPmsby() {
            return enrollInPmsby;
        }

        public void setEnrollInPmsby(String enrollInPmsby) {
            this.enrollInPmsby = enrollInPmsby;
        }

        public String getEnrollInLic() {
            return enrollInLic;
        }

        public void setEnrollInLic(String enrollInLic) {
            this.enrollInLic = enrollInLic;
        }

        public String getEnrollInHic() {
            return enrollInHic;
        }

        public void setEnrollInHic(String enrollInHic) {
            this.enrollInHic = enrollInHic;
        }

    }


    /*@SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }*/

    /******data class********/
   /* public class Data {

        @SerializedName("Member")
        @Expose
        private List<Member> member = null;

        public List<Member> getMember() {
            return member;
        }

        public void setMember(List<Member> member) {
            this.member = member;
        }

    }*/

    /*********member class***********/
  /*  public class Member {

        @SerializedName("shg_code")
        @Expose
        private String shgCode;
        @SerializedName("leader")
        @Expose
        private String leader;
        @SerializedName("belonging_name")
        @Expose
        private String belongingName;
        @SerializedName("shg_member_code")
        @Expose
        private String shgMemberCode;
        @SerializedName("aplbpl")
        @Expose
        private String aplbpl;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("disability")
        @Expose
        private String disability;
        @SerializedName("birth_date")
        @Expose
        private String birthDate;
        @SerializedName("pip_category")
        @Expose
        private String pipCategory;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("entity_code")
        @Expose
        private String entityCode;
        @SerializedName("religon")
        @Expose
        private String religon;
        @SerializedName("social_category")
        @Expose
        private String socialCategory;

        public String getShgCode() {
            return shgCode;
        }

        public void setShgCode(String shgCode) {
            this.shgCode = shgCode;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
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

        public String getAplbpl() {
            return aplbpl;
        }

        public void setAplbpl(String aplbpl) {
            this.aplbpl = aplbpl;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDisability() {
            return disability;
        }

        public void setDisability(String disability) {
            this.disability = disability;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getPipCategory() {
            return pipCategory;
        }

        public void setPipCategory(String pipCategory) {
            this.pipCategory = pipCategory;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getEntityCode() {
            return entityCode;
        }

        public void setEntityCode(String entityCode) {
            this.entityCode = entityCode;
        }

        public String getReligon() {
            return religon;
        }

        public void setReligon(String religon) {
            this.religon = religon;
        }

        public String getSocialCategory() {
            return socialCategory;
        }

        public void setSocialCategory(String socialCategory) {
            this.socialCategory = socialCategory;
        }

    }*/
}
