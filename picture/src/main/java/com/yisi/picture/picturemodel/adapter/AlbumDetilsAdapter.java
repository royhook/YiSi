package com.yisi.picture.picturemodel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.AlbumDetailsViewHolder;
import com.yisi.picture.picturemodel.bean.AlbumImage;

import java.util.List;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumDetilsAdapter extends BaseQuickAdapter<AlbumImage, AlbumDetailsViewHolder> {
    public AlbumDetilsAdapter(List<AlbumImage> dataList) {
        super(R.layout.adapter_album_detiles, dataList);
    }

    @Override
    protected void convert(AlbumDetailsViewHolder helper, AlbumImage item) {
        GlideUtils.displayImage(item.getImg_url(), helper.getImageView());
    }

}
