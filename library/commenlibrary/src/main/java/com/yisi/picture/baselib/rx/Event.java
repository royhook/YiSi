package com.yisi.picture.baselib.rx;

/**
 * Created by chenql on 2017/10/31.
 */

public class Event<T> {

    String tag;
    T values;

    public Event(String tag, T values) {
        this.tag = tag;
        this.values = values;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setValues(T values) {
        this.values = values;
    }

    public String getTag() {
        return tag;
    }

    public T getValues() {
        return values;
    }

    public Event() {

    }
}
