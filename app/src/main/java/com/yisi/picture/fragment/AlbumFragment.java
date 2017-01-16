package com.yisi.picture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yisi.picture.R;
import com.yisi.picture.adapter.AlbumAdapter;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.bean.Album;
import com.yisi.picture.fragment.inter.IAlbumFragment;
import com.yisi.picture.presenter.AlbumFragmentPreImpl;
import com.yisi.picture.presenter.inter.IAlbumFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */

public class AlbumFragment extends BaseFragment implements IAlbumFragment {
    RecyclerView mAlbumRecyclerView;
    private IAlbumFragmentPre albumFragmentPre;

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
        albumFragmentPre.request(1);
    }

    @Override
    public void bindRecycler(List<Album> albumList) {
        AlbumAdapter albumAdapter = new AlbumAdapter(albumList);
        mAlbumRecyclerView.setAdapter(albumAdapter);
        mAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
