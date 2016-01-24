package com.example.bcx.criminallntent;

import android.support.v4.app.Fragment;

import com.example.bcx.base.SingleFragmentActivity;

public class CrimeCameraActivity extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
