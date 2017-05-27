package com.gbq.diary.ui.okami.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.gbq.diary.R;
import com.gbq.diary.adapter.PositionAdapter;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.ui.okami.presenter.impl.IOkamiPresenterImpl;
import com.gbq.diary.ui.okami.view.IOkamiView;
import com.gbq.diary.widget.customview.HeadView;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.pullrefresh.PullRefreshRecyclerView;
import com.gbq.library.pullrefresh.PullRefreshUtil;
import com.gbq.library.pullrefresh.PullRefreshView;
import com.gbq.library.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 类说明：大神杰作
 * Author: Kuzan
 * Date: 2017/5/26 11:37.
 */
public class OkamiActivity extends BaseActivity<IOkamiView, IOkamiPresenterImpl> implements IOkamiView {


    @Bind(R.id.toolbar)
    BaseBar mToolbar;
    @Bind(R.id.list_data)
    PullRefreshRecyclerView mListData;

    private PositionAdapter mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    protected IOkamiPresenterImpl initPresenter() {
        return new IOkamiPresenterImpl();
    }

    @Override
    protected boolean translucentStatusBar() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setTitle(R.string.title_okami);
        PullRefreshUtil.setRefresh(mListData, true, false);
        final HeadView headView = new HeadView(this);
        mListData.setHead(headView);
        mListData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mListData.setOnPullDownRefreshListener(new PullRefreshView.OnPullDownRefreshListener() {
            @Override
            public void onRefresh() {
                headView.notifyDate();
                mListData.refreshFinish();
            }
        });
        mAdapter = new PositionAdapter(this);
        mListData.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PositionBean bean = mAdapter.getList().get(i);
                onMyItemClick(bean);
            }
        });

        mPresenter.loadData();
    }

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, OkamiActivity.class));
    }

    @Override
    public void replaceList(List<PositionBean> beans) {
        if (isRunning && beans != null && mAdapter != null) {
            mAdapter.replaceList(beans);
        }

        if (mListData != null) {
            mListData.refreshFinish();
        }
    }

    @Override
    public void loadErr(boolean isShow, String errMsg) {
        if (mListData != null) {
            mListData.refreshFinish();
        }
        if (isShow) {
            ToastUtils.ToastMessage(this, errMsg);
        } else {
            Log.e("TAG", errMsg);
        }
    }

    private void onMyItemClick(final PositionBean bean) {
        if (bean != null) {
            switch (bean.getType()) {
                case POSITION_0:
                    OkGoActivity.openActivity(this);
                    break;
            }
        }
    }
}
