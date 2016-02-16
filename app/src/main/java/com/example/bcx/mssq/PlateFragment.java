package com.example.bcx.mssq;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bcx.model.Plate;
import com.example.bcx.model.PlateLab;


/**
 * A simple {@link Fragment} subclass. 板块显示
 */
public class PlateFragment extends Fragment {
    private ImageView mImageView;
    private Plate mPlate;
    private PlateLab mPlateLab;
    private Button mButton;
    private TextView tv;

    public PlateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.plate_fragment, container, false);
        mImageView = (ImageView) v.findViewById(R.id.mssq_plate_picture);
        mButton = (Button) v.findViewById(R.id.mssq_plate_testButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Fragment fragment=new PosterFragment();
                fragment.onCreate(savedInstanceState);*/
                Intent i = new Intent(getActivity(), PosterActivity.class);
                startActivity(i);
            }
        });
        PackageInfo pinfo;
        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            mButton.setText("test"+pinfo.versionName);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }

        return v;
    }

}
