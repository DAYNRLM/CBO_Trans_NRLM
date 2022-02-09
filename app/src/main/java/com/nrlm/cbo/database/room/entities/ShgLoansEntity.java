package com.nrlm.cbo.database.room.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShgLoansEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String shg_Code;
    public String shg_loan_id;
    public String loan_status_comming_from;//cutoff or recipt
    public String loan_sync_status;

    public String loan_from_code;
    public String loan_type_code;
    public String loan_Number_code;
    public String bank_name_code;
    public String branch_name_code;
    public String bank_ifsc_code;
    public String loan_sanction_date;
    public String loan_sanction_amount;
    public String loan_disburses_date;
    public String loan_disburses_amount;
    public String loan_roi;
    public String number_of_instalment;
    public String instalment_amount;
    public String number_of_instalment_repaid;
    public String principal_paid;
    public String principal_overdue;
    public String intrest_overdue;
    public String intrest_paid;

}
