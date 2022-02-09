package com.nrlm.cbo.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        this.onFragmentReady();
        //ViewDataBinding binding = DataBindingUtil.inflate(inflater, this.getFragmentLayout(), container, false);
        //this.onFragmentReady();
        //binding.getRoot();
        return view;

/*
        this.onFragmentReady();
        return DataBindingUtil.inflate(inflater,getFragmentLayout(),container,false).getRoot();*/
    }

    abstract public int getFragmentLayout();

    abstract public void onFragmentReady();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
