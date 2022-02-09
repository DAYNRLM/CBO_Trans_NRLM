package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.GpsDao;
import com.nrlm.cbo.database.room.entities.BlockEntity;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entity.GpAssign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GpsRepo {
    private GpsDao gpsDao;
    AppUtils appUtils ;

    public GpsRepo(@NonNull Application application) {
        ShgTrans shgTrans = ShgTrans.getDatabase(application);
        gpsDao = shgTrans.gpsDao();
        appUtils =AppUtils.getInstance();
    }

    public void insertAll(GPsEntity gPsEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                gpsDao.insertAll(gPsEntity);
            }
        });
    }

    public List<GPsEntity> getAllGpData() {
        List<GPsEntity> list = null;
        try {
            Callable<List<GPsEntity>> callable = new Callable<List<GPsEntity>>() {
                @Override
                public List<GPsEntity> call() throws Exception {
                    return gpsDao.getGpData();
                }
            };
            Future<List<GPsEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- "+e,GpsRepo.class);
        }
        return list;
    }

  /*  public List<GPsEntity> getAllGpData(){
        List<GPsEntity> gPsEntities =gpsDao.getGpData();
        return gPsEntities;
    }*/

    public List<String> getGpNameData() {
        ArrayList<String> strList = new ArrayList<>();
        List<GPsEntity> bList = null;
        bList = getAllGpData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).gp_name);
        }
        return strList;
    }
}
