package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentEntity;

import java.util.List;

@Dao
public interface MasterPaymentDao {
    @Insert
    void insertAll(MasterTransShgPaymentEntity masterTransShgPayment);
    @Query("select * from mastertransshgpaymententity")
    List<MasterTransShgPaymentEntity> getAllPaymentType();
}
