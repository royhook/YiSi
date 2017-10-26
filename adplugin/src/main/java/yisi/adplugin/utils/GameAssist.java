package yisi.adplugin.utils;

import android.app.Activity;


/**
 * <strong>Kamcord控制类（单例）</strong> <br/>
 * <font color="green">供SDKWrapper中的KamcordAssist类进行调用</font>
 *
 * @author jinyu
 */
public class GameAssist {
    private static GameAssist mInstance;
    /**
     * 游戏当前Activity上下文对象
     */
    private Activity mActivity;

    /**
     * 构造方法私有化
     */
    private GameAssist(Activity activity) {

        this.mActivity = activity;
    }

    public GameAssist() {

    }

    public void free() {
        mActivity = null;
    }


    public void setGameCurrentActivity(Activity gameCurrentActivity) {
        mActivity = gameCurrentActivity;
    }


    public static GameAssist getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        mInstance = new GameAssist();
        return mInstance;
    }


    public Activity getActivity() {
        return mActivity;
    }


}
