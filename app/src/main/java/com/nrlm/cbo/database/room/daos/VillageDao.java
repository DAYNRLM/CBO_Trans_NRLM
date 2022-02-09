package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;

import java.util.List;

@Dao
public interface VillageDao {

    @Insert
    void insertAll(VillageEntity villageEntity);

    @Query("select * from VillageEntity where gp_code =:gpcode")
    List<VillageEntity> getVillageData(String gpcode);
}
