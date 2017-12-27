package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.CityBean;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.beans.ProvinceBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxResultPresenter;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.diary.utils.AddressUtil;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 类说明：Rx转换操作符结果页面
 * Author: Kuzan
 * Date: 2017/12/26 10:25.
 */
public class RxMapPresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    public RxMapPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("map 转换操作符");
        bean.setRemarks("map 操作符可以将一个 Observable 对象通过某种关系转换为另一个Observable 对象(一对一)");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("flatMap 转换操作符");
        bean.setRemarks("flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，然后将它们发射的数据合并后放到一个单一的 Observable，" +
                "然后发送(一对多)，注：flatMap 并不能保证事件的顺序");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("concatMap 转换操作符");
        bean.setRemarks("concatMap 与flatMap 转换操作符一样，它完善了flatMap的缺点，保证了事件的顺序");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("flatMapIterable 转换操作符");
        bean.setRemarks("flatMapIterable()和flatMap()几乎是一样的，不同的是flatMapIterable()它转化的多个Observable是使用Iterable作为源数据的");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_4);
        bean.setTitle("scan 转换操作符");
        bean.setRemarks("scan()对一个序列的数据应用一个函数，并将这个函数的结果发射出去作为下个数据应用合格函数时的第一个参数使用");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_5);
        bean.setTitle("groupBy 转换操作符");
        bean.setRemarks("groupBy()操作符顾名思义就是分组的意思:将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列");
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
     * map转换操作符
     *      map 操作符可以将一个 Observable 对象通过某种关系转换为另一个Observable 对象
     * */
    private void onPosition0Click() {
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
    private void onPosition1Click() {
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
     *      注：完善了flatMap 保证了事件的顺序
     * */
    private void onPosition2Click() {
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

    /**
     * flatMapIterable 转换操作符
     *      flatMapIterable()和flatMap()几乎是一样的，
     *      不同的是flatMapIterable()它转化的多个Observable是使用Iterable作为源数据的
     * */
    private void onPosition3Click() {
        // 只打印市数据
        Observable.fromIterable(AddressUtil.getAddress())
                .flatMapIterable(new Function<ProvinceBean, Iterable<CityBean>>() {
                    @Override
                    public Iterable<CityBean> apply(@NonNull ProvinceBean bean) throws Exception {
                        return bean.getCityList();
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
     * scan 转换操作符
     *      scan()对一个序列的数据应用一个函数，并将这个函数的结果发射出去作为下个数据应用合格函数时的第一个参数使用
     * */
    private void onPosition4Click() {
        // 只打印市数据
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        // 在这里，执行函数操作，并将结果赋值integer等下一个数据进来使用
                        Timber.e("integer = " + integer + ",integer2 = " + integer2);
                        return integer + integer2;
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
     * groupBy 转换操作符
     *      groupBy()操作符顾名思义就是分组的意思:
     *      将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列
     * */
    private void onPosition5Click() {
        Observable.range(0, 10).groupBy(new Function<Integer, Integer>() {
            @Override
            public Integer apply(@NonNull Integer integer) throws Exception {
                // 实现分组条件
                return integer % 2; // 分成奇数和偶数两个组
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void accept(@NonNull GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) throws Exception {
                        // 发送子序列
                        integerIntegerGroupedObservable.map(new Function<Integer, String>() {
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
                });
    }

    @Override
    public void onDispose() {

    }
}
