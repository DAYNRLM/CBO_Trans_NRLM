package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterMemberSavingEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String saving_type_code;
    public String saving_type_name;
}
