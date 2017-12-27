package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.beans.ProvinceBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.diary.utils.AddressUtil;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 类说明：事件发射操作符
 * Author: Kuzan
 * Date: 2017/12/26 16:30.
 */
public class RxCreatePresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    public RxCreatePresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("create 操作符");
        bean.setRemarks("主要用于产生一个 Observable 被观察者对象");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("fromArray 操作符");
        bean.setRemarks("将array数据发射出去");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("fromIterable 操作符");
        bean.setRemarks("将List<T>数据发射出去");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("fromCallable 操作符");
        bean.setRemarks("使用fromCallable有两点好处：1、获取发送数据的代码只会在有Observer订阅之后执行 ；" +
                "2、获取数据的代码可以在子线程中执行");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_4);
        bean.setTitle("just 操作符");
        bean.setRemarks("将对象一一发射，不得超过10个对象，若超过10个对象，可以将对象封装成array数组/list集合，然后添加到just里去");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_5);
        bean.setTitle("range 操作符");
        bean.setRemarks("range操作符是创建一组在从n开始，个数为m的连续数字，比如range(3,10)，就是创建3、4、5…12的一组数字");
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
     * create操作符
     * */
    private void onPosition0Click() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                // emitter发射器，用来发射事件
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onNext("c");
                emitter.onComplete();   // 结束发射，下面的数据不会被发射
                emitter.onNext("d");    // 不会被发射
            }
        }).observeOn(AndroidSchedulers.mainThread())
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
     * fromArray操作符
     *      将array数据发射出去
     * */
    private void onPosition1Click() {
        String[] arrayStr = new String[] {"a", "b", "c", "d"};
        Observable.fromArray(arrayStr)
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
     * fromIterable操作符
     *      将List<T>数据发射出去
     * */
    private void onPosition2Click() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Observable.fromIterable(list)
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
     * fromCallable操作符
     *      使用fromCallable有两点好处：
     *          1、获取发送数据的代码只会在有Observer订阅之后执行 ；
     *          2、获取数据的代码可以在子线程中执行
     * */
    private void onPosition3Click() {
        Single.fromCallable(new Callable<List<ProvinceBean>>() {
            @Override
            public List<ProvinceBean> call() throws Exception {
                // 获取数据
                return AddressUtil.getAddress();
            }
        }).map(new Function<List<ProvinceBean>, List<String>>() {
            @Override
            public List<String> apply(@NonNull List<ProvinceBean> provinceBeen) throws Exception {
                List<String > list = new ArrayList<>();
                for (ProvinceBean bean : provinceBeen) {
                    list.add(bean.getName());
                }
                return list;
            }
        }).subscribeOn(Schedulers.io()) // 获取数据在子线程进行
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> list) throws Exception {
                        if (mView != null) {
                            mView.replaceResult(list);
                        }
                    }
                });
    }

    /**
     * just操作符
     *      将对象一一发射，不得超过10个对象，若超过10个对象，可以将对象封装成array数组/list集合，然后添加到just里去
     * */
    private void onPosition4Click() {
        Observable.just("Hello", "RxJava")
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
     * range 操作符
     *      range操作符是创建一组在从n开始，个数为m的连续数字，比如range(3,10)，就是创建3、4、5…12的一组数字
     * */
    private void onPosition5Click() {
        Observable.range(3, 10)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return String.valueOf(integer);
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
