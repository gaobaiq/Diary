package com.gbq.diary.ui.okami.presenter.impl;

import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.diary.callback.JsonCallback;
import com.gbq.diary.model.IOkGoModel;
import com.gbq.diary.model.impl.OkGoModelImpl;
import com.gbq.diary.ui.okami.presenter.IOkGoUploadPresenter;
import com.gbq.diary.ui.okami.view.IOkGoUploadView;
import com.gbq.library.base.BasePresenter;
import com.gbq.library.beans.BaseResponse;

import java.io.File;
import java.util.List;

import okhttp3.Response;

/**
 * 类说明：文件上传
 * Author: Kuzan
 * Date: 2017/5/27 14:37.
 */
public class OkGoUploadPresenterImpl extends BasePresenter<IOkGoUploadView> implements IOkGoUploadPresenter {
    private IOkGoModel mModel;

    public OkGoUploadPresenterImpl() {
        this.mModel = new OkGoModelImpl();
    }

    @Override
    public void uploadFile(Object tag, String param1, List<File> files) {
        mModel.uploadFile(tag, param1, files, new JsonCallback<BaseResponse<OkGoRequestDataBean>>() {

            @Override
            public BaseResponse<OkGoRequestDataBean> convertResponse(Response response) throws Throwable {
                return null;
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<BaseResponse<OkGoRequestDataBean>> response) {

            }
        });
    }
}
