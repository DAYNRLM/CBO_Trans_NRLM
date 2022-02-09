package com.nrlm.cbo.database.room.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterSeetingShgSubActivityEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String category_value;
    public String sub_activity_id;
    public String sub_activity_name;

}
