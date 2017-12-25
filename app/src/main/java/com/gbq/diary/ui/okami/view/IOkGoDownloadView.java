package com.gbq.diary.ui.okami.view;

import com.gbq.library.base.BaseView;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/27 13:38.
 */
public interface IOkGoDownloadView extends BaseView {
    void onDownloadBefore();

    void onDownloadSuccess();

    void onDownloadProgress(long currentSize, long totalSize, float progress, long networkSpeed);
}
