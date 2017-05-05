package com.yisi.picture.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.R;
import com.yisi.picture.base.BaseActivity;

/**
 * Created by chenql on 2017/5/5.
 */

public class SettingActivity extends BaseActivity {
    TextView mClearMemroyView;
    ImageView mBackView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_start_in, R.anim.activity_start_out);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_setting);
        mClearMemroyView = findView(R.id.tv_setting_clearmemroy);
        mBackView = findView(R.id.setting_activity_back);
        mClearMemroyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("清理");
                builder.setMessage("你确定要清理缓存吗");
                builder.setCancelable(true);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar snackbar = Snackbar.make(mClearMemroyView, "清理完成", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }
        });
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_finish_in, R.anim.activity_finish_out);
    }
}
