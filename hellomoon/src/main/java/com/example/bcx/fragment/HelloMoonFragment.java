package com.example.bcx.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bcx.hellomoon.R;
import com.example.bcx.model.AudioPlayer;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bcx on 2016/1/9.
 */
public class HelloMoonFragment extends Fragment {
    private Button mPlayButton;
    private Button mStopButton;
    private TextView mTextView;
    private String mUrl="http://localhost:8080/80111/files/index.jsp";
    private AudioPlayer mPlayer=new AudioPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,
                             Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.fragment_hello_moon,parent,false);

        mTextView=(TextView)v.findViewById(R.id.textview);
        mPlayButton=(Button)v.findViewById(R.id.hellomoon_playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.play(getActivity());

            }
        });
        mStopButton=(Button)v.findViewById(R.id.hellomoon_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });
        return v;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop();
    }


}
