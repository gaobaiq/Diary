package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.CityBean;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 类说明：Filter过滤操作符
 * Author: Kuzan
 * Date: 2017/12/26 11:21.
 */
public class RxFilterPresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    public RxFilterPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("filter 过滤操作符");
        bean.setRemarks("filter()用来过滤观测序列中我们不想要的值，只返回满足条件的值");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("take 过滤操作符");
        bean.setRemarks("take(n)用一个整数n作为一个参数，从原始的序列中发射前n个元素;" +
                "另takeLast(n)同样用一个整数n作为参数，只不过它发射的是观测序列中后n个元素");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("takeUntil/takeWhile 过滤操作符");
        bean.setRemarks("takeUntil(Predicate) 过滤掉满足条件后的数据，只取满足条件前的数据;" +
                "takeWhile(Predicate)过滤掉满足条件后的数据，只取还没满足条件前的数据");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("takeUntil 过滤操作符");
        bean.setRemarks("takeUntil(Observable) 发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知" +
                "(括号里的参数为第二个Observable)");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_4);
        bean.setTitle("skip 过滤操作符");
        bean.setRemarks("skip(n)忽略Observable发射的前n项数据;" +
                "另skipLast(n)忽略Observable发射的后n项数据");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_5);
        bean.setTitle("elementAt 过滤操作符");
        bean.setRemarks("elementAt(n)获取元素Observable发射的事件序列中的第n项数据，并当做唯一的数据发射出去。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_6);
        bean.setTitle("debounce 过滤操作符");
        bean.setRemarks("debounce() 去除发送频率过快的数据");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_7);
        bean.setTitle("distinct 过滤操作符");
        bean.setRemarks("distinct()的过滤规则是只允许还没有发射过的数据通过，所有重复的数据项都只会发射一次。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_8);
        bean.setTitle("distinct 过滤操作符");
        bean.setRemarks("distinct(Function)参数中的Function中的apply方法会根据Observable发射的值生成一个Key，然后比较这个key来判断两个数据是不是相同；\n" +
                "如果判定为重复则会和distinct()一样过滤掉重复的数据项。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_9);
        bean.setTitle("distinctUntilChanged 过滤操作符");
        bean.setRemarks("distinctUntilChanged()和distinct()类似，只不过它判定的是Observable发射的当前数据项和前一个数据项是否相同。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_10);
        bean.setTitle("first/last 过滤操作符");
        bean.setRemarks("first()只发送符合条件的第一个数据项;last()只发送符合条件的最后一个数据");
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
                case POSITION_7:
                    onPosition7Click();
                    break;
                case POSITION_8:
                    onPosition8Click();
                    break;
                case POSITION_9:
                    onPosition9Click();
                    break;
                case POSITION_10:
                    onPosition10Click();
                    break;
            }
        }
    }

    /**
     * filter 过滤操作符
     *      filter()用来过滤观测序列中我们不想要的值，只返回满足条件的值
     * */
    private void onPosition0Click() {
        Observable.range(0, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        // 接受一个参数，让其过滤掉不符合我们条件的值
                        return integer % 2 == 0;     // 过滤掉奇数
                    }
                }).map(new Function<Integer, String>() {
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

    /**
     * take 过滤操作符
     *      take(n)用一个整数n作为一个参数，从原始的序列中发射前n个元素
     * */
    private void onPosition1Click() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .take(5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "发射10个元素，这是第 " + integer + "个";
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
     * takeUntil(Predicate)过滤操作符
     *      过滤掉满足条件后的数据，只取满足条件前的数据
     * takeWhile(Predicate)过滤操作符
     *      过滤掉满足条件后的数据，只取还没满足条件前的数据
     * */
    private void onPosition2Click() {
        // 取>=5前的数据，所以会显示0, 2, 6
        Observable.just(0, 2, 6, 3, 4, 5, 8, 1, 9)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer >= 5;
                    }
                }).map(new Function<Integer, String>() {
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

    /**
     * takeUntil(Observable)过滤操作符
     *      发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知
     * */
    private void onPosition3Click() {
        // 每隔300毫秒发一个数据
        Observable<Long> observableA = Observable.interval(300, TimeUnit.MILLISECONDS);
        // 每隔800毫秒发一个数据
        Observable<Long> observableB = Observable.interval(800, TimeUnit.MILLISECONDS);

        // A使用takeUntil实现当B发一个数据后终止发射数据，打印0和1
        observableA.takeUntil(observableB)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return String.valueOf(aLong);
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
     * skip 过滤操作符
     *      skip(n)忽略Observable发射的前n项数据
     * */
    private void onPosition4Click() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skip(5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "发射10个元素，这是第 " + integer + "个";
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
     * elementAt 过滤操作符
     *      elementAt(n)获取元素Observable发射的事件序列中的第n项数据，并当做唯一的数据发射出去。
     * */
    private void onPosition5Click() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .elementAt(5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return String.valueOf(integer);
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
     * debounce过滤操作符
     *      debounce() 去除发送频率过快的数据
     * */
    private void onPosition6Click() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A"); // skip
                Thread.sleep(400);
                emitter.onNext("B"); // deliver
                Thread.sleep(505);
                emitter.onNext("C"); // skip
                Thread.sleep(100);
                emitter.onNext("D"); // deliver
                Thread.sleep(605);
                emitter.onNext("E"); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS) // 去除发送间隔时间小于 500 毫秒的发射事件
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
     * distinct过滤操作符
     *      distinct()的过滤规则是只允许还没有发射过的数据通过，所有重复的数据项都只会发射一次。
     * */
    private void onPosition7Click() {
        Observable.just(0, 1, 1, 3, 4, 5, 3, 2, 1)
                .distinct()
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

    /**
     * distinct过滤操作符
     *      distinct(Function)参数中的Function中的apply方法会根据Observable发射的值生成一个Key，然后比较这个key来判断两个数据是不是相同；
     *      如果判定为重复则会和distinct()一样过滤掉重复的数据项。
     * */
    private void onPosition8Click() {
        List<CityBean> list = new ArrayList<>();
        list.add(new CityBean(10001L, "广州"));
        list.add(new CityBean(10002L, "珠海"));
        list.add(new CityBean(10003L, "广州"));
        list.add(new CityBean(10004L, "深圳"));
        list.add(new CityBean(10005L, "深圳"));
        list.add(new CityBean(10006L, "佛山"));
        list.add(new CityBean(10007L, "中山"));

        Observable.fromIterable(list)
                .distinct(new Function<CityBean, String>() {
                    @Override
                    public String apply(@NonNull CityBean bean) throws Exception {
                        return bean.getName();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityBean>() {
                    @Override
                    public void accept(@NonNull CityBean bean) throws Exception {
                        if (mView != null) {
                            mView.addResultAtEnd(bean.getName());
                        }
                    }
                });

    }

    /**
     * distinctUntilChanged过滤操作符
     *      distinctUntilChanged()和distinct()类似，只不过它判定的是Observable发射的当前数据项和前一个数据项是否相同。
     * */
    private void onPosition9Click() {
        Observable.just(0, 1, 1, 3, 4, 5, 3, 2, 1)
                .distinctUntilChanged()
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

    /**
     * first/last过滤操作符
     *      first()只发送符合条件的第一个数据项;last()只发送符合条件的最后一个数据;
     * */
    private void onPosition10Click() {
        String[] arrayStr = new String[] {"广州", "广州", "深圳", "珠海", "深圳", "佛山", "珠海"};

        Observable.fromArray(arrayStr)
                .last("珠海")
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

    @Override
    public void onDispose() {

    }
}
