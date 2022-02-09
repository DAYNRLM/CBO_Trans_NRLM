package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.view.fragments.cutOffFragment.ShgRunningInsuranceFragment;
import com.nrlm.cbo.view.fragments.cutOffFragment.ShgRunningLoanFragment;
import com.nrlm.cbo.view.fragments.shgSeetingFragments.AddSavingFragment;
import com.nrlm.cbo.view.fragments.shgSeetingFragments.ShgNominationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgSeetingFormsActivity extends AppCompatActivity {

    @BindView(R.id.tbTitle)
    TextView tbTitle;

    AppSharedPreferences appSharedPreferences;

    String screenStatus ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_seeting_forms);
        ButterKnife.bind(this);

        getAllInstance();

        screenStatus =appSharedPreferences.getSettingScreenStatus();
        if(!screenStatus.equalsIgnoreCase("")){
            redirectToFragment(screenStatus);
        }
    }

    private void getAllInstance() {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ShgSeetingFormsActivity.this);
    }

    private void redirectToFragment(String i) {
        switch (i){
            case "1":
                tbTitle.setText("ADD SHG SAVINGS ");
                AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), AddSavingFragment.newInstance(),AddSavingFragment.class.getName(),false,R.id.settingCommonFramLayout);
                break;
            case "2":
                tbTitle.setText("SHG NOMINATION");
                AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), ShgNominationFragment.newInstance(),ShgNominationFragment.class.getName(),false,R.id.settingCommonFramLayout);
                break;
            case "3":
                break;
        }
    }
}