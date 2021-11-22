package com.ducnb.uigmail.data;

import java.util.Date;

public class Gmail {
    private String mName;
    private int mImage;
    private String mContents;
    private Date mDate;
    public Gmail(String mName,int mImage,String mContents,Date mDate){
        this.mName = mName;
        this.mImage = mImage;
        this.mContents = mContents;
        this.mDate = mDate;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmContents() {
        return mContents;
    }

    public void setmContents(String mContents) {
        this.mContents = mContents;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
