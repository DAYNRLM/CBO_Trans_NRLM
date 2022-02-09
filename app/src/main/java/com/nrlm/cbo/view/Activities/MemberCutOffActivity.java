package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.view.fragments.cutOffFragment.memberCuttOff.MemberCutOffFragment;

public class MemberCutOffActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_cut_off);
        AppUtils.getInstance().replaceFragment(getSupportFragmentManager(),MemberCutOffFragment.newInstance(),MemberCutOffActivity.class.getName(),false,R.id.frame_layout_member_cut_off);
    }
}