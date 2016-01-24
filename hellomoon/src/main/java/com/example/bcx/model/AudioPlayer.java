package com.example.bcx.model;

import android.content.Context;
import android.media.*;

import com.example.bcx.hellomoon.R;

/**
 * Created by bcx on 2016/1/9.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;
    public void stop(){
        if (mPlayer!=null){
            mPlayer.release();
            mPlayer=null;
        }
    }
    public void play(Context context){
        stop();
        mPlayer=MediaPlayer.create(context, R.raw.youinmysong);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });

        mPlayer.start();
    }


}
