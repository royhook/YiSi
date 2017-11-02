package yisi.adplugin.utils;

import android.content.Context;
import android.view.WindowManager;

import com.yisi.picture.baselib.utils.ActivityLifestyle;


/**
 * Created with Android Studio
 * </p>
 * Authour:xiaxf
 * </p>
 * Date:16/8/10.
 */

public class WindowUtils {
    public static String currentActivityName = "";

    public static WindowManager getWindowManager() {
        WindowManager mWindowManager = (WindowManager) KyxSDKGlobal.mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        /**
         * 防止HookAty失败，导致广告不能展示
         */
        if (ActivityLifestyle.getInstance() == null || ActivityLifestyle.getInstance().getActivity() == null) {
            return mWindowManager;
        }
        mWindowManager = (WindowManager) ActivityLifestyle.getInstance().getActivity().getSystemService(Context.WINDOW_SERVICE);
        currentActivityName = ActivityLifestyle.getInstance().getActivity().getLocalClassName();

        return mWindowManager;
    }

    public static int getWindowType() {
        int type = WindowManager.LayoutParams.TYPE_TOAST;
        if (ActivityLifestyle.getInstance() == null || ActivityLifestyle.getInstance().getActivity() == null) {
            return type;
        }
        type = WindowManager.LayoutParams.TYPE_APPLICATION;
        return type;
    }
}
