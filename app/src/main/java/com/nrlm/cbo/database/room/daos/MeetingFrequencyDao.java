package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;

import java.util.List;

@Dao
public interface MeetingFrequencyDao {
    @Insert
    void insertAll(MeetingFrequencyEntity meetingFrequencyEntity);

    @Query("select * from MeetingFrequencyEntity")
    List<MeetingFrequencyEntity> getAllData();
}
