package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.yisi.picture.baselib.adapter.BaseViewHolder;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.picturemodel.R;


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
