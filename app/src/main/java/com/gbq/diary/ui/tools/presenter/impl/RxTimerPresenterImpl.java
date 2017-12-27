package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.library.base.BasePresenter;
import com.gbq.library.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 类说明：Rx Timer时间操作符
 * Author: Kuzan
 * Date: 2017/12/26 17:13.
 */
public class RxTimerPresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    private DisposableSubscriber<Long> mTimerSubscriber;
    private DisposableSubscriber<Long> mIntervalSubscriber1;
    private DisposableSubscriber<Long> mIntervalSubscriber2;
    private Disposable mGetCodeDisposable;

    public RxTimerPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("timer 定时器");
        bean.setRemarks("定时器，timer 后执行");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("interval 定时器");
        bean.setRemarks("interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("interval 定时器(延时执行)");
        bean.setRemarks("interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("interval 定时器(实现获取验证码)");
        bean.setRemarks("实现获取短信验证码，button的状态改变");
        beans.add(bean);

        mView.replaceList(beans);
    }

    @Override
    public void onPositionClick(PositionBean bean) {
        if (bean != null) {
            switch (bean.getType()) {
                case POSITION_0:
                    onPosition0Click();
                    break;
                case POSITION_1:
                    onPosition1Click();
                    break;
                case POSITION_2:
                    onPosition2Click();
                    break;
                case POSITION_3:
                    onPosition3Click();
                    break;
            }
        }
    }

    /**
     * timer 定时器
     * */
    private void onPosition0Click() {
        if (mTimerSubscriber != null && !mTimerSubscriber.isDisposed()) {
            mTimerSubscriber.dispose();
            if (mView != null) {
                mView.addResultAtEnd("kill" + DateUtils.getCurrTime("HH:mm:ss"));
            }
            return;
        }

        if (mView != null) {
            mView.addResultAtEnd("start" + DateUtils.getCurrTime("HH:mm:ss"));
        }

        mTimerSubscriber = new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                if (mView != null) {
                    mView.addResultAtEnd("do" + DateUtils.getCurrTime("HH:mm:ss"));
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                if (mView != null) {
                    mView.addResultAtEnd("complete" + DateUtils.getCurrTime("HH:mm:ss"));
                }
            }
        };

        Flowable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mTimerSubscriber);
    }

    /**
     * interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
     * */
    private void onPosition1Click() {
        if (mIntervalSubscriber1 != null && !mIntervalSubscriber1.isDisposed()) {
            mIntervalSubscriber1.dispose();
            if (mView != null) {
                mView.addResultAtEnd("kill" + DateUtils.getCurrTime("HH:mm:ss"));
            }
            return;
        }

        mIntervalSubscriber1 = new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                if (mView != null) {
                    mView.addResultAtEnd("do" + DateUtils.getCurrTime("HH:mm:ss"));
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                if (mView != null) {
                    mView.addResultAtEnd("complete" + DateUtils.getCurrTime("HH:mm:ss"));
                }
            }
        };

        // 间隔1秒执行一次
        Flowable.interval(1, TimeUnit.SECONDS).take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mIntervalSubscriber1);
    }

    /**
     * interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
     * */
    private void onPosition2Click() {
        if (mIntervalSubscriber2 != null && !mIntervalSubscriber2.isDisposed()) {
            mIntervalSubscriber2.dispose();
            if (mView != null) {
                mView.addResultAtEnd("kill" + DateUtils.getCurrTime("HH:mm:ss"));
            }
            return;
        }
        if (mView != null) {
            mView.addResultAtEnd("start" + DateUtils.getCurrTime("HH:mm:ss"));
        }
        mIntervalSubscriber2 = new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                if (mView != null) {
                    mView.addResultAtEnd("do" + DateUtils.getCurrTime("HH:mm:ss"));
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                if (mView != null) {
                    mView.addResultAtEnd("complete" + DateUtils.getCurrTime("HH:mm:ss"));
                }
            }
        };

        // 延时5秒后执行，间隔1秒执行
        Flowable.interval(5, 1, TimeUnit.SECONDS).take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mIntervalSubscriber2);
    }

    /**
     * 实现获取验证码
     * */
    private void onPosition3Click() {
        if (mGetCodeDisposable != null && !mGetCodeDisposable.isDisposed()) {
            mGetCodeDisposable.dispose();
            return;
        }
        if (mView != null) {
            mView.addResultAtEnd("set button state false");
        }
        Observable.interval(1, TimeUnit.SECONDS).take(60)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd("set button state true, 获取验证码");
                        }
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mGetCodeDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (mView != null) {
                            mView.addResultAtEnd((60 - aLong) + "s后重新获取");
                        }
//                        if ((60 - aLong) < 50) {
//                            mGetCodeDisposable.dispose();
//                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.addResultAtEnd("set button state true, 重新获取");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.addResultAtEnd("set button state true, 重新获取");
                        }
                    }
                });
    }

    @Override
    public void onDispose() {
        if (mTimerSubscriber != null && !mTimerSubscriber.isDisposed()) {
            mTimerSubscriber.dispose();
        }
        if (mIntervalSubscriber1 != null && !mIntervalSubscriber1.isDisposed()) {
            mIntervalSubscriber1.dispose();
        }
        if (mIntervalSubscriber2 != null && !mIntervalSubscriber2.isDisposed()) {
            mIntervalSubscriber2.dispose();
        }
        if (mGetCodeDisposable != null && !mGetCodeDisposable.isDisposed()) {
            mGetCodeDisposable.dispose();
        }
    }
}
