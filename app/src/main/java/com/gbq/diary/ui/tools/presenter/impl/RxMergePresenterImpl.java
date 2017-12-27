package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.library.base.BasePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 类说明：Merge组合操作符
 * Author: Kuzan
 * Date: 2017/12/26 14:54.
 */
public class RxMergePresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    public RxMergePresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("merge 组合操作符");
        bean.setRemarks("merge(Observable, Observable)将两个Observable发射的事件序列组合并成一个事件序列，就像是一个Observable发射的一样。" +
                "你可以简单的将它理解为两个Observable合并成了一个Observable，合并后的数据是无序的。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("startWith 组合操作符");
        bean.setRemarks("startWith(T)用于在源Observable发射的数据前插入数据。" +
                "使用startWith(Iterable<T>)我们还可以在源Observable发射的数据前插入Iterable");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("concat 组合操作符");
        bean.setRemarks("用于将多个observable发射的的数据进行合并发射，concat严格按照顺序发射数据，" +
                "前一个Observable没发射完是不会发射后一个Observable的数据的，" +
                "场景：采用 concat 操作符先读取缓存再通过网络请求获取数据");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("zip 组合操作符");
        bean.setRemarks("zip 专用于合并事件，该合并不是连接（连接操作符后面会说），而是两两配对，" +
                "也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同,注：" +
                "zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，" +
                "组合的顺序是严格按照事件发送的顺序来进行的");
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
     * merge 组合操作符
     *      merge(Observable, Observable)将两个Observable发射的事件序列组合并成一个事件序列，就像是一个Observable发射的一样。" +
     *          "你可以简单的将它理解为两个Observable合并成了一个Observable，合并后的数据是无序的。
     * */
    private void onPosition0Click() {
        final String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        // 发射字母
        Observable<String> letterObservable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return letters[aLong.intValue()];
                    }
                }).take(letters.length);
        // 发射数字
        Observable<Long> numberObservable = Observable.interval(500, TimeUnit.MILLISECONDS).take(5);

        Observable.merge(letterObservable, numberObservable)
                .map(new Function<Serializable, String>() {
                    @Override
                    public String apply(@NonNull Serializable serializable) throws Exception {
                        return serializable.toString();
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
     * 格startWith()组合操作符
     *      startWith(T)用于在源Observable发射的数据前插入数据。使用startWith(Iterable<T>)我们还可以在源Observable发射的数据前插入Iterable
     * */
    private void onPosition1Click() {
        final String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        // 发射字母
        Observable<String> letterObservable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return letters[aLong.intValue()];
                    }
                }).take(letters.length);

        Observable.just("1", "2", "3", "4", "5")
                .startWith(letterObservable)
                .subscribeOn(Schedulers.io())
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
     * concat() 组合操作符
     *      用于将多个observable发射的的数据进行合并发射，concat严格按照顺序发射数据，
     *      前一个Observable没发射完是不会发射后一个Observable的数据的
     * */
    private void onPosition2Click() {
        final String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        // 发射字母
        Observable<String> letterObservable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return letters[aLong.intValue()];
                    }
                }).take(letters.length);
        // 发射数字
        Observable<Long> numberObservable = Observable.interval(500, TimeUnit.MILLISECONDS).take(5);

        Observable.concat(letterObservable, numberObservable)
                .map(new Function<Serializable, String>() {
                    @Override
                    public String apply(@NonNull Serializable serializable) throws Exception {
                        return serializable.toString();
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
     * zip组合操作符
     *      zip 专用于合并事件，该合并不是连接（连接操作符后面会说），而是两两配对，
     *      也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同,注：
     *      zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，
     *      组合的顺序是严格按照事件发送的顺序来进行的
     * */
    private void onPosition3Click() {
        final String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        // 发射字母
        Observable<String> letterObservable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return letters[aLong.intValue()];
                    }
                }).take(letters.length);
        // 发射数字
        Observable<Long> numberObservable = Observable.interval(500, TimeUnit.MILLISECONDS).take(5);

        Observable.zip(letterObservable, numberObservable, new BiFunction<String, Long, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Long aLong) throws Exception {
                return s + aLong;
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

    @Override
    public void onDispose() {

    }
}
