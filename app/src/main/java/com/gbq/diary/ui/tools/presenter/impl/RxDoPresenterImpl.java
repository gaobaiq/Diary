package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 类说明：Rx do 辅助操作符
 * Author: Kuzan
 * Date: 2017/12/27 9:57.
 */
public class RxDoPresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    private Disposable mDisposable2;
    private Disposable mDisposable6;

    public RxDoPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("doOnNext");
        bean.setRemarks("doOnNext 让订阅者在接收到数据之前干点有意思的事情。假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("doOnEach");
        bean.setRemarks("doOnEach 注册一个回调，它产生的Observable每发射一项数据就会调用它一次");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("doOnSubscribe");
        bean.setRemarks("doOnSubscribe 注册一个动作，在观察者订阅时使用");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("doOnComplete");
        bean.setRemarks("doOnComplete 注册一个动作，在观察者OnComplete时使用");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_4);
        bean.setTitle("doOnError");
        bean.setRemarks("doOnError 注册一个动作，在观察者doOnError时使用");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_5);
        bean.setTitle("doOnTerminate");
        bean.setRemarks("doOnTerminate 注册一个动作，Observable终止之前会被调用，无论是正常还是异常终止。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_6);
        bean.setTitle("doOnDispose");
        bean.setRemarks("doOnDispose 注册一个动作,当【观察者取消】订阅它生成的Observable它就会被调用。");
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
                case POSITION_6:
                    onPosition6Click();
                    break;
            }
        }
    }

    /**
     * doOnNext
     *      doOnNext 让订阅者在接收到数据之前干点有意思的事情。
     *      假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。
     * */
    private void onPosition0Click() {
        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Timber.e("doOnNext:" + s);
                        if (mView != null) {
                            mView.addResultAtEnd("doOnNext:" + s);
                        }
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
     * doOnEach
     *      doOnEach 注册一个回调，它产生的Observable每发射一项数据就会调用它一次
     * */
    private void onPosition1Click() {
        Observable.range(0, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return String.valueOf(integer);
                    }
                })
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(@NonNull Notification<String> stringNotification) throws Exception {
                        Timber.e("doOnEach:" + stringNotification.getValue());
                        if (mView != null) {
                            mView.addResultAtEnd("doOnEach:" + stringNotification.getValue());
                        }
                    }
                })
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
     * doOnSubscribe
     *      doOnSubscribe 注册一个动作，在观察者订阅时使用
     * */
    private void onPosition2Click() {
        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mDisposable2 = disposable;
                        if (mView != null) {
                            mView.addResultAtEnd("doOnSubscribe");
                        }
                    }
                })
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
     * doOnComplete
     *      doOnComplete 注册一个动作，在观察者OnComplete时使用
     * */
    private void onPosition3Click() {
        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd("doOnComplete");
                        }
                    }
                })
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
     * doOnError
     *      doOnError 注册一个动作，在观察者doOnError时使用
     * */
    private void onPosition4Click() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("one");
                emitter.onNext("two");
                emitter.onNext("three");
                emitter.onNext("four");
                emitter.onNext("five");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd("doOnError");
                        }
                    }
                })
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
     * doOnTerminate
     *      doOnTerminate 注册一个动作，Observable终止之前会被调用，无论是正常还是异常终止。
     * */
    private void onPosition5Click() {
        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd("doOnTerminate");
                        }
                    }
                })
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
     * doOnDispose
     *      doOnDispose 注册一个动作,当【观察者取消】订阅它生成的Observable它就会被调用。
     *      貌似需要在出现complete或error的时候doOnDispose才会触发
     * */
    private void onPosition6Click() {
        Observable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mDisposable6 = disposable;
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (s.equals("four")) {
                            mDisposable6.dispose();
                        }
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd("doOnDispose");
                        }
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd(s);
                        }
                    }
                });
    }

    @Override
    public void onDispose() {
        if (mDisposable2 != null && !mDisposable2.isDisposed()) {
            mDisposable2.dispose();
        }
        if (mDisposable6 != null && !mDisposable6.isDisposed()) {
            mDisposable6.dispose();
        }
    }
}
