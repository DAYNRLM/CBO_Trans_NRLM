package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterPaymentAgencyTypeEntity;

import java.util.List;

@Dao
public interface MasterPaymentAgencyTypeDao {

   @Insert
   void  insert(MasterPaymentAgencyTypeEntity masterPaymentAgencyTypeEntity);

   @Query("select * from masterpaymentagencytypeentity where payment_type_id= :paymentTypeId" +
           " and payment_sub_type_id=:paymentSubTypeId")
   List<MasterPaymentAgencyTypeEntity> getPaymentAgencyTypes(String paymentTypeId, String paymentSubTypeId);
}
