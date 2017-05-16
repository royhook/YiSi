package com.yisi.picture.picturemodel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.MainPageChildViewHolder;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildImageAdapter extends RecyclerView.Adapter<MainPageChildViewHolder> {
    private List<YiSiImage> mYiSiImages;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MainPageChildImageAdapter(List<YiSiImage> yiSiImages) {
        this.mYiSiImages = yiSiImages;
    }

    @Override
    public MainPageChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hot_image, parent, false);
        return new MainPageChildViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MainPageChildViewHolder holder, int position) {
        if (position % 2 != 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.getImageView().getLayoutParams();
            layoutParams.leftMargin = holder.getImageView().getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            holder.getImageView().setLayoutParams(layoutParams);

        }
        GlideUtils.displayImage(mYiSiImages.get(position).getImg_scale(), holder.getImageView(), R.mipmap.defult);
    }

    @Override
    public int getItemCount() {
        return mYiSiImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}