package com.nrlm.cbo.database.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.ShgSeetingSavings;

import java.util.List;

@Dao
public interface ShgSavingDao {

    @Insert
    void inserAll(ShgSeetingSavings saving);

    @Query("SELECT * FROM shg_seeting  where  saving_shg_code = :shgCode")
    List<ShgSeetingSavings> getSavingShg(String shgCode);
}
