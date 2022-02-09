package com.nrlm.cbo.database.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.ShgPaymentTypeEntity;

import java.util.List;

@Dao
public interface ShgPaymentTypeDao {
    @Insert
    void insertShgPaymentTypeDao(ShgPaymentTypeEntity shgPaymentTypeEntity);

    @Query("SELECT * FROM shg_payment_type")
    List<ShgPaymentTypeEntity> loadAllShgPaymentType();


}
