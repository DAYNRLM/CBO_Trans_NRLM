package com.nrlm.cbo.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.MasterSavingEntity;

import java.util.List;

@Dao
public interface MasterSavingDao {
    @Insert
    void insertAll(MasterSavingEntity entity);

    @Delete
    void delete(MasterSavingEntity entity);

    @Query("SELECT * FROM master_saving")
    List<MasterSavingEntity> getAllSaving();

}
