package com.yuntian.androidnewarchitecture.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.db.entity.User;

import androidx.annotation.NonNull;

/**
 * @author chulingyan
 * @time 2018/12/16 12:10
 * @describe
 */
public class UserViewHolder extends BaseViewHolder<User> {



    UserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    void bindData(User user, int position) {
        TextView tv_id = getView(R.id.tv_id);
        TextView textView = getView(R.id.tv_name);
        tv_id.setText(String.valueOf(user.getId()));
        textView.setText(user.getUserName());
    }

    void clear() {

    }
}

