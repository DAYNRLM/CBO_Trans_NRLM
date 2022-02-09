package com.nrlm.cbo.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.BlockAssign;

import java.util.List;


@Dao
public interface BlockCodeDao {
    @Insert
    void insertAll(BlockAssign users);

    @Delete
    void delete(BlockAssign user);

    @Query("SELECT * FROM blockAssign")
    List<BlockAssign> loadAllBlockList();
}
