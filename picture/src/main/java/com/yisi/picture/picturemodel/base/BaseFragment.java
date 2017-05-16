package com.yisi.picture.picturemodel.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.base.inter.IBaseAty;

/**
 * Created by roy on 2017/1/14.
 */

public abstract class BaseFragment extends Fragment implements IBaseAty {
    private View cacheView;
    private MultipleStatusView mMultipleStatusView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initMultyView() {
        mMultipleStatusView = findview(R.id.base_multiplestatusview);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (cacheView == null) {
            cacheView = inflater.inflate(getContentResouce(), container, false);
        }
        ViewGroup parent = (ViewGroup) cacheView.getParent();
        if (parent != null) {
            parent.removeView(cacheView);
        }
        initViews();
        initMultyView();
        initData();
        onLoadingPage();
        return cacheView;
    }

    protected abstract void initViews();

    protected abstract int getContentResouce();

    protected abstract void initData();

    protected <T> T findview(int id) {
        return (T) cacheView.findViewById(id);
    }

    @Override
    public void onLoadingSuccess() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showContent();
    }

    @Override
    public void onLoadingFail() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showError();
    }

    @Override
    public void onLoadingPage() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showLoading();
    }

    @Override
    public void onEmpty() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showEmpty();
    }
}
