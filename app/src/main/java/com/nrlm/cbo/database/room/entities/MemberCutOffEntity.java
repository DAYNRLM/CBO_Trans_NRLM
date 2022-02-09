package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemberCutOffEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String member_cutOff_id;
    public String shg_code;
    public String member_code;
    public String number_of_close_loan;
    public String amount_of_close_loan;
    public String attended_mettings_since_beignning;
    public String member_cutOff_sync_status;
}
