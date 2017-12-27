package com.gbq.diary.ui.tools.view;

import android.support.annotation.NonNull;

import com.gbq.diary.beans.PositionBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：Rx操作结果页面
 * Author: Kuzan
 * Date: 2017/12/26 10:23.
 */
public interface IRxResultView extends BaseView {
    void replaceList(List<PositionBean> beans);

    void replaceResult(List<String> list);

    void addResultAtEnd(@NonNull String res);
}
