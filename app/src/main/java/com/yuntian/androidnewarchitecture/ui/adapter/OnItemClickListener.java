package com.yuntian.androidnewarchitecture.ui.adapter;

import android.view.View;

/**
 * @author chulingyan
 * @time 2018/12/16 17:01
 * @describe
 */
public interface OnItemClickListener<T> {

    void  onItemClick(View view,T t, int position);
}
