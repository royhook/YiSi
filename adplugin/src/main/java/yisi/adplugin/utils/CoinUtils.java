package yisi.adplugin.utils;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.rx.Event;
import com.yisi.picture.baselib.rx.RxBus;
import com.yisi.picture.baselib.rx.RxKey;
import com.yisi.picture.baselib.utils.DesUtil;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.baselib.utils.PreferenceKey;
import com.yisi.picture.baselib.utils.PreferencesUtils;
import com.yisi.picture.baselib.utils.ReLockUtils;

import yisi.adplugin.R;
import yisi.adplugin.activity.CoinActivity;

/**
 * Created by chenql on 2017/10/25.
 */

public class CoinUtils {
    private static int MOST_COIN = 5000;

    public static int getUsrCoin() {
        String encode = null;
        try {
            encode = DesUtil.encode(String.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String defult = PreferencesUtils.getString(YiSiApplication.mGlobleContext, PreferenceKey.USER_COIN, encode);
        String decode = DesUtil.decodeValue(defult);
        LogUtils.d(decode);
        return Integer.valueOf(decode);
    }

    private static void saveUsrCoin(int coin) {
        try {
            String value = String.valueOf(coin);
            String encodeValue = DesUtil.encode(value);
            PreferencesUtils.putString(YiSiApplication.mGlobleContext, PreferenceKey.USER_COIN, encodeValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUsrCoin(int coin, View view) {
        int currentCoin = getUsrCoin();
        if (currentCoin >= MOST_COIN) {
            Snackbar.make(view, R.string.most_coin, Snackbar.LENGTH_SHORT).show();
            return;
        }
        currentCoin = currentCoin + coin;
        saveUsrCoin(currentCoin);
        Snackbar.make(view, view.getResources().getString(R.string.add_coin, coin, currentCoin), Snackbar.LENGTH_LONG).show();
        RxBus.getInstance().send(new Event<String>(RxKey.COIN_EXCHANGE, null));
    }

    public static boolean canBuy(String id, int price, View view) {
        int currentCoin = getUsrCoin();
        if (currentCoin >= MOST_COIN) {
            currentCoin = MOST_COIN;
        }
        if (ReLockUtils.idIsExist(id)) {
            return true;
        }
        if (currentCoin < price) {
            Snackbar snackbar = Snackbar.make(view, R.string.notenough_coin, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(YiSiApplication.mGlobleContext.getResources().getColor(R.color.imagepicker_pink));
            snackbar.setAction("前往金币赚取专区", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = CoinActivity.getCoinIntent();
                    YiSiApplication.mGlobleContext.startActivity(intent);
                }
            });
            snackbar.show();
            return false;
        } else {
            saveUsrCoin(currentCoin - price);
            return true;
        }
    }
}
