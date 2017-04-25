package com.yisi.picture.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.yisi.picture.R;

/**
 * Created by chenql on 2017/4/25.
 */

public class OpertePopList extends PopupWindow {
    private Context mContext;

    public OpertePopList(Context context) {
        this.mContext = context;
    }

    public void showPopList() {
        Activity activity = (Activity) mContext;
        ViewGroup viewDecor = (ViewGroup) activity.getWindow().getDecorView();
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_operate, viewDecor, false);
        setContentView(view);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.RC_Pop);
        showAtLocation(viewDecor, Gravity.BOTTOM, 0, 0);

    }
}
