package com.example.bcx.net;

import android.util.Log;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by bcx on 2016/2/2.
 */
public class HttpRequest {

    private HttpURLConnection connection;
    private static int TEN=100;
    private static final String TAG="HttpRequest";

    public static boolean sendGetRequest(String path,Map<String,String> params)throws IOException{
        StringBuilder sb=new StringBuilder(path);
        sb.append('?');
        for (Map.Entry<String ,String > entry:params.entrySet()){
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        sb.deleteCharAt(sb.length()-1);

        Log.i(TAG,"path为:"+sb.toString());
        URL url=new URL(sb.toString());
        final HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        Log.i(TAG, "开始传递数据！");
        connection.setReadTimeout(5 * 1000);
        new Thread(){

            @Override
            public void run(){
                try {
                    TEN=connection.getResponseCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Log.i(TAG, "传递数据结束！");
        if (TEN==200){
            return true;
        }
        return false;
    }
}
