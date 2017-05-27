package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.AlbumActivity;
import com.yisi.picture.picturemodel.adapter.AlbumAdapter;
import com.yisi.picture.picturemodel.bean.Album;
import com.yisi.picture.picturemodel.fragment.inter.IAlbumFragment;
import com.yisi.picture.picturemodel.presenter.AlbumFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IAlbumFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */

public class AlbumFragment extends BaseFragment implements IAlbumFragment, OnItemClickListener {
    RecyclerView mAlbumRecyclerView;
    private IAlbumFragmentPre albumFragmentPre;
    private List<Album> mAlbumList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumFragmentPre = new AlbumFragmentPreImpl(this);
    }

    @Override
    protected void initViews() {
        mAlbumRecyclerView = findview(R.id.album_rv);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_album;
    }


    @Override
    protected void initData() {
        albumFragmentPre.request();
    }

    @Override
    public void bindRecycler(List<Album> albumList) {
        mAlbumList = albumList;
        AlbumAdapter albumAdapter = new AlbumAdapter(albumList);
        mAlbumRecyclerView.setAdapter(albumAdapter);
        mAlbumRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        albumAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        String albumJson = new Gson().toJson(mAlbumList.get(position));
        Intent intent = new Intent(getContext(), AlbumActivity.class);
        intent.putExtra(IntentKey.KEY_ALBUM, albumJson);
        startActivity(intent);
    }
}
