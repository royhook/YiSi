package com.yisi.picture.picturemodel.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yisi.picture.baselib.adapter.BaseAdapter;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.RandomUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.AlbumViewHolder;
import com.yisi.picture.picturemodel.bean.Album;

import java.util.List;


/**
 * Created by roy on 2017/1/24.
 */

public class AlbumAdapter extends BaseAdapter<AlbumViewHolder, Album> {
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AlbumAdapter(List<Album> albumList) {
        super(albumList);
    }

    @Override
    public AlbumViewHolder holder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_album, viewGroup, false);
        return new AlbumViewHolder(view, onItemClickListener);
    }

    @Override
    protected void bindHolder(AlbumViewHolder holder, int position) {
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.getRootView().getLayoutParams();
        if (position > 1) {
            params.topMargin = ViewUtils.getDimen(R.dimen.px5);
        }
        if (position % 2 != 0) {
            params.leftMargin = ViewUtils.getDimen(R.dimen.px5);
        }
        holder.getRootView().setLayoutParams(params);
        GlideUtils.displayImage(mDataList.get(position).getImg_url(), holder.getmImageView());
        holder.getmTextView().setText(mDataList.get(position).getTitle());
        holder.getTodayUpdate().setText("今日更新数:" + RandomUtils.generateRandom(50, 5));
    }

}
