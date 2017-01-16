package com.yisi.picture.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.R;
import com.yisi.picture.adapter.base.BaseViewHolder;
import com.yisi.picture.adapter.inter.OnItemClickListener;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantViewHolder extends BaseViewHolder {
    ImageView imgView;
    TextView titleView;

    public ImageView getImgView() {
        return imgView;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public PlantViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView, onItemClickListener);
        imgView = getView(R.id.adapter_plant_img);
        titleView = getView(R.id.adapter_plant_title);
    }
}
