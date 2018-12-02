package com.yuntian.androidnewarchitecture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.base.App;
import com.yuntian.androidnewarchitecture.db.AppDatabase;
import com.yuntian.androidnewarchitecture.db.entity.User;
import com.yuntian.baselibs.base.BaseActivity;
import com.yuntian.baselibs.di.component.AppComponent;
import com.yuntian.baselibs.util.GsonUtil;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DbTestActivity extends BaseActivity {



    private AppDatabase appDatabase;

    private TextView tvAdd;
    private TextView tvDelete;
    private TextView tvUpdate;
    private TextView tvQuery;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_db_test;
    }

    @Override
    public void inject(AppComponent appComponent) {

    }

    @Override
    protected void initView() {
        tvAdd=findViewById(R.id.tv_add);
        tvDelete=findViewById(R.id.tv_delete);
        tvUpdate=findViewById(R.id.tv_update);
        tvQuery=findViewById(R.id.tv_query);
        appDatabase= App.getAppDatabase();
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

//        [10,40)不包括300就是 rand.nextInt(40-10)+10
        Random random=new Random();
        tvAdd.setOnClickListener(v->{
            try {
                ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Void>() {
                    @Override
                    public Void doInBackground() throws Throwable {
                        User user=new User();
                        user.setUserName("用户"+ UUID.randomUUID().toString().substring(0,4));
                        user.setAge(random.nextInt(40-10)+10);
                        appDatabase.userDao().insertAll(user);
                        return null;
                    }
                    @Override
                    public void onSuccess(Void result) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tvDelete.setOnClickListener(v->{


        });

        tvUpdate.setOnClickListener(v->{


        });


        tvQuery.setOnClickListener(v->{
            try {
                ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Void>() {
                    @Override
                    public Void doInBackground() throws Throwable {
                        List<User> users= appDatabase.userDao().getAll();
                        LogUtils.d(GsonUtil.toJson(users));
                        return null;
                    }
                    @Override
                    public void onSuccess(Void result) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }


}
