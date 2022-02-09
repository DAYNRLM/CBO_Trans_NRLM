package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;

import java.util.List;

@Dao
public interface ShgRunningInsuranceDao {
    @Insert
    void insertAll(ShgCutOffRunningInsuranceEntity shgCutOffRunningInsuranceEntity);

    @Query("select * from ShgCutOffRunningInsuranceEntity where shgCode =:shgCode")
    List<ShgCutOffRunningInsuranceEntity> getAllInsuranceData(String shgCode);


    @Query("UPDATE ShgCutOffRunningInsuranceEntity SET shgCode=:shgCode," +
            " insuranceId=:insuranceId, " +
            "insuranceSyncStatus=:syncStatus," +
            " policyEndDate=:policyEndDate," +
            " policyStartDate=:policyStartDate, " +
            " policyRiskCover=:policyRiskCover," +
            "policyPremiumAmount=:policyPremiumAmount, " +
            " policyName=:policyName, " +
            "policyNumber = :policyNumber  where insuranceId =:insuranceId")
    void updateVerificationStatus(String insuranceId,
                                  String policyNumber,
                                  String policyName,
                                  String policyPremiumAmount,
                                  String policyRiskCover,
                                  String policyStartDate,
                                  String policyEndDate,
                                  String syncStatus,
                                  String shgCode);

   @Query("Delete from ShgCutOffRunningInsuranceEntity")
    void allData();
}
