package com.yuntian.androidnewarchitecture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.R;

public class MainActivity extends AppCompatActivity {

    private UserProfileFragmentA userProfileFragmentA;
    private UserProfileFragmentB userProfileFragmentB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFrament();

        findViewById(R.id.tv_delete).setOnClickListener(v->{

            LogUtils.d("替换userProfileFragmentB");
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_b,UserProfileFragmentA.newIntance("octocat"));
            fragmentTransaction.commit();
        });
    }


    public void  initFrament(){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        userProfileFragmentA = (UserProfileFragmentA) getSupportFragmentManager().findFragmentByTag(UserProfileFragmentA.TAG);
        if (userProfileFragmentA ==null){
            userProfileFragmentA = UserProfileFragmentA.newIntance("octocat");
        }
        userProfileFragmentB = (UserProfileFragmentB) getSupportFragmentManager().findFragmentByTag(UserProfileFragmentA.TAG);
        if (userProfileFragmentB ==null){
            userProfileFragmentB = UserProfileFragmentB.newIntance("octocat");
        }

        fragmentTransaction.replace(R.id.fl_a, userProfileFragmentA);
        fragmentTransaction.replace(R.id.fl_b, userProfileFragmentB);

        fragmentTransaction.commit();
    }
}
