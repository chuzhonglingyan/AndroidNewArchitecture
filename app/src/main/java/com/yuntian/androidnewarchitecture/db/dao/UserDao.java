package com.yuntian.androidnewarchitecture.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.yuntian.androidnewarchitecture.db.entity.User;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    //    + "age LIKE :last LIMIT 1"
    @Query("SELECT * FROM user WHERE user_name LIKE :userName LIMIT 1 "
    )
    User findByName(String userName);

    @Query("SELECT * FROM user WHERE id=:id")
    User findById(int id);


    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}

