package com.gbq.diary.ui.okami.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gbq.diary.R;
import com.gbq.diary.adapter.OkGoJsonAdapter;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.diary.ui.okami.presenter.impl.OkGoPresenterJsonImpl;
import com.gbq.diary.ui.okami.view.IOkGoJsonView;
import com.gbq.diary.widget.customview.HeadView;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.pullrefresh.PullRefreshRecyclerView;
import com.gbq.library.pullrefresh.PullRefreshUtil;
import com.gbq.library.pullrefresh.PullRefreshView;
import com.gbq.library.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类说明：加载json数据
 * Author: Kuzan
 * Date: 2017/5/26 17:32.
 */
public class OkGoJsonActivity extends BaseActivity<IOkGoJsonView, OkGoPresenterJsonImpl> implements IOkGoJsonView {
    @Bind(R.id.toolbar)
    BaseBar mToolbar;
    @Bind(R.id.btn_single)
    TextView mBtnSingle;
    @Bind(R.id.btn_array)
    TextView mBtnArray;
    @Bind(R.id.list_data)
    PullRefreshRecyclerView mListData;

    private OkGoJsonAdapter mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_okgo_json;
    }

    @Override
    protected OkGoPresenterJsonImpl initPresenter() {
        return new OkGoPresenterJsonImpl();
    }

    @Override
    protected int setStatusBarColor() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setTitle(R.string.title_okgo_json);

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
        mAdapter = new OkGoJsonAdapter(this);
        mListData.setAdapter(mAdapter);


        mPresenter.loadData(this, "paramValue1");
        startLoadingDialog();
    }

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, OkGoJsonActivity.class));
    }

    @OnClick({R.id.btn_single, R.id.btn_array})
    void onBtnClick(View view) {
        startLoadingDialog();
        if (view.getId() == R.id.btn_single) {
            mPresenter.loadData(this, "paramValue1");
        } else {
            mPresenter.loadArrayList(this, "paramValue1");
        }
    }

    @Override
    public void loadDataSuccess(OkGoRequestDataBean bean) {
        stopLoadingDialog();
        if (isRunning && bean != null) {

            List<OkGoRequestDataBean> beans = new ArrayList<>();
            beans.add(bean);

            if (mAdapter != null) {
                mAdapter.replaceList(beans);
            }
        }
    }

    @Override
    public void loadArrayDataSuccess(List<OkGoRequestDataBean> beans) {
        stopLoadingDialog();
        if (isRunning && beans != null) {

            if (mAdapter != null) {
                mAdapter.replaceList(beans);
            }
        }
    }

    @Override
    public void loadErr(boolean isShow, String errMsg) {
        if (isShow) {
            ToastUtils.ToastMessage(this, errMsg);
        } else {
            Log.e("TAG", errMsg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
