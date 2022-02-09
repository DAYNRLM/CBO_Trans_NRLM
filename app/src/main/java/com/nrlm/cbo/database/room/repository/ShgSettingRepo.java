package com.nrlm.cbo.database.room.repository;

import android.content.Context;

import com.nrlm.cbo.database.AppDataBase;
import com.nrlm.cbo.database.room.entity.MasterSavingEntity;

import java.util.List;

public class ShgSettingRepo {
    public static ShgSettingRepo shgSettingInstance;
    Context context;

    public ShgSettingRepo(Context context) {
        this.context = context;
    }

    public synchronized static ShgSettingRepo getInstance(Context context) {
        if (shgSettingInstance == null) {
            shgSettingInstance = new ShgSettingRepo(context);
        }
        return shgSettingInstance;
    }

    public String str(){
        return "";
    }

    public void insertMasterSaving(MasterSavingEntity entity){
        AppDataBase.getDatabase(context).savingDao().insertAll(entity);
    }
    public List<MasterSavingEntity> getAllSavings(){
        List<MasterSavingEntity> saving = AppDataBase.getDatabase(context).savingDao().getAllSaving();
        return saving;
    }



}
