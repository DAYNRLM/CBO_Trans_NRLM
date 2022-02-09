package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgActivityEntity;

import java.util.List;

@Dao
public interface MasterSeetingShgActivityDao {
    @Insert
    void insertAll(MasterSeetingShgActivityEntity masterSeetingShgActivityEntity);

    @Query("select * from MasterSeetingShgActivityEntity")
    List<MasterSeetingShgActivityEntity> getAllData();
}
