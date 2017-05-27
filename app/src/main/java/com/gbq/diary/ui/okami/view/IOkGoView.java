package com.gbq.diary.ui.okami.view;

import com.gbq.diary.base.BaseView;
import com.gbq.diary.beans.PositionBean;

import java.util.List;

/**
 * 类说明：okgo
 * Author: Kuzan
 * Date: 2017/5/26 17:06.
 */
public interface IOkGoView extends BaseView {
    void replaceList(List<PositionBean> beans);
}
