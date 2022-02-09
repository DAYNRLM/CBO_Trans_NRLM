package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MemberCutOffRunningLoanEntity;

import java.util.List;

@Dao
public interface MemberCutOffRunningLoanDao {
    @Insert
    void insertAll(MemberCutOffRunningLoanEntity memberCutOffRunningLoanEntity);

    @Query("delete from MemberCutOffRunningLoanEntity where loan_number=:loanNumber")
    void deleteById(String loanNumber);

    @Query("select * from MemberCutOffRunningLoanEntity where member_code=:memberCode")
    List<MemberCutOffRunningLoanEntity> getRunningLoanData(String memberCode);

}
