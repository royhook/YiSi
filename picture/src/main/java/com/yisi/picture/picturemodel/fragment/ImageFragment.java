package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.net.HttpCallback;
import com.yisi.picture.baselib.url.Api;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.ImageAdapter;
import com.yisi.picture.picturemodel.bean.AliImage;
import com.yisi.picture.picturemodel.bean.AliImageBody;
import com.yisi.picture.picturemodel.bean.AliImageUrl;
import com.yisi.picture.picturemodel.bean.AliPictureResult;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

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
        mRecyclerView = findview(R.id.rv_image);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_img;
    }

    @Override
    protected void initData() {
        if (mAliImageUrls == null) {
            request();
        }
    }

    private void request() {
        HttpParams params = new HttpParams();
        params.put("page", currentPage);
        params.put("type", mId);
        OkGo.get(Api.ALI_PICTURE_DETILES_URL)
                .params(params)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .cacheKey("image" + mId + currentPage)
                .execute(new HttpCallback<AliPictureResult<AliImageBody>>() {
                    @Override
                    public void onSuccess(AliPictureResult<AliImageBody> aliImageBodyAliPictureResult) {
                        onLoadingSuccess();
                        Log.d("tag", "s");
                        AliImageBody body = aliImageBodyAliPictureResult.getShowapi_res_body();
                        List<AliImage> aliImages = body.getPagebean().getContentlist();
                        if (aliImages != null && aliImages.size() == 0) {
                            if (REFRESH_TYPE == REFRESH_LOADMORE)
                                mAdapter.loadMoreEnd();
                            REFRESH_TYPE = REFRESH_NORMAL;
                            return;
                        }
                        decodeUrls(aliImages);
                        if (mAdapter == null) {
                            mAdapter = new ImageAdapter(mAliImageUrls);
                            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                @Override
                                public void onLoadMoreRequested() {
                                    REFRESH_TYPE = REFRESH_LOADMORE;
                                    currentPage++;
                                    request();
                                }
                            });
                            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    startOperaActivity(position);
                                }
                            });
                            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        if (REFRESH_TYPE == REFRESH_NORMAL) {
                            onLoadingFail();
                            retryView = findview(R.id.error_retry);
                            retryView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    REFRESH_TYPE = REFRESH_NORMAL;
                                    request();
                                }
                            });
                        }
                        currentPage--;
                        Log.d("tag", "error:" + e.getMessage());
                        REFRESH_TYPE = REFRESH_NORMAL;
                        if (mAdapter != null)
                            mAdapter.loadMoreFail();
                        super.onError(call, response, e);
                    }
                });
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
