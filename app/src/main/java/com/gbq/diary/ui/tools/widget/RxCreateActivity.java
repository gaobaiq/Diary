package com.gbq.diary.ui.tools.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.gbq.diary.R;
import com.gbq.diary.adapter.PositionAdapter;
import com.gbq.diary.adapter.RxJavaResultAdapter;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.ui.tools.presenter.impl.RxCreatePresenterImpl;
import com.gbq.diary.ui.tools.view.IRxResultView;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * 类说明：Rx Create事件产生操作符
 * Author: Kuzan
 * Date: 2017/12/26 10:34.
 */
public class RxCreateActivity extends BaseActivity<IRxResultView, RxCreatePresenterImpl> implements IRxResultView {
    @Bind(R.id.toolbar)
    BaseBar mToolbar;
    @Bind(R.id.list_result)
    RecyclerView mListResult;
    @Bind(R.id.list_event)
    RecyclerView mListEvent;

    private RxJavaResultAdapter mResultAdapter;
    private PositionAdapter mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_rxjava_result;
    }

    @Override
    protected RxCreatePresenterImpl initPresenter() {
        return new RxCreatePresenterImpl();
    }

    @Override
    protected int setStatusBarColor() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setTitle(R.string.title_rxmap);
        initResultList();
        initEventList();
    }

    private void initResultList() {
        mListResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mResultAdapter = new RxJavaResultAdapter(this);
        mListResult.setAdapter(mResultAdapter);
    }

    private void initEventList() {
        mListEvent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new PositionAdapter(this);
        mListEvent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mResultAdapter.replaceList(new ArrayList<String>());
                PositionBean bean = mAdapter.getList().get(i);
                mPresenter.onPositionClick(bean);
            }
        });

        mPresenter.loadData();
    }

    @Override
    public void replaceList(List<PositionBean> beans) {
        if (isRunning && beans != null && mAdapter != null) {
            mAdapter.replaceList(beans);
        }
    }

    @Override
    public void replaceResult(List<String> list) {
        if (isRunning && list != null && mResultAdapter != null) {
            mResultAdapter.replaceList(list);
        }
    }

    @Override
    public void addResultAtEnd(@NonNull String res) {
        if (isRunning && mResultAdapter != null) {
            mResultAdapter.addListBeanAtEnd(res);
        }
    }

    @Override
    public void loadErr(boolean isShow, String errMsg) {
        if (isShow) {
            ToastUtils.ToastMessage(this, errMsg);
        } else {
            Timber.e("TAG", errMsg);
        }
    }

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, RxCreateActivity.class));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDispose();
        super.onDestroy();
    }
}
