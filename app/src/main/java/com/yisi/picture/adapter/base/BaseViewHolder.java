package com.yisi.picture.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by roy on 2017/1/19.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    SparseArray<View> cacheViews;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected <T> T getView(int viewId) {
        View view = cacheViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            cacheViews.put(viewId, view);
        }
        return (T) view;
    }
}
