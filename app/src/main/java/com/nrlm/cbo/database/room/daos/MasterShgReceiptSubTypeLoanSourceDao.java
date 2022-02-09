package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptLoanEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptSubTypeLoanSourceEntity;

import java.util.List;

@Dao
public interface MasterShgReceiptSubTypeLoanSourceDao {
    @Insert
    void insertAll(MasterShgReceiptSubTypeLoanSourceEntity masterShgReceiptSubTypeLoanSourceEntity);

    @Query("select * from mastershgreceiptsubtypeloansourceentity where receipt_id =:receiptTypeId")
    List<MasterShgReceiptSubTypeLoanSourceEntity> getReceiptSubTypes(String receiptTypeId);
}
