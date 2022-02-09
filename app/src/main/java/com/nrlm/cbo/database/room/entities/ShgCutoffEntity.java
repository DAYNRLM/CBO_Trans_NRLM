package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShgCutoffEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String shg_code;
    public String cutoff_id;
    public String cutoff_sync_status;

    public String meeting_conducted;
    public String last_meeting_date;
    public String shg_cutoff_data;
    public String cash_in_hand;
    public String cash_in_bank;
    public String fixed_deposit;

    public String saving_with_vo;
    public String saving_with_clf;
    public String share_capital_with_vo;
    public String share_capital_with_clf;

    public String startup_grant_from_srlm;
    public String rf_received_from_srlm;
    public String cif_received_from_srlm;
    public String rf_returned_to_srlm;
    public String cif_returned_to_srlm;
    public String other_receipts;
    public String grant_from_govt_source;
    public String vrf_grant_from_nrlm;


    public String closed_loan_from_bank;
    public String amount_for_closed_loan_bank;
    public String closed_loan_from_vo;
    public String amount_for_closed_loan_vo;
    public String closed_loan_from_clf;
    public String amount_for_closed_loan_clf;


}
