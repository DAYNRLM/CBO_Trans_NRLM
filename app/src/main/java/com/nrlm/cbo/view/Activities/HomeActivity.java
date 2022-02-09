package com.nrlm.cbo.view.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, NavigationView.OnNavigationItemSelectedListener {
    private HomeActivity.OnBackPressedListener onBackPressedListener;
    public Toolbar mToolbar;
    public NavigationView navigationView;
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView tvToolbarTitle,tvUserName, tvUserMobile;
    private boolean mChangeFragment;
    private int selectedItem;
    public static Context context;
    @BindView(R.id.settings_dsboard)
    CardView settingDashboard;
    @BindView(R.id.attendance_dsboardM)
    CardView attendanceDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        context=this;
        //AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), DashBoardFragment.newInstance(), DashBoardFragment.class.getName(), true, R.id.fragmentContainer);
       setupToolbar();
       setupNavigationView();
    }

    @OnClick(R.id.attendance_dsboardM)
    public void onAttendance()
    {
        //sNavigationDrawer.setAppbarTitleTV("jjjjjjjjjjjjjjj");
        Toast.makeText(HomeActivity.this,"mubark ho",Toast.LENGTH_LONG);
        AppUtils.getInstance().makeIntent(HomeActivity.this,AttendanceSelectLocationActivity.class,false);
        //View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_navigation_drawer, this, true);
        //titletv.setText("Attendance");
        //titleTv1.setText("Attendance");
    }

    @OnClick(R.id.savingCV)
    public void onSavingClick () {
        AppUtils.getInstance().makeIntent(HomeActivity.this, SavingActivity.class, false);
    }
    @OnClick(R.id.recieptsCV)
    public void onRecieptsClick () {
        AppUtils.getInstance().makeIntent(HomeActivity.this, RecieptsActivity.class, false);
    }
    @OnClick(R.id.paymentCV)
    public void onPaymentClick () {
        AppUtils.getInstance().makeIntent(HomeActivity.this, PaymentActivity.class, false);
    }
    @OnClick(R.id.settings_dsboard)
    public void onSettingsClick () {
        Intent newIntent = new Intent(HomeActivity.this, ShgSeetingActivity.class);
        startActivity(newIntent);
    }
    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarTitle = (TextView) findViewById(R.id.tbTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        tvToolbarTitle.setText(getResources().getString(R.string.home));
    }
    private void setupNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerLayout.setScrimColor(Color.parseColor("#000000"));
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        View headerView = navigationView.getHeaderView(0);
        tvUserName = (TextView) headerView.findViewById(R.id.tvUserName);
        tvUserName.setText("ABC");
        tvUserMobile = (TextView) headerView.findViewById(R.id.tvUserMobileNumber);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
        if (mChangeFragment) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mChangeFragment = false;
                    switch (selectedItem) {
                        case R.id.home: {
                            tvToolbarTitle.setText(getResources().getString(R.string.home));
                           // AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), DashBoardFragment.newInstance(), DashBoardFragment.class.getName(), true, R.id.fragmentContainer);
                            break;
                        }
                        case R.id.savings: {
                            AppUtils.getInstance().showLog("Savings is clicked",HomeActivity.class);
                            break;
                        }
                        case R.id.verification: {
                            AppUtils.getInstance().showLog("Verification is clicked",HomeActivity.class);
                            break;
                        }
                        case R.id.settings: {
                            tvToolbarTitle.setText(getResources().getString(R.string.settings));
                           // AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), SettingsFragment.newInstance(),SettingsFragment.class.getName(),true,R.id.fragmentContainer);
                            break;
                        }

                        case R.id.member_cut_off:
                        {
                            AppUtils.getInstance().makeIntent(HomeActivity.this,MemberCutOffActivity.class,true);
                            break;
                        }
                        case R.id.cut_off: {
                            AppUtils.getInstance().makeIntent(HomeActivity.this, CutOffActivity.class, true);
                            break;
                        }

                        case R.id.attendance: {
                            tvToolbarTitle.setText(getResources().getString(R.string.attendance));
                           // AppUtils.getInstance().replaceFragment(getSupportFragmentManager(), AttendanceFragment.newInstance(),AttendanceFragment.class.getName(),true,R.id.fragmentContainer);
                            break;
                        }
                        case R.id.receipts: {
                            AppUtils.getInstance().showLog("Receipts is clicked",HomeActivity.class);
                            break;
                        }
                        case R.id.payment: {
                            AppUtils.getInstance().showLog("Payment is clicked",HomeActivity.class);
                            break;
                        }
                        case R.id.logout: {
                            AppUtils.getInstance().showLog("Logout is clicked",HomeActivity.class);
                            break;
                        }
                    }
                }
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                AppUtils.hideSoftKeyboard(HomeActivity.this);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportFragmentManager().addOnBackStackChangedListener(this::onBackStackChanged);
    }

    @Override
    public void onBackStackChanged() {
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        AppUtils.getInstance().showLog("id"+item.getItemId(), HomeActivity.class);
        item.setChecked(!item.isChecked());
        selectedItem = item.getItemId();
        mDrawerLayout.closeDrawers();
        mChangeFragment = true;
        return true;
    }


    public interface OnBackPressedListener {
        void doBack();
    }
    public  void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (onBackPressedListener != null) {
                onBackPressedListener.doBack();
            } else {
                super.onBackPressed();
            }
        }
    }

}

