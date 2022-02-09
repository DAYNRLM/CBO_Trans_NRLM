package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.BankBranchDao;
import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.entities.BankEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankBranchRepo {
    private BankBranchDao bankBranchDao;
    AppUtils appUtils;

    public BankBranchRepo(@NonNull Application application){
        bankBranchDao= ShgTrans.getDatabase(application).bankBranchDao();
        appUtils=AppUtils.getInstance();
    }

    public void insertAll(BankBranchEntity bankBranchEntity){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bankBranchDao.insertAll(bankBranchEntity);
            }
        });
    }


    /**********get bank branch and name***************/
    public List<BankBranchEntity> getAllBankBranchData(String bankCode){
        List<BankBranchEntity> list = null;
        try {
            Callable<List<BankBranchEntity>> callable = new Callable<List<BankBranchEntity>>() {
                @Override
                public List<BankBranchEntity> call() throws Exception {
                    return bankBranchDao.getAllData(bankCode);
                }
            };
            Future<List<BankBranchEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in Bank Branch data:- " + e, BankBranchRepo.class);
        }
        return list;
    }

    public List<String> getBranchName(String bankCode){
        ArrayList<String> strList = new ArrayList<>();
        List<BankBranchEntity> bList = getAllBankBranchData(bankCode);
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).branch_name);
        }
        return strList;
    }
}
