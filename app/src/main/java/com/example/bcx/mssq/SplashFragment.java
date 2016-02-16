package com.example.bcx.mssq;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class SplashFragment extends Fragment {
    private TextView mTextView;

    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_splash,container,false);
        PackageInfo pinfo;
        try {
            pinfo=getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            mTextView=(TextView)v.findViewById(R.id.splashVerText);
            mTextView.setText("v"+pinfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new Thread() {
            @Override
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goMain();
            }

        }.start();
        return v;
    }
    private void goMain(){
        Intent i=new Intent(getActivity(),MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }

}
