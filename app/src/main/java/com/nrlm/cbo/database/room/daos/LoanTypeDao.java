package com.nrlm.cbo.database.room.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterCutoffLoanTypeEntity;

import java.util.List;

@Dao
public interface LoanTypeDao {
    @Insert
    void insertAll(MasterCutoffLoanTypeEntity masterCutoffLoanType);

    @Query("select * from MasterCutoffLoanTypeEntity")
    List<MasterCutoffLoanTypeEntity> getAllLoanType();

}
