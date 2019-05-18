package com.ip.alexrebega.mbooking.model;


import android.os.Parcel;
import android.os.Parcelable;

public class HotelDto implements Parcelable {

    private int imageId;
    private String mHotelName;
    private String mHotelDesc;
    private int mRooms;
    private double mPrice;
    private float mRating;

    public HotelDto() {
//        imageId = context.getResources.getDrawable...
        mHotelName = "Great HotelDto";
        mHotelDesc = "Great hotel close to city`s center. We have many facilities and superb rooms waiting for you.";
        mRooms = 4;
        mPrice = 132.24;
        mRating = (float)2.5;
    }

    private HotelDto(Parcel in) {

        mHotelName = in.readString();
        mHotelDesc = in.readString();
        imageId = in.readInt();
        mRooms = in.readInt();
        mPrice = in.readDouble();
        mRating = in.readFloat();

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

    public String getmHotelDesc() {
        return mHotelDesc;
    }

    public void setmHotelDesc(String mHotelDesc) {
        this.mHotelDesc = mHotelDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHotelName);
        dest.writeString(mHotelDesc);
        dest.writeInt(imageId);
        dest.writeInt(mRooms);
        dest.writeDouble(mPrice);
        dest.writeFloat(mRating);

    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<HotelDto> CREATOR
            = new Parcelable.Creator<HotelDto>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public HotelDto createFromParcel(Parcel in) {
            return new HotelDto(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public HotelDto[] newArray(int size) {
            return new HotelDto[size];
        }
    };
}
