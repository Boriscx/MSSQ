package com.example.bcx.criminallntent;


import android.annotation.TargetApi;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.hardware.camera2.*;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.util.Log;


import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.io.IOException;
import java.util.List;

public class CrimeCameraFragment extends Fragment {


    private static final String TAG="CrimeCameraFragment";

    private Camera mCamera;

    private CameraManager mCameraManager;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private Handler mHandler;
    private ImageReader mImageReader;
    private String mCameraId;
    private ImageReader.OnImageAvailableListener mOnImageAvailableListener;


    @SuppressWarnings("")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_crime_camera,parent,false);

        Button takePictuieButton=(Button)v.findViewById(R.id.crime_camera_takePictureButton);
        takePictuieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        mCameraManager=(CameraManager)getActivity().getSystemService(Context.CAMERA_SERVICE);
        mSurfaceView=(SurfaceView)v.findViewById(R.id.crime_camera_suifaceView);
        mSurfaceHolder=mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                initCameraAndPreview();
            }
        });

        private void initCameraAndPreview() {
            Log.d("linc","init camera and preview");
            HandlerThread handlerThread = new HandlerThread("Camera2");
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
            try {
                mCameraId = ""+CameraCharacteristics.LENS_FACING_FRONT;
                mImageReader = ImageReader.newInstance(mSurfaceView.getWidth(), mSurfaceView.getHeight(),
                        ImageFormat.JPEG,/*maxImages*/7);
                mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mHandler);

                mCameraManager.openCamera(mCameraId, DeviceStateCallback, mHandler);
            } catch (CameraAccessException e) {
                Log.e("linc", "open camera failed." + e.getMessage());
            }
        }

        /*SurfaceHolder holder=mSurfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback2() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    if (mCamera!=null){
                        mCamera.setPreviewDisplay(holder);
                    }
                } catch (IOException e) {
                    Log.e(TAG,"开启显示失败！",e);
                    e.printStackTrace();
                }
            }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if(mCamera==null) return;
                    Camera.Parameters parameters=mCamera.getParameters();
                    Camera.Size s=getBestSupportedSize(parameters.getSupportedPreviewSizes(),width,height);
                    parameters.setPreviewSize(s.width, s.height);
                    mCamera.setParameters(parameters);
                try{
                    mCamera.startPreview();
                }catch (Exception e){
                    Log.e(TAG,"未能开始preview",e);
                    mCamera.release();
                    mCamera=null;
                }

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(mCamera!=null){
                    mCamera.stopPreview();
                }
            }

            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }
        });*/
        return v;
    }



    private Camera.Size getBestSupportedSize(List<Camera.Size> sizes,int width,int height){
        Camera.Size bestSize=sizes.get(0);
        int largestArea=bestSize.width*bestSize.height;
        for (Camera.Size s:sizes){
            int area=s.width*s.height;
            if(area>largestArea){
                bestSize=s;
                largestArea=area;
            }
        }
        return bestSize;
    }

    @TargetApi(9)
    @Override
    public void onResume(){
        super.onResume();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD){
            mCamera= Camera.open(0);
        } else {
            mCamera= Camera.open();
        }
    }


    @Override
    public void onPause(){
        super.onResume();
        if(mCamera!=null){
            mCamera.release();
            mCamera=null;
        }
    }
}
