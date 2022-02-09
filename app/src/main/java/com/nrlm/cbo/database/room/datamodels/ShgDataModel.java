package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.VillageRepo;

public class ShgDataModel {
    private ShgDataRepo shgDataRepo;
    public ShgDataModel(@NonNull Application application) {
        shgDataRepo=new ShgDataRepo(application);
    }

    public void insertAll(ShgDataEntity shgDataEntity){
        shgDataRepo.insertAll(shgDataEntity);
    }
}
