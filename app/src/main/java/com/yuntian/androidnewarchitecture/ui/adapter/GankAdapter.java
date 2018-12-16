package com.yuntian.androidnewarchitecture.ui.adapter;

import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.bean.GankInfo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @author chulingyan
 * @time 2018/12/15 23:03
 * @describe
 */
public class GankAdapter extends BasePagedListAdapter<GankInfo> {

    public GankAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.item_gank;
    }

    private static final DiffUtil.ItemCallback<GankInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<GankInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull GankInfo oldObj, @NonNull GankInfo newObj) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldObj.get_id().equals(newObj.get_id());
        }

        @Override
        public boolean areContentsTheSame(@NonNull GankInfo oldObj, @NonNull GankInfo newObj) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldObj.equals(newObj);
        }
    };

}
