package com.yisi.picture.picturemodel.view.utils;

import android.content.Context;
import android.widget.ImageView;

import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by chenql on 2017/10/14.
 */

public class GliderImageFragmentLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RecommandPlantImage recommandPlantImage = (RecommandPlantImage) path;
        GlideUtils.displayImage(recommandPlantImage.getFwfhUrl(1200, 1200), imageView);
    }
}
