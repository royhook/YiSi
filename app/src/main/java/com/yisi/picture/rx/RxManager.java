package com.yisi.picture.rx;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by roy on 2017/2/5.
 */

public class RxManager {

    public HashMap<String, Observable> observables;//被观察者

    public void addObservable(String key, Observable observable) {
        observables.put(key, observable);
    }

    public Observable getObservable(String key) {
        return observables.get(key);
    }

    private RxManager() {
    }

    public RxManager getInstance() {
        return RxHolder.rxManager;
    }

    public static class RxHolder {
        static final RxManager rxManager = new RxManager();
    }
}
