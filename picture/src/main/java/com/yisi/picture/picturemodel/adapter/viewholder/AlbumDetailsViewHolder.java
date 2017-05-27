package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.picturemodel.R;


/**
 * Created by roy on 2017/2/5.
 */

public class AlbumDetailsViewHolder extends BaseViewHolder {
    ImageView imageView;

    public AlbumDetailsViewHolder(View itemView) {
        super(itemView);
        imageView = getView(R.id.adapter_album_iv);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
