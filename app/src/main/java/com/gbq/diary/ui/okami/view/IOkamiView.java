package com.gbq.diary.ui.okami.view;

import com.gbq.diary.beans.PositionBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：大神杰作首页
 * Author: Kuzan
 * Date: 2017/5/26 11:35.
 */
public interface IOkamiView extends BaseView {
    void replaceList(List<PositionBean> beans);
}
