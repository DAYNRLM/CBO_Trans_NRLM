package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemberCutOffSavingEntity {
    @PrimaryKey(autoGenerate =true)
    public int id;
    public String member_saving_id;
    public String shg_code;
    public String member_code;
    public String saving_type_code;
    public String saving_type;
    public String amount;
    public String sync_status;

}
