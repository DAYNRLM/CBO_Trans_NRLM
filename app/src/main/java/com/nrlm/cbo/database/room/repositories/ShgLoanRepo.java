package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.ShgLoanDao;
import com.nrlm.cbo.database.room.daos.ShgRunningInsuranceDao;
import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.ShgLoansEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ShgLoanRepo {
    private ShgLoanDao shgLoanDao;
    AppUtils appUtils;

    public ShgLoanRepo(@NonNull Application application) {
        ShgTrans shgTrans = ShgTrans.getDatabase(application);
        shgLoanDao = shgTrans.shgLoanDao();
        appUtils =AppUtils.getInstance();
    }

    public void insertAll(ShgLoansEntity shgLoansEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shgLoanDao.insertAll(shgLoansEntity);
            }
        });
    }


    public List<ShgLoansEntity> getAllInsuranceData(String shgCode,String commingId){
        List<ShgLoansEntity> list = null;
        try {
            Callable<List<ShgLoansEntity>> callable = new Callable<List<ShgLoansEntity>>() {
                @Override
                public List<ShgLoansEntity> call() throws Exception {
                    return shgLoanDao.getAllRunningLoanData(shgCode,commingId);
                }
            };
            Future<List<ShgLoansEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in SHG LOAN DATA:- " + e, ShgLoanRepo.class);
        }
        return list;
    }

}
