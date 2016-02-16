package com.example.bcx.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by bcx on 2016/1/30.
 */
public class PlateLab {
    private String[] plates_title;
    private ArrayList<Plate> mPlates;
    private Context mContext;
    private static PlateLab sPlateLab;
    private PlateIntentJSONSerializer mSerializer;
    private static final String FILENAME="plate.json";
    private static final String TAG="PlateLab";

    public PlateLab(Context context){
        mContext=context;
        plates_title= new String[]{"无美食不成活", "一如烘培深似海","良食","瘦成一道闪电","有娃后的新生活","无国界美食与旅行",
                "青草摄影社","优食汇服务站","生活中的小记录","Family Day同城聚会","帮助青草进步"};


        mSerializer=new PlateIntentJSONSerializer(mContext,FILENAME);
        try{
            if ((mPlates=mSerializer.loadPlate())!=null){
                Log.d(TAG, "加载本地数据成功！");
            }
            else{
                mPlates=new ArrayList<Plate>();
                newPlates();
                mSerializer.savePlate(mPlates);
                Log.d(TAG,"没有本地数据！新建数据成功！");
            }
        }catch (Exception e){
            mPlates=new ArrayList<Plate>();
            Log.e(TAG,"加载本地数据失败！",e);
            e.printStackTrace();
        }

    }
    public ArrayList<Plate> getPlates(){
        return mPlates;
    }

    public Plate getPlate(UUID id){
        for(Plate p:mPlates){
            if (p.getPlate_id().equals(id)){
                return p;
            }
        }
        return null;
    }
    public static PlateLab get(Context context){
        if (sPlateLab==null){
            sPlateLab=new PlateLab(context.getApplicationContext());
        }
        return sPlateLab;
    }

    public boolean saveCrimes(){
        try{
            mSerializer.savePlate(mPlates);
            Log.d(TAG,"板块信息保存到本地文件成功！");
            return true;
        }catch (Exception E){
            Log.e(TAG, "板块信息保存到本地失败:", E);
            return false;
        }
    }
    private void newPlates(){
        for (int i=0;i<plates_title.length;i++){
            Plate plate=new Plate(plates_title[i].toString());
            mPlates.add(plate);
        }
    }
}
