package com.example.bcx.criminallntent;


import android.annotation.TargetApi;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bcx.dao.PictureUtils;
import com.example.bcx.model.Crime;
import com.example.bcx.model.CrimeLab;
import com.example.bcx.model.Photo;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mSave;
    private Button mDelete;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    public static final String EXTRA_CRIME_ID = "com.example.bcx.model.crime_id";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO=1;
    private static final String TAG="CrimeFragment";
    private static final String DIALOG_IMAGE="image";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        setHasOptionsMenu(true);
    }

    //此处注解是为了向菜单栏添加向上返回按钮的向下兼容问题，说明方法来自API11的版本
    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();
        /*mDateButton.setEnabled(false);*/
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });


        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        mSave=(Button)v.findViewById(R.id.crime_button_save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrimeLab.get(getActivity()).saveCrimes();
                getActivity().finish();
            }
        });

        mDelete=(Button)v.findViewById(R.id.crime_button_delete);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrimeLab.get(getActivity()).deletCrime(mCrime);
                getActivity().finish();
            }
        });
        mPhotoButton=(ImageButton)v.findViewById(R.id.crime_imageButton);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),CrimeCameraActivity.class);
                startActivityForResult(i,REQUEST_PHOTO);
            }
        });

        mPhotoView=(ImageView)v.findViewById(R.id.crime_ImageView);
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo p=mCrime.getPhoto();
                if(p==null){
                    return;
                }
                FragmentManager fm=getActivity().getSupportFragmentManager();
                String path=getActivity().getFileStreamPath(p.getFilename()).getAbsolutePath();
                ImageFragment.newInstance(path).show(fm,DIALOG_IMAGE);


            }
        });
        PackageManager pm=getActivity().getPackageManager();
        boolean hasACamera=pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)||
                pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)||
                Build.VERSION.SDK_INT<Build.VERSION_CODES.GINGERBREAD||
                android.hardware.Camera.getNumberOfCameras()>0;
        if (!hasACamera){
            mPhotoButton.setEnabled(false);
        }
        return v;
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //获得图片显示
    private void showPhoto(){
        Photo photo=mCrime.getPhoto();
        BitmapDrawable bitmapDrawable=null;
        if(photo!=null){
            String path=getActivity().getFileStreamPath(photo.getFilename()).getAbsolutePath();
            bitmapDrawable= PictureUtils.getScaledDrawable(getActivity(),path);
        }
        mPhotoView.setImageDrawable(bitmapDrawable);
    }

    @Override
    public void onStart(){
        super.onStart();
        showPhoto();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != FragmentActivity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }else if (requestCode==REQUEST_PHOTO){
            String filename=data.getStringExtra(CrimeCameraFragment.EXTRA_PHOTO_FILENAME);
            if (filename!=null){
                Photo photo=new Photo(filename);
                mCrime.setPhoto(photo);
                showPhoto();
                Log.i(TAG,"Crime:"+mCrime.getTitle()+" has photo ,filename is "+filename);
            }
        }
    }

    public void updateDate() {
        mDateButton.setText(mCrime.infoDate(mCrime.getDate()).toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        CrimeLab.get(getActivity()).saveCrimes();
    }

    @Override
    public void onStop(){
        super.onStop();
        PictureUtils.cleanImageView(mPhotoView);
    }


}
