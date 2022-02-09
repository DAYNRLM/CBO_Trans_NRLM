package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.repositories.BankBranchRepo;

public class BankBranchModel extends AndroidViewModel {
    private BankBranchRepo bankBranchRepo;

    public BankBranchModel(@NonNull Application application) {
        super(application);
      bankBranchRepo=new BankBranchRepo(application);
    }

    public void insertAll(BankBranchEntity bankBranchEntity){
        bankBranchRepo.insertAll(bankBranchEntity);
    }
}
