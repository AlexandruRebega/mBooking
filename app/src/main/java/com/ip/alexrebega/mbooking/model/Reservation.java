package com.ip.alexrebega.mbooking.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "uid",
        childColumns = "user_id",
        onDelete = CASCADE))
public class Reservation {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "hotel_id")
    public int hotelId;

//    @ColumnInfo(name =  //TODO: add typeconverter class
//    public String date;

    @ColumnInfo(name = "travelers")
    public int travelers;
}
