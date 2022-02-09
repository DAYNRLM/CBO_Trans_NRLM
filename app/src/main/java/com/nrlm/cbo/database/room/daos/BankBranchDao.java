package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.entities.BankEntity;

import java.util.List;

@Dao
public interface BankBranchDao {
   @Insert
   void insertAll(BankBranchEntity bankBranchEntity);

   @Query("select * from BankBranchEntity where bank_code=:bankCode")
   List<BankBranchEntity> getAllData(String bankCode);

}
