package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.BlockEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BlockDao {
    @Insert
    void insertAll(BlockEntity blockEntity);

    @Query("SELECT * FROM blockentity")
   List<BlockEntity> getAllBlock();
}
