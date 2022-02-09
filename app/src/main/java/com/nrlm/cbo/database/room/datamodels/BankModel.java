package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.repositories.BankRepo;

public class BankModel extends AndroidViewModel {
    private BankRepo bankRepo;

    public BankModel(@NonNull Application application) {
        super(application);
        bankRepo=new BankRepo(application);
    }

    public void insertAll(BankEntity bankEntity){
        bankRepo.insertAll(bankEntity);
    }
}
