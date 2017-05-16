package com.yisi.picture.picturemodel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yisi.picture.baselib.adapter.BaseAdapter;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.PlantViewHolder;
import com.yisi.picture.picturemodel.bean.PlantBrowse;

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
            layoutParams.leftMargin = holder.getImgView().getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            holder.getImgView().setLayoutParams(layoutParams);
        }
        GlideUtils.displayImage(mDataList.get(position).getImg_url(), holder.getImgView(), R.mipmap.defult);
        holder.getTitleView().setText(mDataList.get(position).getTitle());
    }
}
