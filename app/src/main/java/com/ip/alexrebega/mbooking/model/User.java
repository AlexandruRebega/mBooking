package com.ip.alexrebega.mbooking.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "mail")
    public String mail;

//    @ColumnInfo(name = "reservations")
//    public List<Integer> reservationsId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}
