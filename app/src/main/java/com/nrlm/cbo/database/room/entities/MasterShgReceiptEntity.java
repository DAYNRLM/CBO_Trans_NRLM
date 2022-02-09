package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterShgReceiptEntity {
   @PrimaryKey(autoGenerate = true)
    public int id;
    public String receipt_description;
    public String receipt_id;
}
