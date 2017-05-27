package com.gbq.diary.ui.okami.view;

import com.gbq.diary.base.BaseView;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/27 14:35.
 */
public interface IOkGoUploadView extends BaseView {
    void onUploadBefore();

    void onUploadSuccess();

    void onUploadProgress(long currentSize, long totalSize, float progress, long networkSpeed);
}
