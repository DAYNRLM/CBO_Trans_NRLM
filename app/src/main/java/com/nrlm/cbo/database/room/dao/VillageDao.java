package com.nrlm.cbo.database.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.VillageAssign;

import java.util.List;

@Dao
public interface VillageDao {
    @Insert
    void insertAll(VillageAssign users);

    @Delete
    void delete(VillageAssign user);

    @Query("SELECT * FROM villageAssign")
    List<VillageAssign> loadAllVillageList();

    @Query("SELECT * FROM villageAssign where  gp_code = :gpcode")
    List<VillageAssign> loadVillageBasedOnGP(String gpcode);
}
