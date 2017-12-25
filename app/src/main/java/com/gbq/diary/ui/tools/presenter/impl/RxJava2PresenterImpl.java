package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.CityBean;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.beans.ProvinceBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxJava2Presenter;
import com.gbq.diary.ui.tools.view.IRxJava2View;
import com.gbq.diary.utils.AddressUtil;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/12/25 11:27.
 */
public class RxJava2PresenterImpl extends BasePresenter<IRxJava2View> implements IRxJava2Presenter {

    private Disposable mDisposable0; // 用于解除订阅，让Observer观察者不再接收上游事件

    public RxJava2PresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("Observable");
        bean.setRemarks("不支持背压(背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。)");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("Consumer");
        bean.setRemarks("简化订阅方法(Consumer 即消费者，用于接收单个值，BiConsumer 则是接收两个值)");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("subScribeOn/observeOn");
        bean.setRemarks("线程调度");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("map 转换操作符");
        bean.setRemarks("map 操作符可以将一个 Observable 对象通过某种关系转换为另一个Observable 对象(一对一)");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_4);
        bean.setTitle("flatMap 转换操作符");
        bean.setRemarks("flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，然后将它们发射的数据合并后放到一个单一的 Observable，" +
                "然后发送(一对多)，注：flatMap 并不能保证事件的顺序");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_5);
        bean.setTitle("concatMap 转换操作符");
        bean.setRemarks("concatMap 与flatMap 转换操作符一样，它完善了flatMap的缺点，保证了事件的顺序");
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
                case POSITION_4:
                    onPosition4Click();
                    break;
                case POSITION_5:
                    onPosition5Click();
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
                .subscribe(new Observer<String>() {
                    // 第二步：初始化Observer

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
     * 简化订阅方法
     *      使用Consumer
     * */
    private void onPosition1Click() {
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
     * 线程调度
     *      subscribeOn() 指定的就是发射事件的线程，通俗说是数据处理线程
     *      observerOn 指定的就是订阅者接收事件的线程，通俗说是数据显示线程
     * */
    private void onPosition2Click() {
        List<String> list = new ArrayList<>();
        list.add("subscribeOn() 指定的就是发射事件的线程，通俗说是数据处理线程");
        list.add("observerOn 指定的就是订阅者接收事件的线程，通俗说是数据显示线程");
        list.add("多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。");
        list.add("但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。");
        list.add("下游数据展示到页面必须要切换回主线程(AndroidSchedulers.mainThread())");

        Observable.just(list)
                .subscribeOn(Schedulers.newThread())    // 发射事件线程切换到新的线程
                .observeOn(Schedulers.io())     // 接收事件线程切换到io线程
                .map(new Function<List<String>, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull List<String> list) throws Exception {
                        List<String> newList = new ArrayList<>();
                        for (int i=0; i<list.size(); i++) {
                            newList.add((i+1) + "." + list.get(i));
                        }
                        return newList;
                    }
                })
                .doOnNext(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> list) throws Exception {
                        // doOnNext一般用来数据库保存等操作，这里对list操作无意义,常见于图片加载时先显示占位图
                        Timber.e("After observeOn(io)，Current thread is " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())   // 发射事件线程切换到io线程，切换不成功，还是原来的线程
                .observeOn(AndroidSchedulers.mainThread())  // 接收事件线程切换到主线程，切换成功，注，数据展示到页面必须要切换回主线程
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> list) throws Exception {
                        Timber.e("After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                        if (mView != null) {
                            mView.replaceResult(list);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Timber.e("error" + throwable);
                    }
                });
    }

    /**
     * map转换操作符
     *      map 操作符可以将一个 Observable 对象通过某种关系转换为另一个Observable 对象
     * */
    private void onPosition3Click() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                for (int i=0; i<10; i++) {
                    e.onNext(i);
                }
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "This map Function:" + integer;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd(s);
                        }
                    }
                });
    }

    /**
     * flatMap转换操作符
     *      flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables，
     *      然后将它们发射的数据合并后放到一个单一的 Observable，然后发送
     *      注：有个需要注意的是，flatMap 并不能保证事件的顺序
     * */
    private void onPosition4Click() {
        // 只打印市数据
        Observable.fromIterable(AddressUtil.getAddress())
                .flatMap(new Function<ProvinceBean, ObservableSource<CityBean>>() {
                    @Override
                    public ObservableSource<CityBean> apply(@NonNull ProvinceBean bean) throws Exception {
                        int delayTime = (int) (1 + Math.random() * 10); // 添加随机数延时，验证顺序
                        return Observable.fromIterable(bean.getCityList()).delay(delayTime, TimeUnit.MILLISECONDS);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityBean>() {
                    @Override
                    public void accept(@NonNull CityBean cityBean) throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd(cityBean.getName());
                        }
                    }
                });
    }

    /**
     * concatMap转换操作符
     *      concatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables，
     *      然后将它们发射的数据合并后放到一个单一的 Observable，然后发送
     *      注：完善了flatMap 不能保证事件的顺序缺点
     * */
    private void onPosition5Click() {
        // 只打印市数据
        Observable.fromIterable(AddressUtil.getAddress())
                .concatMap(new Function<ProvinceBean, ObservableSource<CityBean>>() {
                    @Override
                    public ObservableSource<CityBean> apply(@NonNull ProvinceBean bean) throws Exception {
                        int delayTime = (int) (1 + Math.random() * 10); // 添加随机数延时，验证顺序
                        return Observable.fromIterable(bean.getCityList()).delay(delayTime, TimeUnit.MILLISECONDS);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityBean>() {
                    @Override
                    public void accept(@NonNull CityBean cityBean) throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd(cityBean.getName());
                        }
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
