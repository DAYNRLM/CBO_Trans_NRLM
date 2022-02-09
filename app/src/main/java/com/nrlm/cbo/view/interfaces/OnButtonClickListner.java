package com.nrlm.cbo.view.interfaces;

import android.view.View;

import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;

public interface OnButtonClickListner {
    //void onItemClick(View view);
    void notifyDate(String Type, String date, ShgSettingSavingFromMemberEntity savingObject);
}
