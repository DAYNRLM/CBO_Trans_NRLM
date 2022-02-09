package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.LoginEntity;

import java.util.List;

@Dao
public interface LoginDao {
    @Insert
    void insertAll(LoginEntity loginEntity);

    @Query("Select * from loginentity")
    List<LoginEntity> getLoginInfo();
}
