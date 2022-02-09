package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.ShgMemberDataDao;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberInfoAndSavingsEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ShgmemberRepo {
    private ShgMemberDataDao shgMemberDao;

    AppUtils appUtils;
    public ShgmemberRepo(Application application){
        ShgTrans shgTransDB=ShgTrans.getDatabase(application);
        shgMemberDao=shgTransDB.shgMemberDao();
        appUtils = AppUtils.getInstance();
    }

    public void insertAll(ShgMemberDataEntity shgMemberDataEntity){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgMemberDao.insertAll(shgMemberDataEntity);
            }
        });
    }

    public List<MemberInfoAndSavingsEntityDataModel> getMemberInfo(String entityCode, String shgCode) throws ExecutionException, InterruptedException {

        Callable<List<MemberInfoAndSavingsEntityDataModel>> callable=new Callable<List<MemberInfoAndSavingsEntityDataModel>>() {
            @Override
            public List<MemberInfoAndSavingsEntityDataModel> call() throws Exception {
             return shgMemberDao.getMemberInfo(entityCode,shgCode);
            }
        };
        Future<List<MemberInfoAndSavingsEntityDataModel>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }

    public List<ShgMemberDataEntity> getAllMemberData(String shgCode) {
        List<ShgMemberDataEntity> list = null;
        try {
            Callable<List<ShgMemberDataEntity>> callable = new Callable<List<ShgMemberDataEntity>>() {
                @Override
                public List<ShgMemberDataEntity> call() throws Exception {
                    return shgMemberDao.getAllMember(shgCode);
                }
            };
            Future<List<ShgMemberDataEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public String getTotalMember(String shgCode) {
        String memberCount ="";
        try {
            Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return shgMemberDao.memberCount(shgCode);
                }
            };
            Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);
            memberCount =String.valueOf( future.get());

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return memberCount;
    }
    /************newly Added************/

    public ShgMemberDataEntity getMemberObject(String memberCode)
    {
        ShgMemberDataEntity shgMemberObject=null;

        try
        {
            Callable<ShgMemberDataEntity> callable=new Callable<ShgMemberDataEntity>() {
                @Override
                public ShgMemberDataEntity call() throws Exception {
                    return shgMemberDao.getShgMemberObj(memberCode);
                }


            };
            Future<ShgMemberDataEntity> future=Executors.newSingleThreadExecutor().submit(callable);
            shgMemberObject=future.get();

        }catch (Exception e)
        {
            appUtils.showLog("Error in shg member data repository"+e,ShgmemberRepo.class);
        }
        return shgMemberObject;

    }


}
