package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterNomineeRelationEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String relation_code;
    public String relation_name;
}
