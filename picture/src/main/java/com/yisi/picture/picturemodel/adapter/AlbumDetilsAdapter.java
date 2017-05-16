package com.yisi.picture.picturemodel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yisi.picture.baselib.adapter.BaseAdapter;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.AlbumDetailsViewHolder;
import com.yisi.picture.picturemodel.bean.AlbumImage;

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
