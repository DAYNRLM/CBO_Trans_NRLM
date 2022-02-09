package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.entities.GPsEntity;

import java.util.List;

@Dao
public interface BankDao {
    @Insert
    void insertAll(BankEntity bankEntity);

    @Query("select * from BankEntity  where  bank_code is not null ")
    List<BankEntity> getAllData();

}
