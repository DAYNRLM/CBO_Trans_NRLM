package com.nrlm.cbo.view.fragments.cutOffFragment;

import android.widget.TextView;

import com.nrlm.cbo.view.fragments.BaseFragment;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.OnClick;

public class SHGCutOffFragment extends BaseFragment {
    @BindView(R.id.cutOffSubmitBtn)
    MaterialButton cutOffSubmitBtn;

    @BindView(R.id.shgnameTv)
    TextView shgnameTv;

    @BindView(R.id.shgCodeTv)
    TextView shgCodeTv;

    AppSharedPreferences appSharedPreferences;


    public static SHGCutOffFragment newInstance() {
        SHGCutOffFragment shgFragment = new SHGCutOffFragment();
        return shgFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_cutoff_fragment_layout;
    }

    @Override
    public void onFragmentReady() {
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(getContext());
        String shgCode = appSharedPreferences.getShgCode();
        shgnameTv.setText(AllDataList.getInstance(getContext()).getShgUniqName(shgCode));
        shgCodeTv.setText(shgCode);

    }

    @OnClick(R.id.cutOffSubmitBtn)
    void addRunningLoanClick() {

        AppUtils.getInstance().replaceFragment(getFragmentManager(), ShgRunningLoanFragment.newInstance(), ShgRunningLoanFragment.class.getName(), true, R.id.cutoffFramLayout);
    }
}
