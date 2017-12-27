package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 类说明：Rx Schedulers线程调度
 * Author: Kuzan
 * Date: 2017/12/26 10:45.
 */
public class RxSchedulersPresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    public RxSchedulersPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("subScribeOn/observeOn");
        bean.setRemarks("线程调度");
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
            }
        }
    }

    /**
     * 线程调度
     *      subscribeOn() 指定的就是发射事件的线程，通俗说是数据处理线程
     *      observerOn 指定的就是订阅者接收事件的线程，通俗说是数据显示线程
     * */
    private void onPosition0Click() {
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

    @Override
    public void onDispose() {

    }
}
