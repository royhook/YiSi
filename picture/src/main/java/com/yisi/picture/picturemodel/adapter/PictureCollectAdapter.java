package com.yisi.picture.picturemodel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yisi.picture.baselib.adapter.BaseAdapter;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.PictureCollectViewHolder;
import com.yisi.picture.picturemodel.bean.Image;

import java.util.List;

/**
 * Created by chenql on 2017/5/4.
 */

public class PictureCollectAdapter extends BaseAdapter<PictureCollectViewHolder, Image> {
    public PictureCollectAdapter(List<Image> dataList) {
        super(dataList);
    }

    @Override
    public PictureCollectViewHolder holder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_picture_collect, viewGroup, false);
        return new PictureCollectViewHolder(view, onItemClickListener);
    }

    @Override
    protected void bindHolder(PictureCollectViewHolder holder, int position) {
        if (position % 2 != 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.getImageView().getLayoutParams();
            layoutParams.leftMargin = holder.getImageView().getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            holder.getImageView().setLayoutParams(layoutParams);
        }
        GlideUtils.displayImage(mDataList.get(position).getUrl(), holder.getImageView());
    }
}
