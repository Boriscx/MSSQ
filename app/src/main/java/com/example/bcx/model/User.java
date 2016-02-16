package com.example.bcx.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by bcx on 2016/1/31.
 */
public class User {
    private UUID mId;  //用户唯一标识ID
    private String mName;//用户昵称
    private String mSex;    //用户性别
    private String mPhoneNumber;//用户手机号码
    private String mEmail;//用户邮箱
    private String mPassword; //用户密码
    private Date mDate; //注册时间

    public User(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }


    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Date getDate() {
        return mDate;
    }
}
