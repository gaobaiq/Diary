package com.gbq.diary.ui.okami.presenter.impl;

import com.gbq.diary.base.BasePresenter;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.okami.presenter.IOkGoPresenter;
import com.gbq.diary.ui.okami.view.IOkGoView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：okgo
 * Author: Kuzan
 * Date: 2017/5/26 17:10.
 */
public class IOkGoPresenterImpl extends BasePresenter<IOkGoView> implements IOkGoPresenter {

    public IOkGoPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("基本用法");
        bean.setRemarks("json数据解析");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_1);
        bean.setTitle("方件下载");
        bean.setRemarks("大文件下载和小文件下载，监控下载进度和下载网速，并可以自定义保存目录和文件名");
        beans.add(bean);

        bean = new PositionBean();
        bean.setType(PositionType.POSITION_2);
        bean.setTitle("文件上传");
        bean.setRemarks("单个文件上传和多个文件上传，监控上传进度和上传网速");
        beans.add(bean);


        mView.replaceList(beans);
    }
}
