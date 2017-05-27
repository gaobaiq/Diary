package com.gbq.diary.ui.okami.presenter;

import java.io.File;
import java.util.List;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/5/27 14:37.
 */
public interface IOkGoUploadPresenter {
    void uploadFile(Object tag, String param1, List<File> files);
}
