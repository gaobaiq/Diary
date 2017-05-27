package com.gbq.diary.ui.okami.presenter.impl;

import com.gbq.diary.base.BasePresenter;
import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.diary.model.IOkGoModel;
import com.gbq.diary.model.impl.IOkGoModelImpl;
import com.gbq.diary.ui.okami.presenter.IOkGoUploadPresenter;
import com.gbq.diary.ui.okami.view.IOkGoUploadView;
import com.gbq.library.callback.JsonCallback;
import com.gbq.library.network.BaseResponse;
import com.gbq.library.okgo.request.BaseRequest;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 类说明：文件上传
 * Author: Kuzan
 * Date: 2017/5/27 14:37.
 */
public class IOkGoUploadPresenterImpl extends BasePresenter<IOkGoUploadView> implements IOkGoUploadPresenter {
    private IOkGoModel mModel;

    public IOkGoUploadPresenterImpl() {
        this.mModel = new IOkGoModelImpl();
    }

    @Override
    public void uploadFile(Object tag, String param1, List<File> files) {
        mModel.uploadFile(tag, param1, files, new JsonCallback<BaseResponse<OkGoRequestDataBean>>() {
            @Override
            public void onBefore(BaseRequest request) {
                if (mView != null) {
                    mView.onUploadBefore();
                }
            }

            @Override
            public void onSuccess(BaseResponse<OkGoRequestDataBean> objectBaseResponse, Call call, Response response) {
                if (mView != null) {
                    mView.onUploadSuccess();
                }
            }

            @Override
            public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                if (mView != null) {
                    mView.onUploadProgress(currentSize, totalSize, progress, networkSpeed);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (mView != null) {
                    mView.loadErr(true, e+"");
                }
            }
        });
    }
}
