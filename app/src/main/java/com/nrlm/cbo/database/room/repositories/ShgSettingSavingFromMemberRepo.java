package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.ShgSettingSavingFromMemberDao;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberSavingTypeEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ShgSettingSavingFromMemberRepo {

     private ShgSettingSavingFromMemberDao shgSettingSavingFromMemberDao;
     AppUtils appUtils;

     public ShgSettingSavingFromMemberRepo(Application application){
         shgSettingSavingFromMemberDao= ShgTrans.getDatabase(application).shgSettingSavingFromMemberDao();
         appUtils =AppUtils.getInstance();
     }

     public void insert(ShgSettingSavingFromMemberEntity shgSettingSavingFromMemberEntity){
         ShgTrans.databaseWriteExecutor.execute(new Runnable() {
             @Override
             public void run() {
                 shgSettingSavingFromMemberDao.insert(shgSettingSavingFromMemberEntity);
             }
         });
     }

     public List<MemberSavingTypeEntityDataModel> getSettingSavingType(String shgCode) throws ExecutionException, InterruptedException {

         Callable<List<MemberSavingTypeEntityDataModel>> callable=new Callable<List<MemberSavingTypeEntityDataModel>>() {
             @Override
             public List<MemberSavingTypeEntityDataModel> call() throws Exception {
                 return shgSettingSavingFromMemberDao.getSettingSavingType(shgCode);
             }
         };
         Future<List<MemberSavingTypeEntityDataModel>> future= Executors.newSingleThreadExecutor().submit(callable);
         return future.get();

     }

    public List<ShgSettingSavingFromMemberEntity> getAllSavingData(String shgCode) {
        List<ShgSettingSavingFromMemberEntity> list = null;
        try {
            Callable<List<ShgSettingSavingFromMemberEntity>> callable = new Callable<List<ShgSettingSavingFromMemberEntity>>() {
                @Override
                public List<ShgSettingSavingFromMemberEntity> call() throws Exception {
                    return shgSettingSavingFromMemberDao.getAllSaving(shgCode);
                }
            };
            Future<List<ShgSettingSavingFromMemberEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }


    public void deleteSavingType(String shgCode,String typeId){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgSettingSavingFromMemberDao.deleteSavingType(shgCode,typeId);
            }
        });

    }

    public void updateShgSaving(String shgCode,String savingTypeId,String amount,String roi){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgSettingSavingFromMemberDao.updateSaving(shgCode,savingTypeId,amount,roi);
            }
        });
    }




}
