package com.yuntian.androidnewarchitecture.ui.adapter;

import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.db.entity.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @author chulingyan
 * @time 2018/12/15 23:03
 * @describe
 */
public class UserAdapter extends BasePagedListAdapter<User> {

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.item_user;
    }

    private static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldUser, @NonNull User newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getId() == newUser.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldUser, @NonNull User newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.equals(newUser);
        }
    };

}
