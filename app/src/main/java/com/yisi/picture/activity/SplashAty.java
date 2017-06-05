package com.yisi.picture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.ISplashAty;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.utils.BitmapUtils;
import com.yisi.picture.baselib.utils.DirManager;
import com.yisi.picture.baselib.utils.EnvUtils;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.PermissionUtils;
import com.yisi.picture.baselib.utils.PreferencesUtils;
import com.yisi.picture.baselib.utils.ViewUtils;

import java.util.logging.Level;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashAty extends BaseActivity implements ISplashAty {
    public static final String BMOB_APPID = "9b7629a191656e19839a10be2a27363b";
    public static final String BMOB_YISI_APPID = "bd680cd370f7e8716b941d9d37872d2e";
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goMainPage();
        initBmob();
        initHttp();
        GlideUtils.initGlide(YiSiApplication.mGlobleContext);
        ViewUtils.init(YiSiApplication.mGlobleContext);
        DirManager.getInstance().init();
        EnvUtils.init(YiSiApplication.mGlobleContext);
        PermissionUtils.init(YiSiApplication.mGlobleContext);
        BitmapUtils.initBitmapUtils(YiSiApplication.mGlobleContext);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_splash);
        relativeLayout = findView(R.id.splash_ad_view);
    }

    @Override
    protected void initData() {

    }

    private void goMainPage() {
        Intent intent = new Intent(SplashAty.this, MainActivity.class);
        startActivity(intent);
        SplashAty.this.finish();
        ShareSDK.initSDK(this);
    }


    private void initBmob() {
        String bmobId = PreferencesUtils.getString(this, PreferencesUtils.KEY.KEY_BMOB_ID, BMOB_APPID);
        String preBmobId = PreferencesUtils.getString(this, PreferencesUtils.KEY.KEY_PRE_BMOB_ID, BMOB_APPID);
        //如果没有发生改变，就不改变
        if (bmobId.equals(preBmobId)) {
            YiSiApplication.isChange = false;
        }
        //发生了改变
        else {
            YiSiApplication.isChange = true;
        }
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId(bmobId)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*icon
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
        PreferencesUtils.putString(this, PreferencesUtils.KEY.KEY_PRE_BMOB_ID, bmobId);
    }

    private void initHttp() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        String appcode = "fb3547cb3fd642eeae9c51e5e5533502";
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "APPCODE " + appcode);
        HttpParams params = new HttpParams();

        //-----------------------------------------------------------------------------------//

        //必须调用初始化
        OkGo.init(this.getApplication());

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)

                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates()                                  //方法一：信任所有证书,不安全有风险
                    .addCommonHeaders(headers)  //设置全局公共头
                    .addCommonParams(params);   //设置全局公共参数

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
