package com.nrlm.cbo.view.fragments.cutOffFragment;

import com.nrlm.cbo.view.fragments.BaseFragment;
import com.nrlm.cbo.R;
import com.nrlm.cbo.database.AppDataBase;
import com.nrlm.cbo.database.room.entity.AddShgLoan;

import butterknife.OnClick;

public class ShgRunningLoanForm extends BaseFragment {


    public static ShgRunningLoanForm newInstance() {
        ShgRunningLoanForm shgFragment = new ShgRunningLoanForm();
        return shgFragment;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.shg_running_loan_form_frg_layout;
    }

    @Override
    public void onFragmentReady() {

    }

    @OnClick(R.id.runningLoanBtn)
    void addShgLoan() {

        // perform data base operation
        AddShgLoan addShgLoan = new AddShgLoan();
        addShgLoan.setLoanAmount("200");
        addShgLoan.setShgName("joginder");
        AppDataBase.getDatabase(getContext()).addShgLoanDao().insertAll(addShgLoan);
//        AppUtils.getInstance().replaceFragment(getFragmentManager(), ShgRunningLoanFragment.newInstance(), ShgRunningLoanFragment.class.getName(), true, R.id.cutoffFramLayout);
    }

}
