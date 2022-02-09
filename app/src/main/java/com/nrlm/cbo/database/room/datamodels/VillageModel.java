package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.database.room.repositories.VillageRepo;

public class VillageModel extends AndroidViewModel {
     private VillageRepo villageRepo;
    public VillageModel(@NonNull Application application) {
        super(application);
        villageRepo=new VillageRepo(application);
    }

    public void insertAll(VillageEntity villageEntity){
        villageRepo.insertAll(villageEntity);
    }
}
