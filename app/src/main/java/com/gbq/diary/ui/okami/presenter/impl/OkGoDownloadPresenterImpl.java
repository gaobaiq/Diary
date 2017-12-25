package com.gbq.diary.ui.okami.presenter.impl;

import com.gbq.diary.model.IOkGoModel;
import com.gbq.diary.model.impl.OkGoModelImpl;
import com.gbq.diary.ui.okami.presenter.IOkGoDownloadPresenter;
import com.gbq.diary.ui.okami.view.IOkGoDownloadView;
import com.gbq.library.base.BasePresenter;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

/**
 * 类说明：okgo文件下载
 * Author: Kuzan
 * Date: 2017/5/27 13:39.
 */
public class OkGoDownloadPresenterImpl extends BasePresenter<IOkGoDownloadView> implements IOkGoDownloadPresenter {
    private IOkGoModel mModel;

    public OkGoDownloadPresenterImpl() {
        this.mModel = new OkGoModelImpl();
    }

    @Override
    public void downloadFile(Object tag, String param1, String fileName) {
        mModel.downloadFile(tag, param1, new FileCallback(fileName) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {

            }

        });
    }
}
