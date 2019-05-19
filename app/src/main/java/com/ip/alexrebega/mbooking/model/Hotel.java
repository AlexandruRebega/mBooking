package com.ip.alexrebega.mbooking.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Reservation.class,
        parentColumns = "uid",
        childColumns = "uid",
        onDelete = CASCADE))
public class Hotel {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "image_id")
    public int imageId;
    @ColumnInfo(name = "name")
    public String mHotelName;
    @ColumnInfo(name = "description")
    public String mHotelDesc;
    @ColumnInfo(name = "rooms")
    public int mRooms;
    @ColumnInfo(name = "price")
    public double mPrice;
    @ColumnInfo(name = "rating")
    public float mRating;

}
