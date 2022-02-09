package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.nrlm.cbo.database.ShgTrans;

@Entity
public class ShgSettingSavingFromMemberEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String shg_code;
    public String saving_type;
    public String amount;
    public String roi;
}
