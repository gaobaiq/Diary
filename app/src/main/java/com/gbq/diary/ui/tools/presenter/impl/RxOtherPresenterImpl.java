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
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 类说明：Rx 其他操作符
 * Author: Kuzan
 * Date: 2017/12/26 19:10.
 */
public class RxOtherPresenterImpl extends BasePresenter<IRxResultView> implements IRxResultPresenter {

    public RxOtherPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("reduce");
        bean.setRemarks("reduce操作符实际上是把传入的数据进行两两比较，直到比较出最值，" +
                "传入的数据小于2，那么reduce内的方法不会执行。");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("buffer");
        bean.setRemarks("buffer 操作符接受两个参数，buffer(count,skip)，" +
                "作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，" +
                "然后生成一个 Observable");
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
            }
        }
    }

    /**
     * reduce
     *      reduce操作符实际上是把传入的数据进行两两比较，直到比较出最值，
     *      传入的数据小于2，那么reduce内的方法不会执行。
     * */
    private void onPosition0Click() {
        Observable.just(1, 23, 42, 27, 10, 8, 19)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        // 求出最大值
                        return integer > integer2 ? integer : integer2;
                    }
                }).map(new Function<Integer, String>() {
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
     * buffer操作符
     *      buffer 操作符接受两个参数，buffer(count,skip)，作用是将 Observable 中的数据按 skip (步长)
     *      分成最大不超过 count 的 buffer ，然后生成一个 Observable
     * */
    private void onPosition1Click() {
        Observable.just("one", "two", "three", "four", "five")
                .buffer(3, 2)   // 3->list的长度不超过3; 2->每一次跳2个数
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> list) throws Exception {
                        // 打印 one two three; three four five; five
                        if (mView != null) {
                            mView.addResultAtEnd(list.toString());
                        }
                    }
                });
    }

    @Override
    public void onDispose() {

    }
}
