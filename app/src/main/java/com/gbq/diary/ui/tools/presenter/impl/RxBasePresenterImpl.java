package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.library.base.BasePresenter;
import com.gbq.library.utils.DateUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 类说明：Rx 基本使用
 * Author: Kuzan
 * Date: 2017/12/26 10:43.
 */
public class RxBasePresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    private Disposable mDisposable0; // 用于解除订阅，让Observer观察者不再接收上游事件

    public RxBasePresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("Observable");
        bean.setRemarks("不支持背压(背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。)" +
                "，若上游发射事件和下游接收事件在不同线程上处理，且上游发射数据速度大于下游接收处理数据的速度时, 建议使用Flowable，否则就没有必要使用Flowable，以免影响性能；" +
                "注：Observable(被观察者) 与 Observer(观察者)搭配");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("Flowable");
        bean.setRemarks("支持背压, Flowable(被观察者) 与 Subscriber/DisposableSubscriber(观察者)搭配");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("Consumer");
        bean.setRemarks("简化订阅方法(Consumer 即消费者，用于接收单个值，BiConsumer 则是接收两个值)");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("Single");
        bean.setRemarks("Single 只会接收一个参数，SingleObserver 只会调用 onError() 或者 onSuccess()");
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
     * Observable基本用法，不支持背压
     *      背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。
     * */
    private void onPosition0Click() {
        Observable.create(new ObservableOnSubscribe<Integer>() {    // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                // ObservableEmitter ，字面意思即发射器
                for (int i=0; i<10; i++) {
                    e.onNext(i);
                }
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {    // 添加转换器
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        }).subscribeOn(Schedulers.io()) // io线程
                .observeOn(AndroidSchedulers.mainThread())  // 主线程
                .subscribe(new Observer<String>() { // 第二步：初始化Observer

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable0 = d;
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        if (mView != null) {
                            mView.addResultAtEnd(s);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("onError=" + e);
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("onComplete");
                    }
                });
    }

    /**
     * Flowable基本用法，支持背压
     *      Flowable(被观察者) 与 Subscriber/DisposableSubscriber(观察者)搭配
     * */
    private void onPosition1Click() {
        Flowable.interval(1, TimeUnit.SECONDS).take(5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

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
                });
    }

    /**
     * 简化订阅方法
     *      使用Consumer
     * */
    private void onPosition2Click() {
        Observable.fromArray(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Integer>() {  // 添加过滤
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return String.valueOf(integer);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                if (mView != null) {
                    mView.addResultAtEnd(s);
                }
            }
        });
    }

    /**
     * Single 被观察者
     *      Single 只会接收一个参数
     * */
    private void onPosition3Click() {
        Single.just("Hello RxJava2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull String s) {
                        if (mView != null) {
                            mView.addResultAtEnd(s);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("onError");
                    }
                });
    }

    @Override
    public void onDispose() {
        if (mDisposable0 != null && !mDisposable0.isDisposed()) {
            mDisposable0.dispose();
        }
    }
}
