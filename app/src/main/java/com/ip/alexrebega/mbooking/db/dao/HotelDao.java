package com.ip.alexrebega.mbooking.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ip.alexrebega.mbooking.model.Hotel;
import com.ip.alexrebega.mbooking.model.Reservation;

import java.util.List;

@Dao
public interface HotelDao {
    @Insert
    void insert(Hotel hotel);

    @Update
    void update(Hotel... hotels);

    @Delete
    void delete(Hotel... hotels);
    @Query("SELECT * FROM hotel")
    List<Reservation> getAllHotels();
}
