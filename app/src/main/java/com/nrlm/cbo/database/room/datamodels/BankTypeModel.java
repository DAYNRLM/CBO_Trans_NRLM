package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.BankTypeEntity;
import com.nrlm.cbo.database.room.repositories.BankTypeRepo;

import java.util.List;

public class BankTypeModel extends AndroidViewModel {

    private BankTypeRepo bankTypeRepo;

    public BankTypeModel(@NonNull Application application) {
        super(application);
        bankTypeRepo=new BankTypeRepo(application);
    }

    public void insertAll(BankTypeEntity bankTypeEntity){
        bankTypeRepo.insertAll(bankTypeEntity);
    }
}
