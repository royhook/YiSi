package com.yisi.picture.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.yisi.picture.R;
import com.yisi.picture.activity.AlbumActivity;
import com.yisi.picture.adapter.AlbumAdapter;
import com.yisi.picture.adapter.inter.OnItemClickListener;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.bean.Album;
import com.yisi.picture.fragment.inter.IAlbumFragment;
import com.yisi.picture.presenter.AlbumFragmentPreImpl;
import com.yisi.picture.presenter.inter.IAlbumFragmentPre;
import com.yisi.picture.utils.GsonFactory;
import com.yisi.picture.utils.IntentKey;

import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */

public class AlbumFragment extends BaseFragment implements IAlbumFragment, OnItemClickListener {
    RecyclerView mAlbumRecyclerView;
    ProgressBar mProgressBar;
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
        mProgressBar = findview(R.id.fragment_clp);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_album;
    }


    @Override
    protected void initData() {
        albumFragmentPre.request(1);
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
        String albumJson = GsonFactory.createGson().toJson(mAlbumList.get(position));
        Intent intent = new Intent(getContext(), AlbumActivity.class);
        intent.putExtra(IntentKey.KEY_ALBUM, albumJson);
        startActivity(intent);
    }

    @Override
    public void onLoadingPage() {
        mAlbumRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingSuccess() {
        mProgressBar.setVisibility(View.GONE);
        mAlbumRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingFail() {

    }
}
