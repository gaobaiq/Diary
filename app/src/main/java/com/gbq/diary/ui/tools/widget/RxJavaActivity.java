package com.gbq.diary.ui.tools.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.gbq.diary.R;
import com.gbq.diary.adapter.PositionAdapter;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.beans.PositionBean;
import com.gbq.diary.ui.tools.presenter.impl.RxJavaPresenterImpl;
import com.gbq.diary.ui.tools.view.IRxJavaView;
import com.gbq.diary.widget.customview.HeadView;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.pullrefresh.PullRefreshRecyclerView;
import com.gbq.library.pullrefresh.PullRefreshUtil;
import com.gbq.library.pullrefresh.PullRefreshView;
import com.gbq.library.toast.ToastUtils;

import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * 类说明：RxJava操作符页面列表
 * Author: Kuzan
 * Date: 2017/12/25 11:28.
 */
public class RxJavaActivity extends BaseActivity<IRxJavaView, RxJavaPresenterImpl> implements IRxJavaView {
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
    protected RxJavaPresenterImpl initPresenter() {
        return new RxJavaPresenterImpl();
    }

    @Override
    protected int setStatusBarColor() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setTitle(R.string.title_rxjava2);

        initListView();
    }

    private void initListView() {
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
            Timber.e("TAG", errMsg);
        }
    }

    private void onMyItemClick(final PositionBean bean) {
        if (bean != null) {
            switch (bean.getType()) {
                case POSITION_0:
                    RxBaseActivity.openActivity(this);
                    break;
                case POSITION_1:
                    RxSchedulersActivity.openActivity(this);
                    break;
                case POSITION_2:
                    RxCreateActivity.openActivity(this);
                    break;
                case POSITION_3:
                    RxMapActivity.openActivity(this);
                    break;
                case POSITION_4:
                    RxFilterActivity.openActivity(this);
                    break;
                case POSITION_5:
                    RxMergeActivity.openActivity(this);
                    break;
                case POSITION_6:
                    RxTimerActivity.openActivity(this);
                    break;
                case POSITION_7:
                    RxOtherActivity.openActivity(this);
                    break;
                case POSITION_8:
                    RxDoActivity.openActivity(this);
                    break;
            }
        }
    }

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, RxJavaActivity.class));
    }
}
