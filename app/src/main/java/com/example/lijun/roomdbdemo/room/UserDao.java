package com.example.lijun.roomdbdemo.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by lijun on 18-3-2.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE user_age= :age")
    User findByAge(int age);

    @Query("SELECT * FROM user WHERE user_name LIKE :name")
    User findByName(String name);

    @Insert
    void insertAll(User... users);

    @Delete
    void deleteUser(User user);

}
