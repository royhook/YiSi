package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.yisi.picture.baselib.base.BasePresenterImpl;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.PermissionUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.ImageOperatePagerAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.database.YisiDatabase;
import com.yisi.picture.picturemodel.database.table.PictureTable;
import com.yisi.picture.picturemodel.model.ImageOperaOperateModel;
import com.yisi.picture.picturemodel.model.inter.IImageOperateModel;
import com.yisi.picture.picturemodel.presenter.inter.IImageOperaPre;

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

    }

    @Override
    public void setSystemWallPaper() {

    }

    @Override
    public void collectImg() {
        Image image = mYiSiImages.get(mCurrentPosition);
        int result = PictureTable.getInstance().insertImage(image);
        if (result == YisiDatabase.DB_SUCCESS) {
            Snackbar.make(mView.getViewPager(), R.string.collect_success, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(mView.getViewPager(), R.string.collect_failed_exist, Snackbar.LENGTH_SHORT).show();
        }
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
        mView.refreshFeaturesState();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
