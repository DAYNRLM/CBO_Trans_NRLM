package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.nrlm.cbo.database.room.entities.BankTypeEntity;

import java.util.List;

@Dao
public interface BankTypeDao {
    @Insert
    void insertAll(BankTypeEntity bankTypeEntity);
}
