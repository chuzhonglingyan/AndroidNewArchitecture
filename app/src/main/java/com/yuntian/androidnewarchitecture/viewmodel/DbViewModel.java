package com.yuntian.androidnewarchitecture.viewmodel;

import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.androidnewarchitecture.repository.db.DbUserRepository;
import com.yuntian.androidnewarchitecture.repository.db.IDbUserData;
import com.yuntian.baselibs.lifecycle.BaseResultLiveData;
import com.yuntian.baselibs.lifecycle.BaseViewModle;

import java.util.List;


public class DbViewModel extends BaseViewModle<DbUserRepository> implements IDbUserData {

    private BaseResultLiveData<List<User>>  showList;


    public void  initData(){
        showList=repo.getUserList();
    }

    public BaseResultLiveData<List<User>> getShowList() {
        return showList;
    }


    @Override
    public BaseResultLiveData<List<User>> getUserList() {
        return repo.getUserList();
    }

    @Override
    public void insertAll(List<User> list) {
        repo.insertAll(list);
    }

    @Override
    public void insertAll(User... users) {
        repo.insertAll(users);
    }


}
