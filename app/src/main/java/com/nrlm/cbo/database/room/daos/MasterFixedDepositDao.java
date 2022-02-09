package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffFixedDepositEntity;

import java.util.List;

@Dao
public interface MasterFixedDepositDao {
    @Insert
    void insertAll(MasterCutoffFixedDepositEntity masterCutoffFixedDepositEntity);

    @Query("select * from MasterCutoffFixedDepositEntity")
    List<MasterCutoffFixedDepositEntity> getAllData();
}
