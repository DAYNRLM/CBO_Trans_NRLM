package com.nrlm.cbo.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDatePicker extends DialogFragment {
    private Context context;
    private Calendar MinDate, MaxDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DatePickerDialog dd;
    DatePicker dp;


    public MyDatePicker() {
    }

    public MyDatePicker(DatePickerDialog.OnDateSetListener callback, Calendar MinDate, Calendar MaxDate, Context context) {
        mDateSetListener = callback;
        this.MinDate = MinDate;
        this.MaxDate = MaxDate;
        this.context = context;
    }
    public MyDatePicker(DatePickerDialog.OnDateSetListener callback, Context context) {
        mDateSetListener = callback;
        this.context = context;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();

        dd = new DatePickerDialog(getActivity(), this.mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dd.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                try {
                    if (MinDate!=null&&MaxDate!=null) {
                        ((DatePickerDialog) dialog).getDatePicker().setMaxDate(MaxDate.getTimeInMillis());
                        ((DatePickerDialog) dialog).getDatePicker().setMinDate(MinDate.getTimeInMillis());

                    }
                } catch (NullPointerException e) {
                    dialog.dismiss();
                    e.printStackTrace();
                }
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return dd;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
