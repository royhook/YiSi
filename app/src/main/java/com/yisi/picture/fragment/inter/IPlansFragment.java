package com.yisi.picture.fragment.inter;

import android.content.Context;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.base.inter.IBaseAty;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlansFragment extends IBaseAty {

    XRecyclerView getRecylerView();

    Context getViewContext();

    void dataOut();

}
