package com.gbq.diary.ui.tools.presenter;

import com.gbq.diary.beans.PositionBean;

/**
 * 类说明：Rx操作结果页面
 * Author: Kuzan
 * Date: 2017/12/26 10:24.
 */
public interface IRxResultPresenter {
    void loadData();

    void onPositionClick(final PositionBean bean);

    void onDispose();
}
