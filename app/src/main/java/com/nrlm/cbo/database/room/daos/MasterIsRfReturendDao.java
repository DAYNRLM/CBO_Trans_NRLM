package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterIsRfReturendEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;

import java.util.List;

@Dao
public interface MasterIsRfReturendDao {
    @Insert
    void insertAll(MasterIsRfReturendEntity masterIsRfReturend);

    @Query("select * from MasterIsRfReturendEntity ")
    List<MasterIsRfReturendEntity> getRfData();
}
