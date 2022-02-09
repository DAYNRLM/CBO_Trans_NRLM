package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentSubTypeEntity;

import java.util.List;

@Dao
public interface MasterPaymentSubTypeDao {
    @Insert
    void insertAll(MasterTransShgPaymentSubTypeEntity masterTransShgPaymentSubType);

    @Query("select * from mastertransshgpaymentsubtypeentity where payments_type_id= :paymentTypeId ")
    List<MasterTransShgPaymentSubTypeEntity>  getPaymentSubTypes(String paymentTypeId);
}
