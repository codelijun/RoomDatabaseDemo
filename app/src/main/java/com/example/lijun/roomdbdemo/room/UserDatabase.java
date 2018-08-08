package com.example.lijun.roomdbdemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by lijun on 18-3-2.
 */

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static UserDatabase INSTANCE;
    public static final Object mLock = new Object();

    public abstract UserDao getUserDao();

    public static UserDatabase getInstance(Context context) {
        synchronized (mLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user.db").build();
            }
            return INSTANCE;
        }

    }
}
