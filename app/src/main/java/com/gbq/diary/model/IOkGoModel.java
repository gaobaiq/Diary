package com.gbq.diary.model;

import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.diary.callback.JsonCallback;
import com.gbq.library.beans.BaseResponse;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;
import java.util.List;

/**
 * 类说明：OkGo网络加载
 * Author: Kuzan
 * Date: 2017/5/27 11:15.
 */
public interface IOkGoModel {
    void loadJsonData(Object tag, String param1, JsonCallback<BaseResponse<OkGoRequestDataBean>> callback);

    void loadJsonArrayData(Object tag, String param1, JsonCallback<BaseResponse<List<OkGoRequestDataBean>>> callback);

    void downloadFile(Object tag, String param1, FileCallback callback);

    void uploadFile(Object tag, String param1, List<File> files, JsonCallback<BaseResponse<OkGoRequestDataBean>> callback);
}
