package com.gbq.diary.ui.custom.presenter.impl;

import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.enums.PositionType;
import com.gbq.diary.ui.custom.presenter.ICustomPresenter;
import com.gbq.diary.ui.custom.view.ICustomView;
import com.gbq.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：自定义
 * Author: Kuzan
 * Date: 2017/6/16 17:18.
 */
public class CustomPresenterImpl extends BasePresenter<ICustomView> implements ICustomPresenter {

    public CustomPresenterImpl() {

    }

    @Override
    public void loadData() {
        List<PositionBean> beans = new ArrayList<>();
        PositionBean bean = new PositionBean();
        bean.setType(PositionType.POSITION_0);
        bean.setTitle("自定义");
        bean.setRemarks("拷贝至https://github.com/jeasonlzy/okhttp-OkGo, 该库是封装了okhttp的标准RESTful风格的网络框架，可以与RxJava完美结合，比Retrofit更简单易用。" +
                "支持大文件上传下载，上传进度回调，下载进度回调，表单上传（多文件和多参数一起上传），" +
                "链式调用，可以自定义返回对象，支持Https和自签名证书，支持超时自动重连，支持cookie与session的自动管理，" +
                "支持四种缓存模式缓存网络数据，支持301、302重定向，扩展了统一的上传管理和下载管理功能");
        beans.add(bean);

        mView.replaceList(beans);
    }
}
