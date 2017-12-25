package com.gbq.diary.ui.tools.presenter;

import com.gbq.diary.beans.PositionBean;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/12/25 11:27.
 */
public interface IRxJava2Presenter {
    void loadData();

    void onPositionClick(final PositionBean bean);

    void onDispose();
}
