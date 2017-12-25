package com.gbq.diary.ui.okami.view;

import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：okgo json用法
 * Author: Kuzan
 * Date: 2017/5/26 17:29.
 */
public interface IOkGoJsonView extends BaseView {
    void loadDataSuccess(OkGoRequestDataBean bean);

    void loadArrayDataSuccess(List<OkGoRequestDataBean> beans);
}
