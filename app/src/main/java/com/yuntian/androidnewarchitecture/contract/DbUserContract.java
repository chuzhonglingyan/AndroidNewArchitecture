package com.yuntian.androidnewarchitecture.contract;

import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;

import java.util.List;

import androidx.paging.PagedList;

public interface DbUserContract {

    BaseResultLiveData<List<User>> getUserList();

    BaseResultLiveData<PagedList<User>> getUserPageList(int pageSize);

    void insertAll(List<User> list);

    void insertAll(User... users);

}
