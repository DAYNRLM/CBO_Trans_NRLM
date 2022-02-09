package com.nrlm.cbo.model.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAndBranchResponse implements Serializable {

    private int status;
    private List<BankData> data;

    public static BankAndBranchResponse jsonToJava(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, BankAndBranchResponse.class);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BankData> getData() {
        return data;
    }

    public void setData(List<BankData> data) {
        this.data = data;
    }

    public  class BankData implements Serializable{

        private String banktype_code;
        private String banktype_detail;

        private List<BankDetails> bank_detail;

        public String getBanktype_code() {
            return banktype_code;
        }

        public void setBanktype_code(String banktype_code) {
            this.banktype_code = banktype_code;
        }

        public String getBanktype_detail() {
            return banktype_detail;
        }

        public void setBanktype_detail(String banktype_detail) {
            this.banktype_detail = banktype_detail;
        }

        public List<BankDetails> getBank_detail() {
            return bank_detail;
        }

        public void setBank_detail(List<BankDetails> bank_detail) {
            this.bank_detail = bank_detail;
        }

        public class BankDetails implements Serializable{

            private String entity_code;
            private String bank_name;
            private String bank_code;
            private String banklevel_code;
            private String acc_length;

            private List<BranchData> branch_details;

            public String getEntity_code() {
                return entity_code;
            }

            public void setEntity_code(String entity_code) {
                this.entity_code = entity_code;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBank_code() {
                return bank_code;
            }

            public void setBank_code(String bank_code) {
                this.bank_code = bank_code;
            }

            public String getBanklevel_code() {
                return banklevel_code;
            }

            public void setBanklevel_code(String banklevel_code) {
                this.banklevel_code = banklevel_code;
            }

            public String getAcc_length() {
                return acc_length;
            }

            public void setAcc_length(String acc_length) {
                this.acc_length = acc_length;
            }

            public List<BranchData> getBank_branch_detail() {
                return branch_details;
            }

            public void setBank_branch_detail(List<BranchData> bank_branch_detail) {
                this.branch_details = bank_branch_detail;
            }

            public class BranchData implements Serializable{
                private String branch_code;
                private String branch_name;
                private String ifsc_code;

                public String getBranch_code() {
                    return branch_code;
                }

                public void setBranch_code(String branch_code) {
                    this.branch_code = branch_code;
                }

                public String getBranch_name() {
                    return branch_name;
                }

                public void setBranch_name(String branch_name) {
                    this.branch_name = branch_name;
                }

                public String getIfsc_code() {
                    return ifsc_code;
                }

                public void setIfsc_code(String ifsc_code) {
                    this.ifsc_code = ifsc_code;
                }
            }
        }
    }
}
