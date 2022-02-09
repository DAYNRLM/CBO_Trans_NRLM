package com.nrlm.cbo.database.room.datamodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.repositories.GpsRepo;

public class GpsModel extends AndroidViewModel {
    private GpsRepo gpsRepo;

    public GpsModel(@NonNull Application application) {
        super(application);
        gpsRepo=new GpsRepo(application);
    }

    public void insertAll(GPsEntity gPsEntity){
        gpsRepo.insertAll(gPsEntity);
    }
}
