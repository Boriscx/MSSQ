package com.example.bcx.mssq;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bcx.base.SingleFragmentActivity;

public class PosterActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new PosterFragment();
    }
}
