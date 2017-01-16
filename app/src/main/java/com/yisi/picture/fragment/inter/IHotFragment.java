package com.yisi.picture.fragment.inter;

import com.yisi.picture.base.inter.IBaseView;
import com.yisi.picture.bean.HotImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IHotFragment extends IBaseView {
    void bindRecylerViewData(List<HotImage> hotImages);
}
