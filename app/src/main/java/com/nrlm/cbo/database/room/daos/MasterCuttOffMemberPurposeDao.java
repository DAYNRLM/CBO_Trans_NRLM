package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterCuttOffMemberPurposeEntity;

import java.util.List;

@Dao
public interface MasterCuttOffMemberPurposeDao {
    @Insert
    void insertAll(MasterCuttOffMemberPurposeEntity masterCuttOffMemberPurposeEntity);

    @Query("select * from MasterCuttOffMemberPurposeEntity")
    List<MasterCuttOffMemberPurposeEntity> getAllPurpose();


}
