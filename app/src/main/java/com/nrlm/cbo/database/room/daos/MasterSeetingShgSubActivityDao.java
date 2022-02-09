package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgSubActivityEntity;

import java.util.List;

@Dao
public interface MasterSeetingShgSubActivityDao {

    @Insert
    void insertAll(MasterSeetingShgSubActivityEntity masterSeetingShgSubActivityEntity);


    @Query("select * from MasterSeetingShgSubActivityEntity where category_value=:catValue")
    List<MasterSeetingShgSubActivityEntity> getAllData(String catValue);

}
