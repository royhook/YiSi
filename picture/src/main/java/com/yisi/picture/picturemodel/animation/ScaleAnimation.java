package com.yisi.picture.picturemodel.animation;


import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;

/**
 * Created by roy on 2017/2/13.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ScaleAnimation extends Transition {

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {

    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
    }
}
