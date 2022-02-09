package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.view.fragments.cutOffFragment.ShgRunningInsuranceFragment;
import com.nrlm.cbo.view.fragments.cutOffFragment.ShgRunningLoanFragment;
import com.nrlm.cbo.view.fragments.shgSeetingFragments.ShgSeetingFrag;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgCutoffCommonActivity extends AppCompatActivity {

    @BindView(R.id.tbTitle)
    TextView tbTitle;

    AppSharedPreferences appSharedPreferences;

    String screenStatus ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_cutoff_common);
        ButterKnife.bind(this);

        getAllInstance();
        screenStatus =appSharedPreferences.getCutoffScreenStatus();
        if(!screenStatus.equalsIgnoreCase("")){
            redirectToFragment(screenStatus);
        }




    }

    private void getAllInstance() {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ShgCutoffCommonActivity.this);
    }

    private void redirectToFragment(String i) {
        switch (i){
            case "1":
                tbTitle.setText("Add SHG Running Loan ");
                AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), ShgRunningLoanFragment.newInstance(),ShgRunningLoanFragment.class.getName(),false,R.id.cutoffCommonFramLayout);

                break;
            case "2":
                tbTitle.setText("Add SHG Running Insurance ");
                AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), ShgRunningInsuranceFragment.newInstance(),ShgRunningInsuranceFragment.class.getName(),false,R.id.cutoffCommonFramLayout);
                break;
            case "3":
                break;
        }
    }
}