package com.gbq.diary.ui.okami.presenter.impl;

import com.gbq.diary.model.IOkGoModel;
import com.gbq.diary.model.impl.OkGoModelImpl;
import com.gbq.diary.ui.okami.presenter.IOkGoJsonPresenter;
import com.gbq.diary.ui.okami.view.IOkGoJsonView;
import com.gbq.library.base.BasePresenter;

/**
 * 类说明：okgo json用法
 * Author: Kuzan
 * Date: 2017/5/26 17:30.
 */
public class OkGoPresenterJsonImpl extends BasePresenter<IOkGoJsonView> implements IOkGoJsonPresenter {

    private IOkGoModel mModel;

    public OkGoPresenterJsonImpl() {
        this.mModel = new OkGoModelImpl();
    }

    @Override
    public void loadData(Object tag, String param1) {
//        mModel.loadJsonData(tag, param1, new JsonCallback<BaseResponse<OkGoRequestDataBean>>() {
//            @Override
//            public void onSuccess(BaseResponse<OkGoRequestDataBean> bean, Call call, Response response) {
//                if (mView != null) {
//                    Log.e("TAG", bean.getData().toString());
//                    mView.loadDataSuccess(bean.getData());
//                }
//            }
//
//            @Override
//            public void onError(Call call, Response response, Exception e) {
//                super.onError(call, response, e);
//                if (mView != null) {
//                    mView.loadErr(true, e.toString());
//                }
//            }
//        });
    }

    @Override
    public void loadArrayList(Object tag, String param1) {
//        mModel.loadJsonArrayData(tag, param1, new JsonCallback<BaseResponse<List<OkGoRequestDataBean>>>() {
//            @Override
//            public void onSuccess(BaseResponse<List<OkGoRequestDataBean>> bean, Call call, Response response) {
//                if (mView != null) {
//                    mView.loadArrayDataSuccess(bean.getData());
//                }
//            }
//
//            @Override
//            public void onError(Call call, Response response, Exception e) {
//                super.onError(call, response, e);
//                if (mView != null) {
//                    mView.loadErr(true, e.toString());
//                }
//            }
//        });
    }
}
