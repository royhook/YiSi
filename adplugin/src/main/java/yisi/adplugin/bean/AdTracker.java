package yisi.adplugin.bean;

/**
 * Created by chenql on 2017/8/25.
 * 记录广告生命周期，发生的事情
 */

public class AdTracker {
    int errorTimes;//记录该厂商失败的次数

    public int getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(int errorTimes) {
        this.errorTimes = errorTimes;
    }
}
