package com.gbq.diary.ui.tools.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.tools.presenter.IToolsListPresenter;
import com.gbq.diary.ui.tools.view.IToolsListView;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：常用工具列表
 * Author: Kuzan
 * Date: 2017/12/25 10:33.
 */
public class ToolsListPresenterImpl extends BasePresenter<IToolsListView> implements IToolsListPresenter {

    public ToolsListPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("RxJava2");
        bean.setRemarks("RxJava2的使用与学习");
        beans.add(bean);

        mView.replaceList(beans);
    }
}
