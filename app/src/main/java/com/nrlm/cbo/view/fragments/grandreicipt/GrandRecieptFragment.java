package com.nrlm.cbo.view.fragments.grandreicipt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nrlm.cbo.R;

public class GrandRecieptFragment extends Fragment {

    public GrandRecieptFragment() {
        // Required empty public constructor
    }

    public static GrandRecieptFragment newInstance() {
        GrandRecieptFragment fragment = new GrandRecieptFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grand, container, false);
    }
}