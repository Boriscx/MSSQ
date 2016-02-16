package com.example.bcx.dao;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by bcx on 2016/1/29.
 */
@SuppressWarnings("deprecation")
public class PictureUtils {
    public static BitmapDrawable getScaledDrawable(Activity a,String path){
        Display display=a.getWindowManager().getDefaultDisplay();
        float destwidth=display.getWidth();
        float destheight=display.getHeight();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth=options.outWidth;
        float srcheight=options.outHeight;

        int inSampleSize=1;
        if (srcheight>destheight||srcWidth>destwidth){
            if(srcWidth>srcheight){
                inSampleSize=Math.round(srcheight/destheight);
            }else{
                inSampleSize=Math.round(srcWidth/destwidth);
            }
        }
        options=new BitmapFactory.Options();
        options.inSampleSize=inSampleSize;
        Bitmap bitmap=BitmapFactory.decodeFile(path,options);
        return new BitmapDrawable(a.getResources(),bitmap);
    }
    public static void cleanImageView(ImageView imageView){
        if (!(imageView.getDrawable() instanceof BitmapDrawable)){
            return;
        }
        BitmapDrawable bitmapDrawable=(BitmapDrawable)imageView.getDrawable();
        bitmapDrawable.getBitmap().recycle();
        imageView.setImageDrawable(null);
    }
}
