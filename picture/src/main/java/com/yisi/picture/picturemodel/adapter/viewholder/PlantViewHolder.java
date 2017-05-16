package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.baselib.adapter.BaseViewHolder;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.picturemodel.R;


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
