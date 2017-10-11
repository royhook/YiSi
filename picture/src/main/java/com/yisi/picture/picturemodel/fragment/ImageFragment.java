package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.ImageAdapter;
import com.yisi.picture.picturemodel.bean.AliImage;
import com.yisi.picture.picturemodel.bean.AliImageUrl;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.ArrayList;
import java.util.List;

import static com.yisi.picture.picturemodel.model.ImageOperaOperateModel.TYPE_ONLY_SHOW;

/**
 * Created by chenql on 2017/6/1.
 */

public class ImageFragment extends BaseFragment {
    int mId;
    int currentPage = 1;
    List<AliImageUrl> mAliImageUrls;
    RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    public static final int REFRESH_NORMAL = 1;
    public static final int REFRESH_LOADMORE = 2;
    public static int REFRESH_TYPE = REFRESH_NORMAL;
    View retryView;


    public void setId(int id) {
        mId = id;
    }

    @Override
    protected void initViews() {
//        mRecyclerView = findview(R.id.rv_image);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_img;
    }

    @Override
    protected void initData() {
//        if (mAliImageUrls == null) {
//            request();
//        }
    }

    private void request() {

    }

    private void startOperaActivity(int position) {
        Log.d("tag", position + "");
        List<YiSiImage> yiSiImages = new ArrayList<>();
        for (AliImageUrl aliImageUrl : mAliImageUrls) {
            YiSiImage yiSiImage = new YiSiImage();
            yiSiImage.setImg_url(aliImageUrl.getBig());
            yiSiImages.add(yiSiImage);
        }
        Intent intent = new Intent(getContext(), ImageOperateActivity.class);
        String json = new Gson().toJson(yiSiImages);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA, json);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, position);
        intent.putExtra(IntentKey.KEY_OPEN_TYPE, TYPE_ONLY_SHOW);
        startActivity(intent);
    }

    private void decodeUrls(List<AliImage> aliImages) {
        int size = 0;
        if (mAliImageUrls == null)
            mAliImageUrls = new ArrayList<>();
        for (int i = 0; i < aliImages.size(); i++) {
            AliImage aliImage = aliImages.get(i);
            for (AliImageUrl imageUrl : aliImage.getList()) {
                mAliImageUrls.add(imageUrl);
                size++;
            }
        }
        if (REFRESH_TYPE == REFRESH_LOADMORE) {
            mAdapter.loadMoreComplete();
            mAdapter.notifyItemChanged(mAliImageUrls.size() - size + 1, mAliImageUrls.size());
            REFRESH_TYPE = REFRESH_NORMAL;
        }
    }


}
