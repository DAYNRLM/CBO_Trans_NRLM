package com.nrlm.cbo.view.fragments;

import android.content.Intent;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.nrlm.cbo.view.Activities.HomeActivity;
import com.nrlm.cbo.view.Activities.PaymentActivity;
import com.nrlm.cbo.view.Activities.RecieptsActivity;
import com.nrlm.cbo.view.Activities.ShgSeetingActivity;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class DashBoardFragment extends BaseFragment implements HomeActivity.OnBackPressedListener {
    private int exit = 0;
    @BindView(R.id.settings_dsboard)
    CardView settingDashboard;

    @BindView(R.id.attendance_dsboard)
    CardView attendanceDashboard;


    @BindView(R.id.recieptsCV)
    CardView recieptsCV;


    public static DashBoardFragment newInstance() {
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        return dashBoardFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void onFragmentReady() {

    }


   /*     @OnClick(R.id.savingCV)
        public void onSavingClick () {
            AppUtils.getInstance().makeIntent(getContext(), SavingActivity.class, false);
        }*/

        @OnClick(R.id.recieptsCV)
        public void onRecieptsClick () {
            AppUtils.getInstance().makeIntent(getContext(), RecieptsActivity.class, false);
        }

        @OnClick(R.id.paymentCV)
        public void onPaymentClick () {
            AppUtils.getInstance().makeIntent(getContext(), PaymentActivity.class, false);
        }


        @OnClick(R.id.settings_dsboard)
        public void onSettingsClick () {
            Intent newIntent = new Intent(getContext(), ShgSeetingActivity.class);
            startActivity(newIntent);
        }

        @OnClick(R.id.attendance_dsboard)
        public void onAttendanceClick () {
            Toast.makeText(getContext(), "mubark ho", Toast.LENGTH_LONG);
/*
            AppUtils.getInstance().replaceFragment(getFragmentManager(), AttendanceFragment.newInstance(), AttendanceFragment.class.getName(), true, R.id.fragmentContainer);
*/
        }

        @Override
        public void doBack () {
            if (exit == 0) {
                Toast.makeText(getContext().getApplicationContext(), getString(R.string.toast_exit_app), Toast.LENGTH_LONG).show();
                exit += 1;
            } else
                System.exit(0);
        }

}
