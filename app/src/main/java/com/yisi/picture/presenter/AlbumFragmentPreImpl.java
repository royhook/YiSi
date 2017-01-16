package com.yisi.picture.presenter;

import com.yisi.picture.base.BasePresenterImpl;
import com.yisi.picture.bean.Album;
import com.yisi.picture.fragment.AlbumFragment;
import com.yisi.picture.model.AlbumFragmentModelImpl;
import com.yisi.picture.presenter.inter.IAlbumFragmentPre;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by roy on 2017/1/23.
 */

public class AlbumFragmentPreImpl extends BasePresenterImpl<AlbumFragment, AlbumFragmentModelImpl> implements IAlbumFragmentPre {

    public AlbumFragmentPreImpl(AlbumFragment baseView) {
        super(baseView);
    }

    @Override
    protected AlbumFragmentModelImpl setModel() {
        return new AlbumFragmentModelImpl(this);
    }

    @Override
    public void request(int type) {
        mModel.request(type);
    }

    @Override
    public void onSuccess(List<Album> albumList) {
        if (albumList != null) {
            Collections.sort(albumList, new SorTlist());
            mView.bindRecycler(albumList);
        }
    }

    @Override
    public void onFail(int errorCode) {

    }

    private class SorTlist implements Comparator<Album> {

        @Override
        public int compare(Album o1, Album o2) {
            if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
