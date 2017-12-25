package com.gbq.diary.ui.tools.view;

import com.gbq.diary.beans.PositionBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：常用工具列表
 * Author: Kuzan
 * Date: 2017/12/25 10:30.
 */
public interface IToolsListView extends BaseView {
    /**工具列表*/
    void replaceList(List<PositionBean> beans);
}
