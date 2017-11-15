package com.yisi.picture.picturemodel.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantAdapter extends BaseQuickAdapter<RecommandPlantImage, BaseViewHolder> {


    public PlantAdapter(List<RecommandPlantImage> dataList) {
        super(R.layout.adapter_plant, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommandPlantImage item, int position) {
        ImageView imageView = helper.getView(R.id.adapter_plant_img);
        TextView textView = helper.getView(R.id.adapter_plant_title);
        if (position % 2 != 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.leftMargin = imageView.getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            imageView.setLayoutParams(layoutParams);
        }
        GlideUtils.displayImage(mData.get(position).getWrapWidth(ViewUtils.getDimen(R.dimen.px800)), imageView, R.mipmap.defult);
        textView.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
