package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberInfoAndSavingsEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.model.response.ShgMemberDataResponse;

import java.util.List;

@Dao
public interface ShgMemberDataDao {

    @Insert
    void insertAll(ShgMemberDataEntity shgMemberDataEntity);

    @Query("SELECT COUNT(shgMemberCode) FROM ShgMemberDataEntity  where  shgCode = :shgCode")
    int memberCount(String shgCode);

    @Query("SELECT * FROM ShgMemberDataEntity  where  shgCode = :shgCode")
    List<ShgMemberDataEntity> getAllMember(String shgCode);

      /*        @Query("SELECT * FROM book " +
                            "INNER JOIN loan ON loan.book_id = book.id " +
                            "INNER JOIN user ON user.id = loan.user_id " +
                            "WHERE user.name LIKE :userName")*/
/*    @Query("")
    List<>getMemberInfoAndSavings(String shgCode);*/

    @Query("select shgMemberCode,memberName,belongingName from shgmemberdataentity where entityCode=:entityCode and shgCode=:shgCode ")
    List<MemberInfoAndSavingsEntityDataModel> getMemberInfo(String entityCode, String shgCode);

    @Query("select * from ShgMemberDataEntity where shgMemberCode =:memberCode")
    ShgMemberDataEntity getShgMemberObj(String memberCode);
}
