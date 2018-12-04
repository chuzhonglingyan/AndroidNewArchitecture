package com.yuntian.androidnewarchitecture.repository.db;

import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;

import java.util.List;

public interface IDbUserData {

    BaseResultLiveData<List<User>> getUserList();

    void insertAll(List<User> list);

    void insertAll(User... users);

}
