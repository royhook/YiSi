package com.yisi.picture.picturemodel.net;

import com.kinvey.android.callback.KinveyListCallback;

import java.util.List;

/**
 * Created by chenql on 2017/9/28.
 */

public abstract class KinveyHelpCallback<T> implements KinveyListCallback<T> {

    @Override
    public void onSuccess(List<T> list) {
        if (list != null) {
            if (list.size() > 0) {
                onDataSuccess(list);
            } else {
                onEmpty();
            }
        } else {
            onFail(new Throwable("list is null "));
        }
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    public abstract void onDataSuccess(List<T> list);

    public abstract void onFail(Throwable throwable);

    public abstract void onEmpty();

}
