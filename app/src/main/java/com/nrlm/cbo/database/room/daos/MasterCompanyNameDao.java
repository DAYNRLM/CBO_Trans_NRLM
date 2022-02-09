package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyNameEntity;

import java.util.List;

@Dao
public interface MasterCompanyNameDao {

    @Insert
    void insertAll(MasterCutoffCompanyNameEntity masterCutoffCompanyNameEntity);

    @Query("select * from MasterCutoffCompanyNameEntity")
    List<MasterCutoffCompanyNameEntity> getAllData();
}
