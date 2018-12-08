package com.yuntian.androidnewarchitecture.db.dao;



import com.yuntian.androidnewarchitecture.db.entity.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();



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


    @Insert
    void insertAll(List<User> userList);

    @Delete
    void delete(User user);
}

