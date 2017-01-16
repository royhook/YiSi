package com.yisi.picture.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.activity.AlbumActivity;
import com.yisi.picture.activity.ImageOperateActivity;
import com.yisi.picture.activity.inter.IAlbumAty;
import com.yisi.picture.adapter.AlbumDetilsAdapter;
import com.yisi.picture.adapter.base.BaseAdapter;
import com.yisi.picture.adapter.inter.OnItemClickListener;
import com.yisi.picture.base.BaseRefreshPresenterImpl;
import com.yisi.picture.bean.Album;
import com.yisi.picture.bean.AlbumImage;
import com.yisi.picture.bean.YiSiImage;
import com.yisi.picture.model.AlbumAtyModelImpl;
import com.yisi.picture.model.inter.IAlbumAtyModel;
import com.yisi.picture.presenter.inter.IAlbumAtyPre;
import com.yisi.picture.utils.GsonFactory;
import com.yisi.picture.utils.IntentKey;

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
            mView.getRecyclerView().setLayoutManager(new GridLayoutManager(mView.getViewContext(), 2, 1, false));
            mView.getRecyclerView().setAdapter(albumDetilsAdapter);
            mView.getRecyclerView().setLoadingListener(this);
            albumDetilsAdapter.setOnItemClickListener(this);
        }
    }


    @Override
    public BaseAdapter getRefreshAdapter() {
        return albumDetilsAdapter;
    }

    @Override
    protected XRecyclerView getRecyclerView() {
        return mView.getRecyclerView();
    }


    @Override
    protected AlbumAtyModelImpl setModel() {
        return new AlbumAtyModelImpl(this);
    }

    @Override
    public void onFail(int errorCode) {

    }


    private void decodeAlbum() {
        String json_album = mView.getAlbumIntent().getStringExtra(IntentKey.KEY_ALBUM);
        mAlbum = GsonFactory.createGson().fromJson(json_album, Album.class);
    }


    @Override
    public void request(boolean readCache) {
        mModel.request(mAlbum.getType(), currentPage, readCache);
    }

    @Override
    public void onClick(View view, int position) {
        for (AlbumImage albumImage : currentList) {
            YiSiImage yisi = new YiSiImage();
            yisi.setImg_url(albumImage.getImg_url());
            yisi.setPage(albumImage.getPage());
            yisi.setType_id(albumImage.getType_id());
            mYiSiImgs.add(yisi);
        }
        Gson gson = new Gson();
        String json = gson.toJson(mYiSiImgs);
        Intent intent = new Intent(mView.getViewContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA, json);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, position);
        mView.getViewContext().startActivity(intent);
    }
}
