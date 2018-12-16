package com.yuntian.androidnewarchitecture.ui.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author chulingyan
 * @time 2018/12/16 11:25
 * @describe
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder  {



    private SparseArray<View> viewSparseArray=new SparseArray<>();

    public static <T> BaseViewHolder<T> createViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return  ViewHolderManager.createViewHolder(viewType,itemView);
    }



    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract   void  bindData(T t,int position);

    abstract     void clear();

    public <V extends View> V  getView(@IdRes int id){
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            if (view != null) {
                viewSparseArray.put(id, view);
            }
        }
        return (V) view;
    }

}
