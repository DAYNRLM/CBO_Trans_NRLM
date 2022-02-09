package com.nrlm.cbo.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.Shg;

import java.util.List;

@Dao
public interface ShgDao {
    @Insert
    void inserAll(Shg shg);

    @Delete
    void deleteAll(Shg shg);

    @Query("SELECT * FROM Shg")
    List<Shg> loadAllShgList();

    @Query("SELECT * FROM Shg  where  village_code = :villageCode")
    List<Shg> loadAllShgWithVillage(String villageCode);

    @Query("SELECT shg_name FROM Shg WHERE shg_code =:shgCode ")
    String getShgname(String shgCode);

    @Query("UPDATE Shg SET shg_verify_status = :status WHERE shg_code = :shgCode")
    void updateVerificationStatus(String shgCode,String status);

    @Query("SELECT shg_verify_status FROM Shg WHERE shg_code =:shgCode ")
    String getVerificationStatus(String shgCode);

}
