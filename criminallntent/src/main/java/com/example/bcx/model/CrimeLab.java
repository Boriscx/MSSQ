package com.example.bcx.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by bcx on 2016/1/8.
 */
public class CrimeLab {
    private static final String TAG="CrimeLab";
    private static final String FILENAME="Crimes.json";
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;
    private CriminalIntentJSONSerializer mSerializer;



    private CrimeLab(Context appContext){
        mAppContext=appContext;

        mSerializer=new CriminalIntentJSONSerializer(mAppContext,FILENAME);
        try{
            if ((mCrimes=mSerializer.loadCrimes())!=null){
                Log.d(TAG,"加载本地数据成功！");
            }
            else{
                mCrimes=new ArrayList<Crime>();
                Log.d(TAG,"没有本地数据！");
            }

        }catch (Exception e){
            mCrimes=new ArrayList<Crime>();
            Log.e(TAG,"Error loading crimes:",e);
        }
        /*for (int i=0;i<100;i++){
            Crime crime=new Crime();
            crime.setTitle("坏习惯"+i);
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }*/

    }
    public boolean saveCrimes(){
        try{
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG,"Crimes saved to file");
            return true;
        }catch (Exception E){
            Log.e(TAG,"Error saving crimes:",E);
            return false;
        }
    }

    public void addCrime(Crime crime){
        mCrimes.add(crime);
    }

    public static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab=new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID uuid){
        for (Crime crime:mCrimes){
            if (crime.getId().equals(uuid)){
                return crime;
            }
        }
        return null;
    }
    public void deletCrime(Crime c){
        mCrimes.remove(c);
        saveCrimes();
    }
}
