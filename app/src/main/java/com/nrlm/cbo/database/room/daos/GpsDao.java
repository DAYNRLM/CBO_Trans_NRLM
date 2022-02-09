package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entity.AddShgLoan;

import java.util.List;

@Dao
public interface GpsDao {

    @Insert
    void insertAll(GPsEntity gPsEntity);

    @Query("select * from GPsEntity")
    List<GPsEntity> getGpData();
}
