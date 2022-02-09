package com.nrlm.cbo.database.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyBranchEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyNameEntity;

import java.util.List;

@Dao
public interface MasterCompanyBranchNamedao {
    @Insert
    void insertAll(MasterCutoffCompanyBranchEntity masterCutoffCompanyBranchEntity);

    @Query("select * from MasterCutoffCompanyBranchEntity where company_name_id=:companyId")
    List<MasterCutoffCompanyBranchEntity> getAllData(String companyId);

}
