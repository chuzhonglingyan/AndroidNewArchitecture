package com.yuntian.androidnewarchitecture.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.yuntian.androidnewarchitecture.R;
import com.yuntian.androidnewarchitecture.bean.GankInfo;
import com.yuntian.androidnewarchitecture.db.entity.User;

import androidx.annotation.NonNull;

/**
 * @author chulingyan
 * @time 2018/12/16 12:10
 * @describe
 */
public class GankViewHolder extends BaseViewHolder<GankInfo> {



    GankViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    void bindData(GankInfo gankInfo, int position) {
        TextView tv_pos = getView(R.id.tv_pos);
        TextView tv_id = getView(R.id.tv_id);
        TextView textView = getView(R.id.tv_name);
        tv_pos.setText(String.valueOf(position+1));
        tv_id.setText(String.valueOf(gankInfo.get_id()));
        textView.setText(gankInfo.getDesc());
    }

    void clear() {

    }
}

