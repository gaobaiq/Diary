package com.gbq.diary.ui.main.widget;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.gbq.diary.R;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.ui.main.presenter.impl.MainPresenterImpl;
import com.gbq.diary.ui.main.view.IMainView;
import com.gbq.diary.ui.okami.widget.OkamiActivity;
import com.gbq.diary.ui.tools.widget.ToolsListActivity;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.toast.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类说明：首页
 * Author: Kuzan    github 地址  https://github.com/gaobaiq
 * Date: 2017/5/25 18:23.
 */
public class MainActivity extends BaseActivity<IMainView, MainPresenterImpl> implements IMainView {

    @Bind(R.id.toolbar)
    BaseBar toolbar;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenterImpl initPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected void initViewAndData() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        initToolBar(toolbar, false, "");
    }

    @OnClick({R.id.btn_my_github, R.id.layout_okami, R.id.layout_tools, R.id.layout_custom, R.id.layout_other, R.id.layout_my_info})
    void onBtnClick(View view) {
        mPresenter.onBtnClick(view.getId());
    }

    @Override
    public void onMyGithubClick() {
        WebActivity.openActivity(this, "我的github", "https://github.com/gaobaiq");
    }

    @Override
    public void onOkamiClick() {
        OkamiActivity.openActivity(this);
    }

    @Override
    public void onToolsClick() {
        ToolsListActivity.openActivity(this);
    }

    @Override
    public void onCustomClick() {
        ToastUtils.ToastMessage(this, "自定义");
    }

    @Override
    public void onOtherClick() {
        ToastUtils.ToastMessage(this, "其他");
    }

    @Override
    public void onMyInfoClick() {
        ToastUtils.ToastMessage(this, "我的信息");
    }
}
