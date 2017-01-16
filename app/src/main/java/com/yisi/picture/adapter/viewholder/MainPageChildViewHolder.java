package com.yisi.picture.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.yisi.picture.R;
import com.yisi.picture.adapter.base.BaseViewHolder;
import com.yisi.picture.adapter.inter.OnItemClickListener;

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
