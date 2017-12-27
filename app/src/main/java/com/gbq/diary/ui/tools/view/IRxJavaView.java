package com.gbq.diary.ui.tools.view;

import com.gbq.diary.beans.PositionBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：RxJava操作符页面
 * Author: Kuzan
 * Date: 2017/12/25 11:27.
 */
public interface IRxJavaView extends BaseView {
    void replaceList(List<PositionBean> beans);
}
