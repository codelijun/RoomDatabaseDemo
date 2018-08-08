package com.example.lijun.roomdbdemo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lijun on 18-3-2.
 */

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    public int userId;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "user_age")
    public int userAge;
}
