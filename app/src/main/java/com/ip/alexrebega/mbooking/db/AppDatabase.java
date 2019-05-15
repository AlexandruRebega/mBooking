package com.ip.alexrebega.mbooking.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ip.alexrebega.mbooking.db.dao.UserDao;
import com.ip.alexrebega.mbooking.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}