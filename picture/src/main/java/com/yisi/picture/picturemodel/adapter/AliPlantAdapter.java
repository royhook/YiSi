package com.yisi.picture.picturemodel.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.AliImage;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public class AliPlantAdapter extends BaseQuickAdapter<AliImage, BaseViewHolder> {


    public AliPlantAdapter(List<AliImage> dataList) {
        super(R.layout.adapter_plant, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, AliImage item, int position) {
        ImageView imageView = helper.getView(R.id.adapter_plant_img);
        TextView textView = helper.getView(R.id.adapter_plant_title);
        if (position % 2 != 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.leftMargin = imageView.getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            imageView.setLayoutParams(layoutParams);
        }
        GlideUtils.displayImage(mData.get(position).getList().get(0).getBig(), imageView, R.mipmap.defult);
        textView.setText(mData.get(position).getTitle());
    }
}
