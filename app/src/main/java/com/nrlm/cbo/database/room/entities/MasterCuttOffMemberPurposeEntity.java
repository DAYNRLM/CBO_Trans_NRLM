package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterCuttOffMemberPurposeEntity {
    @PrimaryKey(autoGenerate = true)
  public   int id;
   public String discription;
   public String purpose_id;
}
