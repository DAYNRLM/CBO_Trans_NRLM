package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterCutoffLoanTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffLoanSourceEntity;

import java.util.List;

@Dao
public interface MasterCuttOffLoanSourceDao {
    @Insert
    void insertAll(MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity);

    @Query("select * from MasterCuttOffLoanSourceEntity")
    List<MasterCuttOffLoanSourceEntity> getAllLoanSourceType();
}
