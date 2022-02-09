package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.MasterNomineeRelationEntity;

import java.util.List;

@Dao
public interface MasterNomineeRelationDao {
    @Insert
    void insertAll(MasterNomineeRelationEntity masterNomineeRelationEntity);

    @Query("SELECT * FROM MasterNomineeRelationEntity")
    List<MasterNomineeRelationEntity> getAllRelation();
}
