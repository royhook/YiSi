package com.yisi.picture.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.R;
import com.yisi.picture.adapter.base.BaseViewHolder;
import com.yisi.picture.adapter.inter.OnItemClickListener;

/**
 * Created by roy on 2017/1/24.
 */

public class AlbumViewHolder extends BaseViewHolder {
    ImageView mImageView;//图片
    TextView mTextView;//标题

    public AlbumViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView, onItemClickListener);
        mImageView = getView(R.id.adapter_album_img);
        mTextView = getView(R.id.adapter_album_title);
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getmTextView() {
        return mTextView;
    }
}
