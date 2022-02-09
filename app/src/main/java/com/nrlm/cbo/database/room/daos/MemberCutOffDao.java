package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MemberCutOffEntity;

import java.util.List;

@Dao
public interface MemberCutOffDao {
    @Insert
    void insertAll(MemberCutOffEntity memberCutOffEntity);

    @Query("select * from MemberCutOffEntity where member_code =:memberCode")
    MemberCutOffEntity getData(String memberCode);

    @Query("select * from MemberCutOffEntity where member_cutOff_sync_status=:status")
    List<MemberCutOffEntity> getNotSyncData(String status);

}
