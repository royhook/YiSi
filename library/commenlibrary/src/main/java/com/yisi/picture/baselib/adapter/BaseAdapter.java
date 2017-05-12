package com.yisi.picture.baselib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;

import java.util.List;

/**
 * Created by roy on 2017/1/20.
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseAdapter(List<T> dataList) {
        this.mDataList = dataList;
    }

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
