package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.picturemodel.R;

/**
 * Created by chenql on 2017/10/12.
 */

public class ImageChoseViewHolder extends BaseViewHolder {

    ImageView imageView;

    public ImageChoseViewHolder(View itemView) {
        super(itemView);
        imageView = getView(R.id.adapter_plant_img);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
