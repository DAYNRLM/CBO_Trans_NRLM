package com.nrlm.cbo.database.room.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.ShgCutoffEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;

import java.util.List;

@Dao
public interface ShgCutoffDao {
    @Insert
    void insertAll(ShgCutoffEntity shgCutoffEntity);

    @Query("select * from ShgCutoffEntity where shg_code=:shgCode")
    List<ShgCutoffEntity> getAllData(String shgCode);

    @Query("DELETE  from ShgCutoffEntity where shg_code=:shgCode")
    void deleteAll(String shgCode);

}
