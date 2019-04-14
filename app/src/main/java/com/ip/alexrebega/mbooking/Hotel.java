package com.ip.alexrebega.mbooking;


public class Hotel {

    private int imageId;
    private String mHotelName;
    private int mRooms;
    private double mPrice;
    private float mRating;

    public Hotel() {
//        imageId = context.getResources.getDrawable...
        mHotelName = "Great Hotel";
        mRooms = 4;
        mPrice = 132.24;
        mRating = (float)2.5;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getmHotelName() {
        return mHotelName;
    }

    public void setmHotelName(String mHotelName) {
        this.mHotelName = mHotelName;
    }

    public int getmRooms() {
        return mRooms;
    }

    public void setmRooms(int mRooms) {
        if(mRooms < 0)
            this.mRooms = 0;
        else
            this.mRooms = mRooms;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        if(mPrice < 0)
            this.mPrice = 0;
        else
            this.mPrice = mPrice;
    }

    public float getmRating() {
        return mRating;
    }

    public void setmRating(float mRating) {
        this.mRating = mRating;
    }

}

