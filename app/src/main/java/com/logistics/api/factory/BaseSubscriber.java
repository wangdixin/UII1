package com.logistics.api.factory;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by paopao on 2018/2/5.
 */

public class BaseSubscriber<T> extends Subscriber<T> {

    protected Context context_;
    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        this.context_ = context;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError(context_, e);
    }


    @Override
    public void onNext(T t) {

    }



}
