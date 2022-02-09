package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.BankDao;
import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankRepo {
    private BankDao bankDao;
    AppUtils appUtils;

    public BankRepo(@NonNull Application application){
        ShgTrans shgTrans=ShgTrans.getDatabase(application);
        bankDao= shgTrans.bankDao();
        appUtils = AppUtils.getInstance();
    }

    public void insertAll(BankEntity bankEntity){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bankDao.insertAll(bankEntity);
            }
        });
    }

    /*********get bank data and bank name**********/
    public List<BankEntity> getAllBankData(){
        List<BankEntity> list = null;
        try {
            Callable<List<BankEntity>> callable = new Callable<List<BankEntity>>() {
                @Override
                public List<BankEntity> call() throws Exception {
                    return bankDao.getAllData();
                }
            };
            Future<List<BankEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in Bank data:- " + e, BankRepo.class);
        }
        return list;
    }

    public List<String> getBankName(){
        ArrayList<String> strList = new ArrayList<>();
        List<BankEntity> bList = getAllBankData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).bank_name);
        }
        return strList;
    }
}
