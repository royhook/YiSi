package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.baselib.adapter.BaseViewHolder;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.picturemodel.R;


/**
 * Created by roy on 2017/1/24.
 */

public class AlbumViewHolder extends BaseViewHolder {
    ImageView mImageView;//图片
    TextView mTextView;//标题
    TextView mTodayUpdate;//今日更新
    View mRootView;

    public AlbumViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView, onItemClickListener);
        if (itemView != null)
            mRootView = itemView;
        mImageView = getView(R.id.adapter_album_img);
        mTextView = getView(R.id.adapter_album_title);
        mTodayUpdate = getView(R.id.adapter_album_title_update);

    }

    public TextView getTodayUpdate() {
        return mTodayUpdate;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getmTextView() {
        return mTextView;
    }

    public View getRootView() {
        return mRootView;
    }
}
