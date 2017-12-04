package com.yisi.picture.picturemodel.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildImageAdapter extends BaseQuickAdapter<YiSiImage, BaseViewHolder> {

    public MainPageChildImageAdapter(List<YiSiImage> dataList) {
        super(R.layout.adapter_hot_image, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, YiSiImage item) {
        ImageView imageView = helper.getView(R.id.adapter_hot_iv);

        if (helper.getPosition() % 2 != 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.leftMargin = imageView.getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            imageView.setLayoutParams(layoutParams);

        }
        if (helper.getPosition() == mData.size() - 1) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params.height = ViewUtils.getDimen(R.dimen.px100);
            params.width = ViewUtils.getDimen(R.dimen.px100);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.mipmap.more);
        } else {
            GlideUtils.displayImage(item.getImg_scale(), imageView);
        }
    }
}
