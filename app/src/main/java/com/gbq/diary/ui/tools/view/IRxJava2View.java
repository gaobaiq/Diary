package com.gbq.diary.ui.tools.view;

import android.support.annotation.NonNull;

import com.gbq.diary.beans.PositionBean;
import com.gbq.library.base.BaseView;

import java.util.List;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/12/25 11:27.
 */
public interface IRxJava2View extends BaseView {
    void replaceList(List<PositionBean> beans);

    void replaceResult(List<String> list);

    void addResultAtEnd(@NonNull  String res);
}
