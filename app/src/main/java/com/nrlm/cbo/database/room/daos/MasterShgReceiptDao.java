package com.nrlm.cbo.database.room.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;

import java.util.List;

@Dao
public interface MasterShgReceiptDao {
    @Insert
    void insertAll(MasterShgReceiptEntity masterShgReceiptEntity);

    @Query("select * from mastershgreceiptentity")
    List<MasterShgReceiptEntity> getAllReceiptType();

}
