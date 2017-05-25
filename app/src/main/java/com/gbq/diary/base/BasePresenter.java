package com.gbq.diary.base;

/**
 * 类说明：Presenter基类
 * Author: Kuzan
 * Date: 2017/5/25 17:20.
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detach() {
        mView = null;
    }
}
