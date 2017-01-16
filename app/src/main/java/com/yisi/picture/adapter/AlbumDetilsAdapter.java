package com.yisi.picture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yisi.picture.R;
import com.yisi.picture.adapter.base.BaseAdapter;
import com.yisi.picture.adapter.viewholder.AlbumDetailsViewHolder;
import com.yisi.picture.bean.AlbumImage;
import com.yisi.picture.utils.GlideUtils;

import java.util.List;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumDetilsAdapter extends BaseAdapter<AlbumDetailsViewHolder, AlbumImage> {
    public AlbumDetilsAdapter(List<AlbumImage> dataList) {
        super(dataList);
    }

    @Override
    public AlbumDetailsViewHolder holder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_album_detiles, viewGroup, false);
        return new AlbumDetailsViewHolder(view, onItemClickListener);
    }

    @Override
    protected void bindHolder(AlbumDetailsViewHolder holder, int position) {
        GlideUtils.displayImage(mDataList.get(position).getImg_url(), holder.getImageView());
    }
}
