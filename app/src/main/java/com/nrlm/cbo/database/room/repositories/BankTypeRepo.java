package com.nrlm.cbo.database.room.repositories;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.BankTypeDao;
import com.nrlm.cbo.database.room.entities.BankTypeEntity;
import com.nrlm.cbo.database.room.entity.Shg;

import java.util.List;

public class BankTypeRepo {

    private BankTypeDao bankTypeDao;

    public BankTypeRepo(@NonNull Application application){
        bankTypeDao= ShgTrans.getDatabase(application).bankTypeDao();
    }

    public void insertAll(BankTypeEntity bankTypeEntity){
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bankTypeDao.insertAll(bankTypeEntity);


            }
        });

    }
}
