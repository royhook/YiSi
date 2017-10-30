package com.yisi.picture.picturemodel.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.utils.DesUtil;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.baselib.utils.PreferenceKey;
import com.yisi.picture.baselib.utils.PreferencesUtils;
import com.yisi.picture.picturemodel.R;

/**
 * Created by chenql on 2017/10/25.
 */

public class CoinUtils {
    private static int MOST_COIN = 5000;

    private static int getUsrCoin() {
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
        Snackbar.make(view, view.getResources().getString(R.string.add_coin, coin, currentCoin), Snackbar.LENGTH_SHORT).show();
    }

    public static boolean canBuy(int price, View view) {
        int currentCoin = getUsrCoin();
        if (currentCoin >= MOST_COIN) {
            currentCoin = MOST_COIN;
        }
        if (currentCoin < price) {
            Snackbar.make(view, R.string.notenough_coin, Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            saveUsrCoin(currentCoin - price);
            return true;
        }
    }
}
