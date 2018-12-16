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
public abstract class BasePagedListAdapter<T> extends PagedListAdapter<T, BaseViewHolder<T>> {

    private OnItemClickListener<T> onItemClickListener;

    protected BasePagedListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    protected BasePagedListAdapter(@NonNull AsyncDifferConfig<T> config) {
        super(config);
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder<T> baseViewHolder = BaseViewHolder.createViewHolder(parent, viewType);
        createItemClickListener(baseViewHolder);
        return baseViewHolder;
    }

    private void createItemClickListener(BaseViewHolder<T> baseViewHolder) {
        if (baseViewHolder != null && onItemClickListener != null) {
            baseViewHolder.itemView.setOnClickListener(v -> {
                int position = baseViewHolder.getAdapterPosition();
                if (position < 0) { //视图没及时刷新，获得位置信息可能为-1
                    return;
                }
                if (getItem(position) == null){
                    return;
                }
                onItemClickListener.onItemClick(baseViewHolder.itemView, getItem(position), position);
            });
        }
    }


    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);
    }

    protected abstract int getLayoutId(int position);


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        if (getItem(position) != null) {
            holder.bindData(getItem(position), position);
        } else {
            holder.clear();
        }
    }


}
