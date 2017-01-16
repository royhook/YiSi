package com.yisi.picture.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.yisi.picture.R;
import com.yisi.picture.adapter.base.BaseViewHolder;
import com.yisi.picture.adapter.inter.OnItemClickListener;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumDetailsViewHolder extends BaseViewHolder {
    ImageView imageView;

    public AlbumDetailsViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView, onItemClickListener);
        imageView = getView(R.id.adapter_album_iv);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
