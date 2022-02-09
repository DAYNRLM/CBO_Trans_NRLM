package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterSeetingShgActivityEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String category_name;
    public String category_value;
}
