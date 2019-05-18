package com.ip.alexrebega.mbooking.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ip.alexrebega.mbooking.db.dao.HotelDao;
import com.ip.alexrebega.mbooking.db.dao.ReservationDao;
import com.ip.alexrebega.mbooking.db.dao.UserDao;
import com.ip.alexrebega.mbooking.model.Hotel;
import com.ip.alexrebega.mbooking.model.Reservation;
import com.ip.alexrebega.mbooking.model.User;

@Database(entities = {User.class, Reservation.class, Hotel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ReservationDao reservationDao();
    public abstract HotelDao hotelDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}