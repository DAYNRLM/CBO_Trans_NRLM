package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgEntityDataModel;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgSavingInfoEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;

import java.util.List;

import java.util.List;

@Dao
public interface ShgDataDao {
    @Insert
    void insertAll(ShgDataEntity shgDataEntity);

    @Query("SELECT shgCode, shgName, entityCode, verificationStatus, settingStatus, cuttOffStatus" +
            "  FROM SHGDATAENTITY WHERE entityCode= '0523002005004'")
    List<ShgEntityDataModel> getAllShgInfo();

    @Query("SELECT shgName, shgCode, entityCode, lastMeetingDate, lastMeetingNo FROM SHGDATAENTITY" +
            " WHERE entityCode= :entityCode AND shgCode=:selectedShgCode ")
    ShgSavingInfoEntityDataModel getShgSavingInfo(String entityCode, String selectedShgCode);


    @Query("select * from ShgDataEntity where entityCode =:villageCode order by cuttOffStatus DESC")
    List<ShgDataEntity> getShgData(String villageCode);

    @Query("SELECT verificationStatus FROM ShgDataEntity WHERE shgCode =:shgCode ")
    String getVerificationStatus(String shgCode);

    @Query("UPDATE ShgDataEntity SET verificationStatus = :status WHERE shgCode = :shgCode")
    void updateVerificationStatus(String shgCode,String status);

    @Query("SELECT shgName FROM ShgDataEntity WHERE shgCode =:shgCode ")
    String getShgName(String shgCode);

    @Query("SELECT groupFormationDate FROM ShgDataEntity WHERE shgCode =:shgCode ")
    String getFormationDate(String shgCode);

    @Query("UPDATE ShgDataEntity SET cuttOffStatus = :status WHERE shgCode = :shgCode")
    void updateShgCutOffStatus(String shgCode,String status);

    @Query("select * from ShgDataEntity where entityCode =:villageCode and cuttOffStatus=:cutOffstatus")
    List<ShgDataEntity> getShgData(String villageCode,String cutOffstatus);



}
