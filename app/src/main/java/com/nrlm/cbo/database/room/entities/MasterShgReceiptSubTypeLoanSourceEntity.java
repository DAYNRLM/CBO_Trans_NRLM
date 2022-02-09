package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterShgReceiptSubTypeLoanSourceEntity {
   @PrimaryKey(autoGenerate = true)
    public int id;
    public String receipt_id;
    public String sub_receipt_description;
    public String sub_receipt_id;
}
