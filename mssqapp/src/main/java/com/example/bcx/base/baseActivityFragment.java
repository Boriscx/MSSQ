package com.example.bcx.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.bcx.mssqapp.R;

/**
 * Created by bcx on 2016/2/2.
 */
public abstract class baseActivityFragment extends AppCompatActivity{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.base_activity_fragment);
        if (fragment==null){
            fragment=createFragment();
            fm.beginTransaction().add(R.id.base_activity_fragment,fragment).commit();
        }
    }
}
