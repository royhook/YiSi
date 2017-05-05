package com.yisi.picture.utils;

import java.util.Random;

/**
 * Created by chenql on 2017/4/29.
 * 生成随机数逻辑
 */

public class RandomUtils {

    public static int generateRandom(int max, int min) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
