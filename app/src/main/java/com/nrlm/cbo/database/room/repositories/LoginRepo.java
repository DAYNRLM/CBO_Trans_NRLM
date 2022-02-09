package com.nrlm.cbo.database.room.repositories;

import android.app.Application;
import android.telecom.Call;

import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.LoginDao;
import com.nrlm.cbo.database.room.entities.LoginEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoginRepo {

    private LoginDao loginDao;

    public LoginRepo(Application application){
        ShgTrans shgTransDB=ShgTrans.getDatabase(application);
        loginDao=shgTransDB.loginDao();
    }

    public void insertLoginData(LoginEntity loginEntity){
       ShgTrans.databaseWriteExecutor.execute(new Runnable() {
           @Override
           public void run() {
               loginDao.insertAll(loginEntity);
           }
       });

    }

    public  List<LoginEntity> getLoginInfo() throws ExecutionException, InterruptedException {
        Callable< List<LoginEntity>> callable=new Callable< List<LoginEntity>>() {
            @Override
            public List<LoginEntity> call() throws Exception {
                return loginDao.getLoginInfo();
            }
        };
        Future<List<LoginEntity>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }
}
