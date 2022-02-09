package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;

import java.util.List;

@Dao
public interface MasterBankCompanyDao {
    @Insert
    void insertAll(MasterCutoffBankCompnyEntity masterCutoffBankCompnyEntity);

    @Query("select * from MasterCutoffBankCompnyEntity")
    List<MasterCutoffBankCompnyEntity> getAllData();
}
