package com.yisi.picture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by roy on 2017/1/14.
 */

public abstract class BaseFragment extends Fragment {
    private View cacheView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        initData();
        return cacheView;
    }

    protected abstract void initViews();

    protected abstract int getContentResouce();

    protected abstract void initData();

    protected <T> T findview(int id) {
        return (T) cacheView.findViewById(id);
    }
}
