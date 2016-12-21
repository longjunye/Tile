package com.zhaozhuan.mobile.tile.http;

/**
 * Created by ricky.ye on 12/20/16.
 */

public interface SubscriberOnNextListener<T> {
    void doNext(T t);
}
