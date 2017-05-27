package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.activity.AlbumActivity;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.activity.inter.IAlbumAty;
import com.yisi.picture.picturemodel.adapter.AlbumDetilsAdapter;
import com.yisi.picture.picturemodel.bean.Album;
import com.yisi.picture.picturemodel.bean.AlbumImage;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.model.AlbumAtyModelImpl;
import com.yisi.picture.picturemodel.model.inter.IAlbumAtyModel;
import com.yisi.picture.picturemodel.presenter.inter.IAlbumAtyPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumAtyPreImpl extends BaseRefreshPresenterImpl<IAlbumAty, IAlbumAtyModel, AlbumImage> implements IAlbumAtyPre<AlbumImage>, XRecyclerView.LoadingListener, OnItemClickListener {
    private Album mAlbum;
    private List<YiSiImage> mYiSiImgs = new ArrayList<>();

    private AlbumDetilsAdapter albumDetilsAdapter;


    public AlbumAtyPreImpl(AlbumActivity baseView) {
        super(baseView);
        decodeAlbum();
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        if (albumDetilsAdapter == null) {
            albumDetilsAdapter = new AlbumDetilsAdapter(currentList);
            mView.bindLayoutManager(new GridLayoutManager(mView.getViewContext(), 2, 1, false));
            mView.bindAdapter(albumDetilsAdapter);
            albumDetilsAdapter.setOnItemClickListener(null);
        }
    }


    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return albumDetilsAdapter;
    }


    @Override
    protected AlbumAtyModelImpl setModel() {
        return new AlbumAtyModelImpl(this);
    }

    private void decodeAlbum() {
        String json_album = mView.getAlbumIntent().getStringExtra(IntentKey.KEY_ALBUM);
        mAlbum = new Gson().fromJson(json_album, Album.class);
    }


    @Override
    public void request(boolean readCache) {
        mModel.request(mAlbum.getType(), currentPage, readCache);
    }

    @Override
    public void onEmpty() {
        currentPage--;
        mView.dataRunOut();
        mView.onEmpty();
    }

    @Override
    public void onSuccess(List<AlbumImage> t) {
        super.onSuccess(t);
        if (mYiSiImgs != null)
            mYiSiImgs.clear();
        for (AlbumImage albumImage : currentList) {
            YiSiImage yisi = new YiSiImage();
            yisi.setImg_url(albumImage.getImg_url());
            yisi.setPage(albumImage.getPage());
            yisi.setType_id(albumImage.getType_id());
            mYiSiImgs.add(yisi);
        }
        mView.onLoadingSuccess();
    }

    @Override
    public void onClick(View view, int position) {
        Gson gson = new Gson();
        String json = gson.toJson(mYiSiImgs);
        Intent intent = new Intent(mView.getViewContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA, json);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, position);
        mView.getViewContext().startActivity(intent);
    }

    @Override
    public void initViewDatas() {
        mView.setToolBarTitle(mAlbum.getTitle());
    }

    @Override
    public void onFail(int errorCode) {
        super.onFail(errorCode);
        mView.dataRunOut();
        mView.onLoadingFail();
    }
}
