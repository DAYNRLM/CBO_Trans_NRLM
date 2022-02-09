package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.ShgLoansEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;

import java.util.List;

@Dao
public interface ShgLoanDao {
    @Insert
    void insertAll(ShgLoansEntity shgLoansEntity);


    @Query("select * from ShgLoansEntity where shg_Code =:shgCode and loan_status_comming_from=:loanCommingId")
    List<ShgLoansEntity> getAllRunningLoanData(String shgCode,String loanCommingId);
}
