package com.gbq.diary.ui.main.widget;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gbq.diary.R;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.ui.main.presenter.impl.IMainPresenterImpl;
import com.gbq.diary.ui.main.view.IMainView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类说明：首页
 * Author: Kuzan    github 地址  https://github.com/gaobaiq
 * Date: 2017/5/25 18:23.
 */
public class MainActivity extends BaseActivity<IMainView, IMainPresenterImpl> implements IMainView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected IMainPresenterImpl initPresenter() {
        return new IMainPresenterImpl();
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected void initViewAndData() {
        initToolBar(toolbar, false, "");
    }

    @OnClick({R.id.btn_my_github, R.id.layout_okami, R.id.layout_tools, R.id.layout_custom, R.id.layout_other, R.id.layout_my_info})
    void onBtnClick(View view) {
        mPresenter.onBtnClick(view.getId());
    }

    @Override
    public void onMyGithubClick() {
        //ToastUtils.ToastMessage(this, "我的github");
    }

    @Override
    public void onOkamiClick() {
        //ToastUtils.ToastMessage(this, "大神杰作");
    }

    @Override
    public void onToolsClick() {
        //ToastUtils.ToastMessage(this, "常用工具");
    }

    @Override
    public void onCustomClick() {
        //ToastUtils.ToastMessage(this, "自定义");
    }

    @Override
    public void onOtherClick() {
        //ToastUtils.ToastMessage(this, "其他");
    }

    @Override
    public void onMyInfoClick() {
        //ToastUtils.ToastMessage(this, "我的信息");
    }
}
