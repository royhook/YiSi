package com.yisi.picture.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by roy on 2017/1/20.
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    protected List<T> mDataList;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        return holder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        bindHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (mDataList.size() <= 0) {
            return 0;
        }
        return mDataList.size();
    }

    public abstract VH holder(ViewGroup viewGroup, int viewType);

    protected abstract void bindHolder(VH holder, int position);

}
