package com.yisi.picture.picturemodel.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.PlantImage;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public class AliPlantAdapter extends BaseQuickAdapter<PlantImage, BaseViewHolder> {


        public AliPlantAdapter(List<PlantImage> dataList) {
            super(R.layout.adapter_plant, dataList);
        }

        @Override
        protected void convert(BaseViewHolder helper, PlantImage item, int position) {
            ImageView imageView = helper.getView(R.id.adapter_plant_img);
            TextView textView = helper.getView(R.id.adapter_plant_title);
            TextView coinView = helper.getView(R.id.adapter_plant_coin);
            if (position % 2 != 0) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.leftMargin = imageView.getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
                imageView.setLayoutParams(layoutParams);
            }
            GlideUtils.displayImage(mData.get(position).getWrapWidth(ViewUtils.getDimen(R.dimen.px800)), imageView, R.mipmap.defult);
            textView.setText(mData.get(position).getName());
            String coin = YiSiApplication.getStringResource(R.string.need_coin) + item.getCoin();
            coinView.setText(coin);
        }
}
