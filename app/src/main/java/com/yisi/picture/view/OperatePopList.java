package com.yisi.picture.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yisi.picture.R;
import com.yisi.picture.utils.ViewUtils;
import com.yisi.picture.view.inter.IPopListClick;

/**
 * Created by chenql on 2017/4/25.
 */

public class OperatePopList extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private TextView mDownLoadImgView;
    private TextView mCollectView;
    private TextView mSetWallPagerView;
    private TextView mCancelView;
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
        mSetWallPagerView = ViewUtils.findView(view, R.id.tv_operate_set);
        mCancelView = ViewUtils.findView(view, R.id.tv_operate_cancel);
        mCancelView.setOnClickListener(this);
        mDownLoadImgView.setOnClickListener(this);
        mCollectView.setOnClickListener(this);
        mSetWallPagerView.setOnClickListener(this);
        setContentView(view);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.RC_Pop);
        showAtLocation(viewDecor, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_operate_save:
                if (mPopListClick != null)
                    mPopListClick.onDownLoadClick();
                this.dismiss();
                break;

            case R.id.tv_operate_collect:
                if (mPopListClick != null)
                    mPopListClick.onCollectionClick();
                this.dismiss();
                break;

            case R.id.tv_operate_set:
                if (mPopListClick != null)
                    mPopListClick.onSettingWallPageClick();
                this.dismiss();
                break;
            case R.id.tv_operate_cancel:
                this.dismiss();
                break;
        }
    }
}
