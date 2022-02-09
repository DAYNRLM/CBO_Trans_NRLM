package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.ShgCutoffDao;
import com.nrlm.cbo.database.room.daos.ShgDataDao;
import com.nrlm.cbo.database.room.daos.ShgRunningInsuranceDao;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.ShgCutoffEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ShgCutOffRepo {
    private ShgRunningInsuranceDao shgRunningInsuranceDao;
    private ShgCutoffDao shgCutoffDao;
    AppUtils appUtils;

    public ShgCutOffRepo(@NonNull Application application) {
        ShgTrans shgTrans = ShgTrans.getDatabase(application);
        shgRunningInsuranceDao = shgTrans.runningInsuranceDao();
        shgCutoffDao =shgTrans.shgCutoffDao();
        appUtils =AppUtils.getInstance();
    }

    /********************shg running insurance***************************/
    public void insertAll(ShgCutOffRunningInsuranceEntity shgCutOffRunningInsuranceEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgRunningInsuranceDao.insertAll(shgCutOffRunningInsuranceEntity);
            }
        });
    }

    public List<ShgCutOffRunningInsuranceEntity> getAllInsuranceData(String shgCode){
        List<ShgCutOffRunningInsuranceEntity> list = null;
        try {
            Callable<List<ShgCutOffRunningInsuranceEntity>> callable = new Callable<List<ShgCutOffRunningInsuranceEntity>>() {
                @Override
                public List<ShgCutOffRunningInsuranceEntity> call() throws Exception {
                    return shgRunningInsuranceDao.getAllInsuranceData(shgCode);
                }
            };
            Future<List<ShgCutOffRunningInsuranceEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in INSURANCE:- " + e, ShgCutOffRepo.class);
        }
        return list;
    }

    public void delete() {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgRunningInsuranceDao.allData();
            }
        });
    }

    public void updateInsuranceData(ShgCutOffRunningInsuranceEntity shgObject) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String policyNumber=shgObject.policyNumber;
                String policyName=shgObject.policyName;
                String policyPremiumAmount=shgObject.policyPremiumAmount;
                String policyRiskCover=shgObject.policyRiskCover;
                String policyStartDate=shgObject.policyStartDate;
                String policyEndDate=shgObject.policyEndDate;
                String syncStatus=shgObject.insuranceSyncStatus;
                String insuranceId=shgObject.insuranceId;
                String shgCode=shgObject.shgCode;

                shgRunningInsuranceDao.updateVerificationStatus(insuranceId,
                        policyNumber,
                        policyName,
                        policyPremiumAmount,
                        policyRiskCover,
                        policyStartDate,
                        policyEndDate,
                        syncStatus,
                        shgCode);
            }
        });
    }
    /*************************************shg cutoff main page data***************************/

    public void insertAllCutoffData(ShgCutoffEntity  shgCutoffEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgCutoffDao.insertAll(shgCutoffEntity);
            }
        });
    }

    public List<ShgCutoffEntity> getShgCutOffData(String shgCode){
        List<ShgCutoffEntity> list = null;
        try {
            Callable<List<ShgCutoffEntity>> callable = new Callable<List<ShgCutoffEntity>>() {
                @Override
                public List<ShgCutoffEntity> call() throws Exception {
                    return shgCutoffDao.getAllData(shgCode);
                }
            };
            Future<List<ShgCutoffEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in SHG CUTOFF:- " + e, ShgCutOffRepo.class);
        }
        return list;
    }

    public void deleteShgCutOff(String shgCode){
        List<ShgCutoffEntity> list = getShgCutOffData(shgCode);
        if(!list.isEmpty()){
            ShgTrans.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    shgCutoffDao.deleteAll(shgCode);
                }
            });
        }
    }
}
