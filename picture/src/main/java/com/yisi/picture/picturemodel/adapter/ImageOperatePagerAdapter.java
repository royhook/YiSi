package com.yisi.picture.picturemodel.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.view.PinchImageView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by roy on 2017/2/11.
 */

public class ImageOperatePagerAdapter extends PagerAdapter {

    private int currentPostion;

    private List<Image> images;
    private LinkedList<PinchImageView> pinchImageViews;
    private onPincherViewClickListener onPincherViewClickListener;

    public void setOnPincherViewClickListener(ImageOperatePagerAdapter.onPincherViewClickListener onPincherViewClickListener) {
        this.onPincherViewClickListener = onPincherViewClickListener;
    }

    public ImageOperatePagerAdapter(List<Image> images) {
        pinchImageViews = new LinkedList<>();
        this.images = images;
    }

    public int getCurrentPostion() {
        return currentPostion;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_operate_image, container, false);
        PinchImageView piv = (PinchImageView) view.findViewById(R.id.operate_iv_show);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.operate_pb);
        GlideUtils.displayImageWithThrun(images.get(position).getUrl(), piv, new GlideUtils.LoaderListener() {
            @Override
            public void loadSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void loadFail(String errorMessage) {
                progressBar.setVisibility(View.GONE);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPincherViewClickListener != null)
                    onPincherViewClickListener.onClick();
            }
        });
        piv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPincherViewClickListener != null)
                    onPincherViewClickListener.onClick();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface onPincherViewClickListener {
        void onClick();
    }
}
