package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.view.fragments.verificationFragment.ShgVerificationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShgVerificationActivity extends AppCompatActivity {

    @BindView(R.id.tbTitle)
    TextView tbTitle;

    AppUtils appUtils;
    AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_verification);
        ButterKnife.bind(this);
        getAllInstance();
        tbTitle.setText("SHG Verification");

       appUtils.replaceFragment(getSupportFragmentManager(),
                ShgVerificationFragment.newInstance(),
               ShgVerificationFragment.class.getName(),
               true, R.id.verificationFramContainer);
    }

    private void getAllInstance() {
        appUtils =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(ShgVerificationActivity.this);
    }
}