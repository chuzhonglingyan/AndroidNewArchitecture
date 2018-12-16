package com.yuntian.androidnewarchitecture.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author chulingyan
 * @time 2018/12/16 11:25
 * @describe
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder  {


    public static <T> BaseViewHolder<T> createViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return  ViewHolderManager.createViewHolder(viewType,itemView);
    }



    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract   void  bindData(T t,int position);

    abstract     void clear();



}
