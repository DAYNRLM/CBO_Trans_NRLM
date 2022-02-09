package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.repositories.LoginRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginModel extends AndroidViewModel {
    private LoginRepo loginRepo;

    public LoginModel(@NonNull Application application) {
        super(application);
        loginRepo=new LoginRepo(application);
    }

    public void insertLoginData(LoginEntity loginEntity){
        loginRepo.insertLoginData(loginEntity);
    }

    public List<LoginEntity>  getLoginInfo() throws ExecutionException, InterruptedException {
        return loginRepo.getLoginInfo();
    }



}
