package com.yuntian.androidnewarchitecture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.yuntian.androidnewarchitecture.base.data.BaseResultLiveData;
import com.yuntian.androidnewarchitecture.repository.IUserData;
import com.yuntian.androidnewarchitecture.bean.Repo;
import com.yuntian.androidnewarchitecture.bean.User;
import com.yuntian.androidnewarchitecture.repository.UserRepository;
import java.util.List;

public class UserViewModel extends ViewModel implements IUserData{

    private LiveData<User> user;

    private UserRepository userRepo;

    public UserViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId) {
        if (this.user != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        user = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }

    @Override
    public BaseResultLiveData<List<Repo>> getRepoList(String userId) {
        return userRepo.getRepoList(userId);
    }


}
