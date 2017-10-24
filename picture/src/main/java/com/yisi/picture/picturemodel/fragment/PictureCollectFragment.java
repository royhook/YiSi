package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.PreferenceKey;
import com.yisi.picture.baselib.utils.PreferencesUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.PictureCollectAdapter;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;

/**
 * Created by chenql on 2017/5/4.
 * 图片收藏Fragment
 */

public class PictureCollectFragment extends BaseFragment {
    RecyclerView mXRecyclerView;
    PictureCollectAdapter mAdapter;

    @Override
    protected void initViews() {
        mXRecyclerView = findview(R.id.xr_picture_collect);
        mXRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
