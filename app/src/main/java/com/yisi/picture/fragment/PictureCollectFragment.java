package com.yisi.picture.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.R;
import com.yisi.picture.activity.ImageOperateActivity;
import com.yisi.picture.adapter.PictureCollectAdapter;
import com.yisi.picture.adapter.inter.OnItemClickListener;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.bean.YiSiImage;
import com.yisi.picture.utils.IntentKey;
import com.yisi.picture.utils.PreferenceKey;
import com.yisi.picture.utils.PreferencesUtils;

import java.util.List;

/**
 * Created by chenql on 2017/5/4.
 * 图片收藏Fragment
 */

public class PictureCollectFragment extends BaseFragment {
    XRecyclerView mXRecyclerView;
    PictureCollectAdapter mAdapter;

    @Override
    protected void initViews() {
        mXRecyclerView = findview(R.id.xr_picture_collect);
        mXRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mXRecyclerView.setNoMore(true);
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setPullRefreshEnabled(false);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_picture_collect;
    }

    @Override
    protected void initData() {
        final String json = PreferencesUtils.getString(getContext(), PreferenceKey.MY_COLLECT_IMAGE);
        List<YiSiImage> list = new Gson().fromJson(json, new TypeToken<List<YiSiImage>>() {
        }.getType());
        if (list == null || list.size() == 0)
            return;
        mAdapter = new PictureCollectAdapter(list);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), ImageOperateActivity.class);
                intent.putExtra(IntentKey.KEY_IMAGE_OPERA, json);
                intent.putExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, position);
                startActivity(intent);
            }
        });
        mXRecyclerView.setAdapter(mAdapter);
    }
}