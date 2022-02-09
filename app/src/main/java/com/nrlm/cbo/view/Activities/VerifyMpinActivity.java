package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyMpinActivity extends AppCompatActivity {
    @BindView(R.id.indicator_dots)
    IndicatorDots indicator_dots;
    @BindView(R.id.pin_lock_view)
    PinLockView pin_lock_view;
    AppSharedPreferences appSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mpin);
        ButterKnife.bind(this);
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(VerifyMpinActivity.this);

        pin_lock_view.attachIndicatorDots(indicator_dots);
        pin_lock_view.setPinLength(4);

        String getMpin =appSharedPreferences.getMpinStatus();

        pin_lock_view.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                if(pin.equalsIgnoreCase(getMpin)){
                    Intent intent =new Intent(VerifyMpinActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    pin_lock_view.resetPinLockView();
                    Toast.makeText(VerifyMpinActivity.this,"Entered Mpin is wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {

            }
        });


    }
}