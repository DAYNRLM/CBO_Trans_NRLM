package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemberCutOffRunningLoanEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public  String  member_running_loan_id;
    public String shg_code;
    public String member_code;
    public String loan_source;
    public String loan_number;
    public String loan_date;
    public String loan_amount;
    public String rate_of_intrest;
    public String number_of_installments;
    public String installments_amount;
    public String repayment_starting_month;
    public String amount_paid;
    public String purpose;
    public String principal_overdue;
    public String intrest_overdue;
    public String member_running_loan_sync_status;
}
