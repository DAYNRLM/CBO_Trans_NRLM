package com.nrlm.cbo.view.interfaces;

import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;

public interface OnClickListnerForRunningInsurance {
    void notifyDate(String Type, ShgCutOffRunningInsuranceEntity insuranceObject);
}
