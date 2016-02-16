package com.example.bcx.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by bcx on 2016/1/31.
 */
public class Replyer {
    private UUID mId;
    private User mUser;
    private String mText;
    private Date mDate;
    private Integer mInteger=0;

    public Replyer(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Integer getInteger() {
        return mInteger;
    }

    public void setInteger(Integer integer) {
        mInteger = integer;
    }
}
