package com.yisi.picture.picturemodel.presenter;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yisi.picture.baselib.base.BasePresenterImpl;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.PermissionUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.ImageOperatePagerAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.database.table.PictureTable;
import com.yisi.picture.picturemodel.model.ImageOperaOperateModel;
import com.yisi.picture.picturemodel.model.inter.IImageOperateModel;
import com.yisi.picture.picturemodel.presenter.inter.IImageOperaPre;

import java.io.IOException;
import java.util.List;

import static com.yisi.picture.picturemodel.model.ImageOperaOperateModel.TYPE_ONLY_SHOW;


/**
 * Created by roy on 2017/2/11.
 */

public class ImageOperateOperaPreImpl extends BasePresenterImpl<ImageOperateActivity, IImageOperateModel> implements IImageOperaPre, ViewPager.OnPageChangeListener {
    private int type_id;
    private int open_type;
    private int mAllNum;//图片的总数
    private int mCurrentPosition;
    private List<Image> mYiSiImages;
    private ImageOperatePagerAdapter adapter;

    public ImageOperateOperaPreImpl(ImageOperateActivity baseView) {
        super(baseView);
        type_id = getChildIntent().getIntExtra(IntentKey.KEY_PLANT_TYPE, 0);
        open_type = getChildIntent().getIntExtra(IntentKey.KEY_OPEN_TYPE, TYPE_ONLY_SHOW);
    }

    @Override
    protected IImageOperateModel setModel() {
        return new ImageOperaOperateModel(this);
    }

    @Override
    public Intent getChildIntent() {
        return mView.getIntent();
    }

    @Override
    public void getData() {
        mModel.getData(type_id);
    }

    @Override
    public void onSuccess(List<Image> images, int position) {
        mCurrentPosition = position;
        mYiSiImages = images;
        if (adapter != null)
            adapter = null;
        adapter = new ImageOperatePagerAdapter(images);
        adapter.setOnPincherViewClickListener(new ImageOperatePagerAdapter.onPincherViewClickListener() {
            @Override
            public void onClick() {
                mView.finish();
            }
        });
        if (open_type == 2)
            mAllNum = mYiSiImages.size() - 1;
        else
            mAllNum = mYiSiImages.size();
        mView.updataTextView((position + 1) + "/" + mAllNum);
        mView.setViewPagerAdapter(adapter);
        mView.getViewPager().setCurrentItem(position);
        mView.getViewPager().clearOnPageChangeListeners();
        mView.getViewPager().addOnPageChangeListener(this);
    }

    @Override
    public void onEmpty() {
        mView.finish();
        Toast.makeText(mView, "已经到头啦", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void downloadImg() {
        PermissionUtils.requestWriteSDCard(new PermissionUtils.OnRequestCallback() {
            @Override
            public void onSuccess() {
                Image image = mYiSiImages.get(mCurrentPosition);
                if (image != null)
                    GlideUtils.displayImageAndDownLoad(image.getUrl());
                Toast.makeText(mView, mView.getResources().getString(R.string.download_success), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail() {
                Toast.makeText(mView, mView.getResources().getString(R.string.download_fail_perssion_refused), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setWallPaper() {
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(mView);
        Image yiSiImage = mYiSiImages.get(mCurrentPosition);
        if (yiSiImage != null)
            GlideUtils.displayImageAndDownLoad(yiSiImage.getUrl(), new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    try {
                        wallpaperManager.setBitmap(resource);
                        Toast.makeText(mView, mView.getString(R.string.set_wapper_success), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    @Override
    public void setSystemWallPaper() {

        PermissionUtils.requestWriteSDCard(new PermissionUtils.OnRequestCallback() {
            @Override
            public void onSuccess() {
                Image yiSiImage = mYiSiImages.get(mCurrentPosition);
                if (yiSiImage != null)
                    GlideUtils.displayImageAndDownLoad(yiSiImage.getUrl(), new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent.putExtra("mimeType", "image/*");
                            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(mView.getContentResolver(), resource, null, null));
                            intent.setData(uri);
                            mView.startActivityForResult(intent, 100);
                        }
                    });
            }

            @Override
            public void onFail() {
                Toast.makeText(mView, mView.getResources().getString(R.string.wallpaper_fail_perssion_refused), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void collectImg() {
        Image image = mYiSiImages.get(mCurrentPosition);
        PictureTable.getInstance().insertImage(image);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        if (open_type == 2) {
            if (position != mYiSiImages.size() - 1)
                mView.updataTextView(position + 1 + "/" + (mYiSiImages.size() - 1));
        } else
            mView.updataTextView(position + 1 + "/" + (mYiSiImages.size()));

        //仅仅在最后一张，并且套图模式的情况下 才请求下一套图
        if (position == mYiSiImages.size() - 1 && open_type == 2) {
            type_id = type_id - 1;
            Toast.makeText(mView, R.string.next_plant, Toast.LENGTH_SHORT).show();
            getData();
        }
        mView.refreshFeaturesState();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
