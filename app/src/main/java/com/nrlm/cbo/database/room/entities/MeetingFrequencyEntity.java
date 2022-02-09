package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MeetingFrequencyEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String meeting_frequency;
    public String meeting_frequency_name;
}
