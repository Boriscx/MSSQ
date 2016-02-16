package com.example.bcx.mssqapp;

import android.support.v4.app.Fragment;

import com.example.bcx.base.baseActivityFragment;

public class MainActivity extends baseActivityFragment {


    @Override
    protected Fragment createFragment() {
        return new ActivityFragment();
    }
}
