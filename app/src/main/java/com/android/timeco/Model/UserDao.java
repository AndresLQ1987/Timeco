package com.android.timeco.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 *  User Class define the features and methods of Users
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getUsers();

    @Query("SELECT * FROM User WHERE id LIKE :uuid")
    User getUser(String uuid);

    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);
}
