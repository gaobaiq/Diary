package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IRxJavaPresenter;
import com.gbq.diary.ui.tools.view.IRxJavaView;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：Rx 操作符页面
 * Author: Kuzan
 * Date: 2017/12/25 11:27.
 */
public class RxJavaPresenterImpl extends BasePresenter<IRxJavaView> implements IRxJavaPresenter {

    public RxJavaPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("Rx 基本操作符");
        bean.setRemarks("Observable/Flowable/Consumer/Single等介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("Rx Schedulers 线程调度");
        bean.setRemarks("subScribeOn/observeOn介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("Rx Create 事件发射操作符");
        bean.setRemarks("create/from/just等事件发射操作符");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_3);
        bean.setTitle("Map 转换操作符");
        bean.setRemarks("map/flatMap/concatMap/flatMapIterable/switchMap/scan/groupBy等转换操作符介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_4);
        bean.setTitle("Filter 过滤操作符");
        bean.setRemarks("filter/take/takeLast/takeUntil/skip/skipLast/elementAt/debounce/distinct/distinctUntilChanged/first/last等过滤操作符介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_5);
        bean.setTitle("Merge 组合操作符");
        bean.setRemarks("merge/startWith/concat/zip等组合操作符介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_6);
        bean.setTitle("Timer 时间操作符");
        bean.setRemarks("timer/interval时间有关操作符介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_7);
        bean.setTitle("其他操作符");
        bean.setRemarks("reduce/buffer等有关操作符介绍");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_8);
        bean.setTitle("do 辅助操作符");
        bean.setRemarks("doOnNext/doOnSubscribe/doOnEach/doOnComplete/doOnError/doOnTerminate/doOnDispose等有关操作符介绍");
        beans.add(bean);

        mView.replaceList(beans);
    }
}
