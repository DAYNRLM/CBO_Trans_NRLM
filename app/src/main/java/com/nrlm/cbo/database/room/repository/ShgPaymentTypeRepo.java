package com.nrlm.cbo.database.room.repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.database.AppDataBase;
import com.nrlm.cbo.database.room.dao.ShgPaymentTypeDao;
import com.nrlm.cbo.database.room.entity.ShgPaymentTypeEntity;

import java.util.ArrayList;
import java.util.List;

public class ShgPaymentTypeRepo {

    private ShgPaymentTypeDao shgPaymentTypeDao;

   public ShgPaymentTypeRepo (@NonNull Application application){
        AppDataBase appDataBase=AppDataBase.getDatabase(application);
        shgPaymentTypeDao=appDataBase.shgPaymentTypeDao();
    }

    public void insertShgPaymentTypeDao(ShgPaymentTypeEntity shgPaymentTypeEntity){
        AppDataBase.databaseWriteExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        shgPaymentTypeDao.insertShgPaymentTypeDao(shgPaymentTypeEntity);
                    }
                }
        );
    }

    public List<ShgPaymentTypeEntity> loadShgPaymentTypeList(){

       final List<ShgPaymentTypeEntity>[] shgPaymentTypeListArray = new List[]{new ArrayList<>()};

        AppDataBase.databaseWriteExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        shgPaymentTypeListArray[0] = shgPaymentTypeDao.loadAllShgPaymentType();
                    }
                }
        );
        return shgPaymentTypeListArray[0];
    }
}
