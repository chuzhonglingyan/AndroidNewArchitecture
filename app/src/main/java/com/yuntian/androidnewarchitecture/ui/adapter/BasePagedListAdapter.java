package com.yuntian.androidnewarchitecture.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @author chulingyan
 * @time 2018/12/16 11:31
 * @describe
 */
public abstract class BasePagedListAdapter<T> extends PagedListAdapter<T,BaseViewHolder<T>>{


    protected BasePagedListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    protected BasePagedListAdapter(@NonNull AsyncDifferConfig<T> config) {
        super(config);
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(parent,viewType);
    }


    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);
    }

    protected abstract int getLayoutId(int position);


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        if (getItem(position)!=null){
            holder.bindData(getItem(position) ,position);
        }else {
            holder.clear();
        }
    }



}
