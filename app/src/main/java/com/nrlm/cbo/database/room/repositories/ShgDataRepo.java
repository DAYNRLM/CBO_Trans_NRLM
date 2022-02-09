package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.room.Query;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.ShgDataDao;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgEntityDataModel;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgSavingInfoEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;


public class ShgDataRepo {
    private ShgDataDao shgDataDao;
    AppUtils appUtils;
    public ShgDataRepo(Application application){
        ShgTrans shgTransDB=ShgTrans.getDatabase(application);
        shgDataDao=shgTransDB.shgDataDao();
        appUtils = AppUtils.getInstance();
    }
    public void insertAll(ShgDataEntity shgDataEntity){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgDataDao.insertAll(shgDataEntity);
            }
        });
    }

    public List<ShgEntityDataModel> getAllShgInfo() throws ExecutionException, InterruptedException {
        Callable<List<ShgEntityDataModel>> callable = new Callable<List<ShgEntityDataModel>>() {
            @Override
            public List<ShgEntityDataModel> call() throws Exception {
                return shgDataDao.getAllShgInfo();
            }
        };

        Future<List<ShgEntityDataModel>> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
    }

    public List<ShgDataEntity> getShgData(String villageCod) {
        List<ShgDataEntity> list = null;
        try {
            Callable<List<ShgDataEntity>> callable = new Callable<List<ShgDataEntity>>() {
                @Override
                public List<ShgDataEntity> call() throws Exception {
                    return shgDataDao.getShgData(villageCod);
                }
            };
            Future<List<ShgDataEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public String getVerificationStatus(String shgCode) {
       String status ="";
        try {
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return shgDataDao.getVerificationStatus(shgCode);
                }
            };
            Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
            status = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return status;
    }

    public void updateVerificationStatus(String shgCode,String verificationStatus){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgDataDao.updateVerificationStatus(shgCode,verificationStatus);
            }
        });
    }

    public String getshgName(String shgCode){
        String status ="";
        try {
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return shgDataDao.getShgName(shgCode);
                }
            };
            Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
            status = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return status;

    }


    public ShgSavingInfoEntityDataModel getShgSavingInfo(String entityCode,String selectedShgCode) throws ExecutionException, InterruptedException {
            Callable<ShgSavingInfoEntityDataModel> callable = new Callable<ShgSavingInfoEntityDataModel>() {
                @Override
                public ShgSavingInfoEntityDataModel call() throws Exception {
                    return shgDataDao.getShgSavingInfo(entityCode,selectedShgCode);
                }
            };
            Future future = Executors.newSingleThreadExecutor().submit(callable);
            return (ShgSavingInfoEntityDataModel) future.get();
        }

        public String getFormationDate(String shgCode){
            String date ="";
            try {
                Callable<String> callable = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return shgDataDao.getFormationDate(shgCode);
                    }
                };
                Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
                date = future.get();

            } catch (Exception e) {
                appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
            }
            return date;

        }


        public void updateShgCutoffStatus(String shgCode,String verificationStatus){
            ShgTrans.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    shgDataDao.updateShgCutOffStatus(shgCode,verificationStatus);
                }
            });
        }

        public List<ShgDataEntity> getShgData(String villageCode,String cutOffstatus)
        {
            List<ShgDataEntity> shgData=null;
            try {
                Callable<List<ShgDataEntity>> callable=new Callable<List<ShgDataEntity>>() {
                    @Override
                    public List<ShgDataEntity> call() throws Exception {
                        return shgDataDao.getShgData(villageCode,cutOffstatus);
                    }
                };
                Future<List<ShgDataEntity>> future=ShgTrans.databaseWriteExecutor.submit(callable);
                shgData=future.get();



            }catch (Exception e)
            {
                appUtils.showLog("Error in getShg data which have the shg cutOff status"+e,ShgDataRepo.class);
            }
            return shgData;

        }


}
