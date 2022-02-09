package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;

import java.util.List;

@Dao
public interface MasterMemberSavingDao {

    @Insert
    void insertAll(MasterMemberSavingEntity masterMemberSavingEntity);

    @Query("SELECT saving_type_name FROM MasterMemberSavingEntity where saving_type_code = :savingTypeId")
    String getSavingTypeName(String savingTypeId);




    @Query("SELECT * FROM MasterMemberSavingEntity")
    List<MasterMemberSavingEntity> getAllSaving();

    @Query("select saving_type_code from MasterMemberSavingEntity where saving_type_name=:savingTypeName")
    String getSavingTypeCode(String savingTypeName);




}
