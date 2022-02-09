package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MemberCutOffSavingEntity;

import java.util.List;

@Dao
public interface MemberCutOffSavingDao {
    @Insert
    void insertAll(MemberCutOffSavingEntity memberCutOffSavingEntity);

    @Query("select * from MemberCutOffSavingEntity where member_code =:memberCode")
    List<MemberCutOffSavingEntity> getMemberCutOffSavingData(String memberCode);

   @Query("delete from MemberCutOffSavingEntity where member_code=:memberCode and saving_type=:savingTypeName")
     void deleteSavingType(String memberCode,String savingTypeName);

}
