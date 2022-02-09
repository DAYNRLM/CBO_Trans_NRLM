package com.nrlm.cbo.database.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bankentity")
public class BankEntity  {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String bank_code, bank_name, entity_code,acc_length,bank_level_code;





    /*  bankDetails.getAcc_length();
                    bankDetails.getBank_code();
                    bankDetails.getBank_name();
                    bankDetails.getBanklevel_code();
                    bankDetails.getEntity_code();*/
}
