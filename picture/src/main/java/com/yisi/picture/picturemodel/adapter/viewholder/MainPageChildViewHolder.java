package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.yisi.picture.baselib.adapter.BaseViewHolder;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.picturemodel.R;


/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildViewHolder extends BaseViewHolder {

    private ImageView imageView;

    public MainPageChildViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView, onItemClickListener);
        imageView = getView(R.id.adapter_hot_iv);
    }


    public ImageView getImageView() {
        return imageView;
    }

}
