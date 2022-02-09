package com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings;

import androidx.room.ColumnInfo;

public class MemberSavingTypeEntityDataModel {

    @ColumnInfo(name = "saving_type")
    private String saving_type;

    public String getSaving_type() {
        return saving_type;
    }

    public void setSaving_type(String saving_type) {
        this.saving_type = saving_type;
    }
}
