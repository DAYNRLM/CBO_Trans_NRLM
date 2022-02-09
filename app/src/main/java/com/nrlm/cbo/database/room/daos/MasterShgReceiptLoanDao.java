package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptLoanEntity;

import java.util.List;

@Dao
public interface MasterShgReceiptLoanDao {

    @Insert
    void insertAll(MasterShgReceiptLoanEntity masterShgReceiptLoanEntity);

    @Query("Select * from mastershgreceiptloanentity where sub_receipt_id=:receiptSubTypeId")
    List<MasterShgReceiptLoanEntity> getReceiptSubToSubTypes(String receiptSubTypeId);

}

