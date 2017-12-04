package com.yisi.picture.picturemodel.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.PlantModel;
import com.yisi.picture.picturemodel.bean.PlantType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenql on 2017/5/29.
 */

public class MainPageChildAliAdapter extends BaseQuickAdapter<PlantModel, BaseViewHolder> {

    private OnTypeClickListener mClickListener;

    public void setOnRecommandClickListener(OnTypeClickListener listener) {
        this.mClickListener = listener;
    }

    public MainPageChildAliAdapter(@Nullable List<PlantModel> data) {
        super(R.layout.adapter_alipic, data);
    }

    private List<View> mViews = new ArrayList<>();

    @Override
    protected void convert(BaseViewHolder helper, final PlantModel plantModel) {
        TextView mainTitle = helper.getView(R.id.aa_title);
        mainTitle.setText(plantModel.getTitle());
        final List<PlantType> plantTypes = plantModel.getModel_list();
        View firstView = helper.getView(R.id.vd_first);
        View secondView = helper.getView(R.id.vd_second);
        View thridView = helper.getView(R.id.vd_third);
        View fouthView = helper.getView(R.id.vd_fourth);
        View moreView = helper.getView(R.id.aa_more);
        if (mViews.size() != 0) {
            mViews.clear();
        }
        mViews.add(firstView);
        mViews.add(secondView);
        mViews.add(thridView);
        mViews.add(fouthView);
        if (plantTypes.size() <= 4) {
            moreView.setVisibility(View.GONE);
        }
        for (int i = 0; i < plantTypes.size(); i++) {
            if (plantTypes.get(i) != null) {
                setTextView(mViews.get(i), plantTypes.get(i));
                final ImageView imageView = setImageView(mViews.get(i), plantTypes.get(i));
                final int finalI = i;
                mViews.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onClick(plantTypes.get(finalI).getId(),
                                plantTypes.get(finalI).getTitle(),
                                plantTypes.get(finalI).getFwfhUrl(500, 500),
                                imageView);
                    }
                });
            } else {
                mViews.get(i).setVisibility(View.GONE);
            }
        }
        //隐藏掉不需要的项
        for (int i = plantTypes.size(); i < mViews.size(); i++) {
            mViews.get(i).setVisibility(View.GONE);
        }
    }


    public interface OnTypeClickListener {
        void onClick(int id, String name, String url, View view);
    }

    private void displayImage(String url, ImageView imageView) {
        GlideUtils.displayImage(url, imageView);
    }

    public void setTextView(View parent, PlantType plantType) {
        TextView textView = ViewUtils.findView(parent, R.id.tv_detiles_title);
        if (plantType.getTitle() != null)
            textView.setText(plantType.getTitle());
    }

    public ImageView setImageView(View parent, PlantType plantType) {
        ImageView imageView = ViewUtils.findView(parent, R.id.iv_detiles_img);
        Log.d("cql", plantType.getFwfhUrl(500, 500));
        displayImage(plantType.getFwfhUrl(500, 500), imageView);
        return imageView;
    }
}
