package com.yisi.picture.baselib.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by roy on 2017/2/5.
 */

public class RxBus {

    private RxBus() {
    }

    public static RxBus getInstance() {
        return RxHolder.RX_BUS;
    }

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Event event) {
        _bus.onNext(event);
    }


    public <T> Observable<T> toObservable(Class<T> eventType) {
        return _bus.ofType(eventType);

    }


    private static class RxHolder {
        static final RxBus RX_BUS = new RxBus();
    }

}
