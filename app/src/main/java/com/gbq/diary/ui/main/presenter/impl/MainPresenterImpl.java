package com.gbq.diary.ui.main.presenter.impl;

import com.gbq.diary.R;
import com.gbq.diary.ui.main.presenter.IMainPresenter;
import com.gbq.diary.ui.main.view.IMainView;
import com.gbq.library.base.BasePresenter;

/**
 * 类说明：首页
 * Author: Kuzan
 * Date: 2017/5/25 17:20.
 */
public class MainPresenterImpl extends BasePresenter<IMainView> implements IMainPresenter {

    public MainPresenterImpl() {

    }

    @Override
    public void onBtnClick(int viewId) {
        switch (viewId) {
            case R.id.btn_my_github:
                mView.onMyGithubClick();
                break;

            case R.id.layout_okami:
                mView.onOkamiClick();
                break;

            case R.id.layout_tools:
                mView.onToolsClick();
                break;

            case R.id.layout_custom:
                mView.onCustomClick();
                break;

            case R.id.layout_other:
                mView.onOtherClick();
                break;

            case R.id.layout_my_info:
                mView.onMyInfoClick();
                break;
        }
    }
}
