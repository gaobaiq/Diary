package com.gbq.diary.ui.okami.presenter.impl;

import com.gbq.diary.base.BasePresenter;
import com.gbq.diary.model.IOkGoModel;
import com.gbq.diary.model.impl.IOkGoModelImpl;
import com.gbq.diary.ui.okami.presenter.IOkGoDownloadPresenter;
import com.gbq.diary.ui.okami.view.IOkGoDownloadView;
import com.gbq.library.callback.FileCallback;
import com.gbq.library.okgo.request.BaseRequest;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 类说明：okgo文件下载
 * Author: Kuzan
 * Date: 2017/5/27 13:39.
 */
public class IOkGoDownloadPresenterImpl extends BasePresenter<IOkGoDownloadView> implements IOkGoDownloadPresenter {
    private IOkGoModel mModel;

    public IOkGoDownloadPresenterImpl() {
        this.mModel = new IOkGoModelImpl();
    }

    @Override
    public void downloadFile(Object tag, String param1, String fileName) {
        mModel.downloadFile(tag, param1, new FileCallback(fileName) {
            @Override
            public void onBefore(BaseRequest request) {
                if (mView != null) {
                    mView.onDownloadBefore();
                }
            }

            @Override
            public void onSuccess(File file, Call call, Response response) {
                if (mView != null) {
                    mView.onDownloadSuccess();
                }
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                if (mView != null) {
                    mView.onDownloadProgress(currentSize, totalSize, progress, networkSpeed);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (mView != null) {
                    mView.loadErr(true, "" + e);
                }
            }
        });
    }
}
