package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterShgReceiptLoanEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String sub_receipt_id;
    public String shg_recpt_loan_name;
    public String shg_recpt_loan_value;

}
