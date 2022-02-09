package com.nrlm.cbo.database.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.GpAssign;

import java.util.List;

@Dao
public interface GpDao {
    @Insert
    void insertAll(GpAssign users);

    @Delete
    void delete(GpAssign user);

    @Query("SELECT * FROM gpAssign")
    List<GpAssign> loadAllGpList();

}
