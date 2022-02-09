package com.nrlm.cbo.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entity.ShgMember;

import java.util.List;

@Dao
public interface ShgMemberDao {

    @Insert
    void inserAll(ShgMember member);

    @Delete
    void deleteAll(ShgMember member);

    @Query("SELECT * FROM shg_member")
    List<ShgMember> loadAllShgMemberList();

    @Query("SELECT COUNT(shg_member_code) FROM shg_member  where  shg_code = :shgCode")
    int memberCount(String shgCode);

    @Query("SELECT * FROM shg_member  where  shg_code = :shgCode")
    List<ShgMember> getmemberBasedShg(String shgCode);


}


