package com.yisi.picture.baselib.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;


/**
 * Created by roy on 2017/1/19.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    SparseArray<View> cacheViews;

    public BaseViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(v, getLayoutPosition());
                }
            }
        });
    }

    protected <T> T getView(int viewId) {
        if (cacheViews == null)
            cacheViews = new SparseArray<>();
        View view = cacheViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            cacheViews.put(viewId, view);
        }
        return (T) view;
    }


}
