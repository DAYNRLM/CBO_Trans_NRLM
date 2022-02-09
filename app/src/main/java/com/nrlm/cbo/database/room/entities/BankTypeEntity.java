package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BankTypeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String banktype_detail,banktype_code;
}
