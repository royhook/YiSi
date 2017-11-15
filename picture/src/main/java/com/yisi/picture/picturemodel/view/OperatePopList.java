package com.yisi.picture.picturemodel.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.view.inter.IPopListClick;


/**
 * Created by chenql on 2017/4/25.
 */

public class OperatePopList extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private TextView mDownLoadImgView;
    private TextView mCollectView;
    private IPopListClick mPopListClick;

    public void setPopListClickListener(IPopListClick popListClick) {
        mPopListClick = popListClick;
    }

    public OperatePopList(Context context) {
        this.mContext = context;
    }

    public void showPopList() {
        Activity activity = (Activity) mContext;
        ViewGroup viewDecor = (ViewGroup) activity.getWindow().getDecorView();
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_operate, viewDecor, false);
        mDownLoadImgView = ViewUtils.findView(view, R.id.tv_operate_save);
        mCollectView = ViewUtils.findView(view, R.id.tv_operate_collect);
        mDownLoadImgView.setOnClickListener(this);
        mCollectView.setOnClickListener(this);
        setContentView(view);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.RC_Pop);
        showAtLocation(viewDecor, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_operate_save) {
            if (mPopListClick != null)
                mPopListClick.onDownLoadClick();
            this.dismiss();
        } else if (v.getId() == R.id.tv_operate_collect) {
            if (mPopListClick != null)
                mPopListClick.onCollectionClick();
            this.dismiss();
        } else if (v.getId() == R.id.tv_operate_cancel) {
            this.dismiss();

        }
    }
}
