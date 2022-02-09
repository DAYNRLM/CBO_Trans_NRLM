package com.nrlm.cbo.database.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.AddShgLoan;

import java.util.List;

@Dao
public interface AddShgLoanDAO {
    @Insert
    void insertAll(AddShgLoan users);

    @Delete
    void delete(AddShgLoan user);

    @Query("SELECT * FROM addShgRunningLoan")
    List<AddShgLoan> loadAllByIds();
}
