package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberSavingTypeEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;

import java.util.List;

@Dao
public interface ShgSettingSavingFromMemberDao {

    @Insert
    void insert(ShgSettingSavingFromMemberEntity shgSettingSavingFromMemberEntity);

    @Query("select saving_type from shgsettingsavingfrommemberentity where shg_code=:shgCode")
     List<MemberSavingTypeEntityDataModel> getSettingSavingType(String shgCode);

    @Query("select * from ShgSettingSavingFromMemberEntity where shg_code =:shgCode")
    List<ShgSettingSavingFromMemberEntity> getAllSaving(String shgCode);


    @Query("delete from ShgSettingSavingFromMemberEntity where shg_code =:shgCode and saving_type=:typeid")
    void deleteSavingType(String shgCode,String typeid);

    @Query("UPDATE ShgSettingSavingFromMemberEntity SET amount=:amount, roi = :roi WHERE shg_code = :shgCode and saving_type=:savingTypeId")
    void updateSaving(String shgCode,String savingTypeId,String amount,String roi);


}
