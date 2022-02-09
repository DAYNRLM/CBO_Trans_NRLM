package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.VillageDao;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.database.room.entity.VillageAssign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VillageRepo {

    private VillageDao villageDao;
    AppUtils appUtils;

    public VillageRepo(@NonNull Application application) {
        ShgTrans shgTrans = ShgTrans.getDatabase(application);
        villageDao = shgTrans.villageDao();
        appUtils = AppUtils.getInstance();
    }

    public void insertAll(VillageEntity villageEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                villageDao.insertAll(villageEntity);
            }
        });
    }

    public List<VillageEntity> getVillageData(String gpCode) {
        List<VillageEntity> list = null;
        try {
            Callable<List<VillageEntity>> callable = new Callable<List<VillageEntity>>() {
                @Override
                public List<VillageEntity> call() throws Exception {
                    return villageDao.getVillageData(gpCode);
                }
            };
            Future<List<VillageEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getVillageName(String gpcode) {
        ArrayList<String> strList = new ArrayList<>();
        List<VillageEntity> bList = getVillageData(gpcode);
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).village_name);
        }
        return strList;
    }



}
