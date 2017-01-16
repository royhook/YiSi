package com.yisi.picture.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yisi.picture.R;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.utils.GlideUtils;
import com.yisi.picture.utils.LogUtils;
import com.yisi.picture.view.PinchImageView;

/**
 * Created by roy on 2017/1/20.
 */

public class ImageOperateActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_operate);
        String imgUrl = getIntent().getStringExtra("imgUrl");
        PinchImageView imageView = findView(R.id.operate_iv_show);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageOperateActivity.this.finish();
            }
        });
        GlideUtils.displayImage(imgUrl, imageView, new GlideUtils.LoaderListener() {
            @Override
            public void loadSuccess(Bitmap bitmap) {
                LogUtils.d("success");
            }

            @Override
            public void loadFail(String errorMessage) {

            }
        });
    }

}
