package com.yisi.picture.picturemodel.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.inter.IImageDetilsAty;
import com.yisi.picture.picturemodel.presenter.ImageDetilsPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IDetilsPre;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class ImageDetilsActivity extends BaseActivity implements IImageDetilsAty {
    IDetilsPre mIDetilsPre;
    int requestId;
    String name;
    ImageView mImageView;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestId = getIntent().getIntExtra(IntentKey.KEY_KIND_ID, 0);
        name = getIntent().getStringExtra(IntentKey.kEY_KIND_NAME);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLoadingSuccess() {
        super.onLoadingSuccess();
        if (mRecyclerView == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(name);
            setSupportActionBar(toolbar);
            mImageView = findView(R.id.iv_img_detils);
            mRecyclerView = findView(R.id.rv_content_img_detils);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showShare();
                }
            });
        }
    }

    @Override
    protected void initPresenter() {
        mIDetilsPre = new ImageDetilsPreImpl(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_image_detils);
    }

    @Override
    protected void initData() {
        onLoadingPage();
        mIDetilsPre.request(false);
    }

    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setHeadImageUrl(String imageUrl) {
        GlideUtils.displayImage(imageUrl, mImageView);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public int getId() {
        return requestId;
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("Yisi");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sj.qq.com/myapp/detail.htm?apkName=com.yisi.picture");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("不错的应用，大家快下载");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://bmob-cdn-8831.b0.upaiyun.com/2017/05/05/3298c7a440cf9337806d20ef12b8d116.png");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sj.qq.com/myapp/detail.htm?apkName=com.yisi.picture");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("不错的应用，大家快下载");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Yisi");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sj.qq.com/myapp/detail.htm?apkName=com.yisi.picture");

// 启动分享GUI
        oks.show(this);
    }
}
