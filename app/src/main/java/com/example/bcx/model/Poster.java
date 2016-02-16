package com.example.bcx.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by bcx on 2016/1/31.
 */
public class Poster {

    private UUID mId;
    private String mPoster_title;
    private Date mDate;
    private Replyer mReplyer;

    public Poster(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getPoster_title() {
        return mPoster_title;
    }

    public void setPoster_title(String poster_title) {
        mPoster_title = poster_title;
    }

    public Replyer getReplyer() {
        return mReplyer;
    }

    public void setReplyer(Replyer replyer) {
        mReplyer = replyer;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
