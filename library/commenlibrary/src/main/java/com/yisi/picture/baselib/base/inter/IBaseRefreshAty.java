package com.yisi.picture.baselib.base.inter;

import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by chenql on 2017/5/24.
 */

public interface IBaseRefreshAty extends IBaseAty {

    void bindLayoutManager(LinearLayoutManager manager);

    void bindAdapter(BaseQuickAdapter adapter);

}
