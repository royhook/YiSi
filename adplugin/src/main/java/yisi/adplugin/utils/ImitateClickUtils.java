package yisi.adplugin.utils;

import android.util.Log;

import com.yisi.picture.baselib.utils.ViewUtils;

import java.util.Random;

import yisi.adplugin.R;

/**
 * Create With AndroidStudio
 * Author chenql
 * Data  2016-8-17.
 */
public class ImitateClickUtils {

    /**
     * 创建点击坐标Y(按下)
     *
     * @param screenHeight
     * @return
     */
    private static float createRandomY(int screenHeight) {
        Random random = new Random();
        int heightMax = screenHeight / 2 + ViewUtils.getDimen(R.dimen.px100);
        int heightMin = screenHeight / 2 - ViewUtils.getDimen(R.dimen.px100);
        return (float) (random.nextInt(heightMax) % (heightMax - heightMin + 1) + heightMin);
    }

    /**
     * banner的Y坐标在底部
     *
     * @param screenHeight
     * @return
     */
    private static float createRandomBannerY(int screenHeight) {
        Random random = new Random();
        int heightMax = screenHeight;
        int heightMin = screenHeight - ViewUtils.getDimen(R.dimen.px108);
        return (float) (random.nextInt(heightMax) % (heightMax - heightMin + 1) + heightMin);
    }

    private static float createMockRandomY(int viewHeight) {
        Random random = new Random();
        int heightMin = ViewUtils.getDimen(R.dimen.px20);
        return (float) (random.nextInt(viewHeight) % (viewHeight - heightMin + 1) + heightMin);
    }

    /**
     * 创建点击坐标X(按下)
     *
     * @param ScreenWidth
     * @return
     */
    private static float createRandomX(int ScreenWidth) {
        int widthMax = ScreenWidth / 2 + ViewUtils.getDimen(R.dimen.px200);
        int widthMin = ScreenWidth / 2 - ViewUtils.getDimen(R.dimen.px200);
        Random random = new Random();
        return (float) (random.nextInt(widthMax) % (widthMax - widthMin + 1) + widthMin);
    }

    private static float createMockRandomX(int viewWidth) {
        int widthMin = ViewUtils.getDimen(R.dimen.px20);
        Random random = new Random();
        return (float) (random.nextInt(viewWidth) % (viewWidth - widthMin + 1) + widthMin);
    }

    private static float createRandomCleanY(int viewHeight) {
        Random random = new Random();
        int heightMin = viewHeight - ViewUtils.getDimen(R.dimen.px150);
        return (float) (random.nextInt(viewHeight) % (viewHeight - heightMin + 1) + heightMin);
    }

    /**
     * 创建延迟时间
     *
     * @return
     */
    public static int createTime() {
        Random random = new Random();
        int minTime = 60;
        int maxTime = 180;
        return random.nextInt(maxTime) % (maxTime - minTime + 1) + minTime;
    }

    /**
     * 创建弹起坐标增量
     * 测试结果 基本都没怎么变化。少数几率会有变化
     *
     * @return
     */
    private static float createRandomUpNum() {
        Random random = new Random();
        int min = 0;
        int max = 0;
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 2.5s - 5s之间产生一个随机数 并点击
     *
     * @return
     */
    public static int createDelayTimeToClick() {
        Random random = new Random();
        int minTime = 2500;
        int maxTime = 5000;
        return random.nextInt(maxTime) % (maxTime - minTime + 1) + minTime;
    }

    /**
     * 命中概率
     * 按人数命中
     */
    public static boolean hitProbability(int chance) {
        if (chance == 0) {
            return false;
        }
        Random random = new Random();
        int min = 1;
        int max = chance;
        int randomValue = random.nextInt(max) % (max - min + 1) + min;
        Log.d("studio", "chance:" + randomValue);
        if (randomValue == 1) {
            return true;
        }
        return false;
    }

    /**
     * 随机概率事件
     *
     * @param chance
     * @return
     */
    public static boolean hitRandomProbability(int chance) {
        Random rn = new Random();
        int answer = rn.nextInt(10) + 1;
        return answer > 10 - chance;
    }
}