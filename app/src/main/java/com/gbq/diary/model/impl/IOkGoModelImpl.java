package com.gbq.diary.model.impl;

import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.diary.constants.UrlConstants;
import com.gbq.diary.model.IOkGoModel;
import com.gbq.library.callback.FileCallback;
import com.gbq.library.callback.JsonCallback;
import com.gbq.library.network.BaseResponse;
import com.gbq.library.network.OkGoRequest;
import com.gbq.library.okgo.model.HttpHeaders;
import com.gbq.library.okgo.model.HttpParams;

import java.io.File;
import java.util.List;

/**
 * 类说明：okgo网络加载
 * Author: Kuzan
 * Date: 2017/5/27 11:18.
 */
public class IOkGoModelImpl implements IOkGoModel {

    // 请求头部参数
    private HttpHeaders headers;
    // 请求参数
    private HttpParams params;

    public IOkGoModelImpl() {
        headers = new HttpHeaders();
        params = OkGoRequest.getAppParams();
    }

    @Override
    public void loadJsonData(Object tag, String param1, JsonCallback<BaseResponse<OkGoRequestDataBean>> callback) {
        headers.put("header1", "headerValue1");
        params.put("param1", param1);
        OkGoRequest.jsonRequest(UrlConstants.URL_JSON_OBJECT, tag, headers, params, callback);
    }

    @Override
    public void loadJsonArrayData(Object tag, String param1, JsonCallback<BaseResponse<List<OkGoRequestDataBean>>> callback) {
        headers.put("header1", "headerValue1");
        params.put("param1", param1);
        OkGoRequest.jsonRequest(UrlConstants.URL_JSON_ARRAY, tag, headers, params, callback);
    }

    @Override
    public void downloadFile(Object tag, String param1, FileCallback callback) {
        headers.put("header1", "headerValue1");
        params.put("param1", param1);
        OkGoRequest.downloadRequest(UrlConstants.URL_DOWNLOAD, tag, headers, params, callback);
    }

    @Override
    public void uploadFile(Object tag, String param1, List<File> files, JsonCallback<BaseResponse<OkGoRequestDataBean>> callback) {
        headers.put("header1", "headerValue1");
        params.put("param1", param1);
        OkGoRequest.uploadRequest(UrlConstants.URL_FORM_UPLOAD, tag, headers, params, files, callback);
    }
}
