package com.example.bcx.mssqapp;

import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bcx.net.HttpRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class ActivityFragment extends Fragment {

    private static final String TAG="ActivityFragment";

    private static final String PATHER="http://192.168.10.33:8080/MSSQServer/android/loginAction";

    private EditText mEditText;
    private EditText mEditText2;
    private Button mButton;
    private TextView infoText;


    public ActivityFragment() {
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
        View view=inflater.inflate(R.layout.activity_main,container,false);
        mEditText=(EditText)view.findViewById(R.id.editText1);
        mEditText2=(EditText)view.findViewById(R.id.EditText2);
        mButton=(Button)view.findViewById(R.id.TestButton);
        infoText=(TextView)view.findViewById(R.id.infoText);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title=mEditText.getText().toString().trim();
                String timelength=mEditText2.getText().toString().trim();

                Map<String,String> params=new HashMap<String, String>();
                params.put("username",title);
                params.put("password",timelength);
                try {
                    if(HttpRequest.sendGetRequest(PATHER, params)) {

                       infoText.setText("");
                        Log.i(TAG, "success");
                    } else {
                        Log.i(TAG, "failure");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
