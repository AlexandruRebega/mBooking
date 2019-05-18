package com.ip.alexrebega.mbooking.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ip.alexrebega.mbooking.model.Reservation;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    void insert(Reservation reservation);

    @Update
    void update(Reservation... reservations);

    @Delete
    void delete(Reservation... reservations);
    @Query("SELECT * FROM reservation")
    List<Reservation> getAllReservations();

    @Query("SELECT * FROM reservation WHERE user_id=:userId")
    List<Reservation> findRepositoriesForUser(final int userId);

}
