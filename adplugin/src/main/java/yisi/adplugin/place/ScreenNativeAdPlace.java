package yisi.adplugin.place;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;

import yisi.adplugin.R;
import yisi.adplugin.bean.NativeAdInfo;
import yisi.adplugin.business.BaseAdPlace;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created by chenql on 2017/12/15.
 * Native广告都可以走这里
 */

public abstract class ScreenNativeAdPlace extends BaseAdPlace {


    protected void showNativeAd(final NativeAdInfo nativeAdInfo, final IAdCallback callback) {
        final View view = LayoutInflater.from(KyxSDKGlobal.mContext).inflate(R.layout.screen_ad, null);
        ImageView imageView = ViewUtils.findView(view, R.id.img_screen);
        final TextView titleView = ViewUtils.findView(view, R.id.tv_title);
        final TextView descView = ViewUtils.findView(view, R.id.tv_desc);
        final ImageView iconView = ViewUtils.findView(view, R.id.img_icon);
        final TextView skipView = ViewUtils.findView(view, R.id.tv_skip);
        skipView.setClickable(false);
        GlideUtils.displayImage(nativeAdInfo.getIconUrl(), iconView);
        GlideUtils.displayImage(nativeAdInfo.getImageUrl(), imageView, new GlideUtils.LoaderListener() {
            @Override
            public void loadSuccess() {
                iconView.setVisibility(View.VISIBLE);
                titleView.setText(nativeAdInfo.getTitle());
                descView.setText(nativeAdInfo.getDesc());
                skipView.setVisibility(View.VISIBLE);
                if (callback != null) {
                    callback.onAdPresent(view);
                }
                CountDownTimer timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {
                        int second = (int) (l / 1000);
                        String text = second + "s";
                        skipView.setText(text);
                    }

                    @Override
                    public void onFinish() {
                        skipView.setText("Skip");
                        skipView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onAdSkip();
                            }
                        });
                    }
                };
                timer.start();
            }

            @Override
            public void loadFail(String errorMessage) {

            }
        });
    }


    public interface IAdCallback {
        void onAdPresent(View view);
    }
}
