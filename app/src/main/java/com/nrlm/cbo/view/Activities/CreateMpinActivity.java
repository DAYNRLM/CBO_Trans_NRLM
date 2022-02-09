package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateMpinActivity extends AppCompatActivity {
    @BindView(R.id.mpinEt)
    PinEntryEditText mpinEt;

    @BindView(R.id.mpinConfirmEt)
    PinEntryEditText mpinConfirmEt;

    @BindView(R.id.createMpinBtn)
    MaterialButton materialButton;

    AppSharedPreferences appSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mpin);
        ButterKnife.bind(this);
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(CreateMpinActivity.this);
        mpinConfirmEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mpin = mpinEt.getText().toString();
                String confirmMpin = mpinConfirmEt.getText().toString();
                if (confirmMpin.length() == 4) {
                    if (!mpin.equalsIgnoreCase(confirmMpin)) {
                        mpinConfirmEt.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mpinConfirmEt.setText(null);
                                Toast.makeText(CreateMpinActivity.this,"Confirm mpin is wrong.", Toast.LENGTH_SHORT).show();
                            }
                        }, 300);
                    }
                }

            }
        });

        mpinConfirmEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String mpin = mpinEt.getText().toString();
                String confirmMpin = mpinConfirmEt.getText().toString();

                if (hasFocus) {
                    if (mpin.isEmpty()) {
                        Toast.makeText(CreateMpinActivity.this, "First enter the Mpin", Toast.LENGTH_SHORT).show();
                        mpinConfirmEt.setText(null);
                    }
                }
            }
        });
    }

    @OnClick(R.id.createMpinBtn)
    public void createMpin() {
        String mpin = mpinEt.getText().toString();
        String confirmMpin = mpinConfirmEt.getText().toString();
        if(mpin.equalsIgnoreCase(confirmMpin)){
            appSharedPreferences.setMpinStatus(confirmMpin);
            Intent intent =new Intent(CreateMpinActivity.this,VerifyMpinActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(CreateMpinActivity.this, "Mpin and confirm Mpin is not matched", Toast.LENGTH_SHORT).show();
            mpinConfirmEt.setText(null);
            mpinEt.setText(null);
        }
    }
    }
