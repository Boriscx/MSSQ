package com.example.bcx.mssq;

import android.content.pm.PackageInfo;
import android.support.v4.app.Fragment;

import com.example.bcx.base.SingleFragmentActivity;

public class PlateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        PackageInfo packageInfo;
        return new PlateFragment();
    }
}
