package com.yisi.picture.rx;

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

    public Observable<Object> toObservable() {
        return _bus;
    }

    private static class RxHolder {
        static final RxBus RX_BUS = new RxBus();
    }

    public static class Event<T> {
        String tag;
        T values;

        public Event(String tag, T values) {
            this.tag = tag;
            this.values = values;
        }
    }


}
