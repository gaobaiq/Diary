package com.gbq.diary.ui.custom.view;

import com.gbq.diary.beans.PositionBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：自定义
 * Author: Kuzan
 * Date: 2017/6/16 17:17.
 */
public interface ICustomView extends BaseView {
    void replaceList(List<PositionBean> beans);
}
