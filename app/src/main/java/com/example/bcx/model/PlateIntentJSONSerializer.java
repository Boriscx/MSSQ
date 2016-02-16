package com.example.bcx.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by bcx on 2016/1/20.
 */
public class PlateIntentJSONSerializer {
    private Context mContext;
    private String mFilename;
    private String TAG="PlateIntentJSONSerializer";

    public PlateIntentJSONSerializer(Context context, String f){
        mContext=context;
        mFilename=f;
    }
    public void savePlate(ArrayList<Plate> plates) throws JSONException,IOException{
        JSONArray array=new JSONArray();
        for (Plate c: plates){
            array.put(c.toJSON());
        }
        Writer writer=null;
        try{
            OutputStream out=mContext.openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer=new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if (writer!=null){
                writer.close();
            }
        }
    }

    public ArrayList<Plate> loadPlate() throws IOException,JSONException{
        ArrayList<Plate> plates=new ArrayList<Plate>();
        BufferedReader reader=null;
        try{
            InputStream in=mContext.openFileInput(mFilename);
            reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                jsonString.append(line);
            }
            JSONArray array=(JSONArray)new JSONTokener(jsonString.toString()).nextValue();
            for (int i=0;i<array.length();i++){
                plates.add(new Plate(array.getJSONObject(i)));
            }
            Log.d(TAG,"读取本地数据完毕！");
        }catch (FileNotFoundException e){
            plates=null;
            Log.e(TAG,"读取本地数据失败！",e);
        }finally {
            if(reader!=null)
                reader.close();
        }
        return plates;
    }
}
