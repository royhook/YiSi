package com.yisi.picture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yisi.picture.R;
import com.yisi.picture.adapter.base.BaseAdapter;
import com.yisi.picture.adapter.viewholder.PlantViewHolder;
import com.yisi.picture.application.YiSiApplication;
import com.yisi.picture.bean.PlantBrowse;
import com.yisi.picture.utils.GlideUtils;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantAdapter extends BaseAdapter<PlantViewHolder, PlantBrowse> {


    public PlantAdapter(List<PlantBrowse> dataList) {
        super(dataList);
    }

    @Override
    public PlantViewHolder holder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_plant, viewGroup, false);
        return new PlantViewHolder(view, onItemClickListener);
    }

    @Override
    protected void bindHolder(PlantViewHolder holder, int position) {
        if (position % 2 != 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.getImgView().getLayoutParams();
            layoutParams.leftMargin = YiSiApplication.mGlobleContext.getResources().getDimensionPixelOffset(R.dimen.px1);
            holder.getImgView().setLayoutParams(layoutParams);

        }
        GlideUtils.displayImage(mDataList.get(position).getImg_url(), holder.getImgView(), R.mipmap.defult);
        holder.getTitleView().setText(mDataList.get(position).getTitle());
    }
}
