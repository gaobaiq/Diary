package com.gbq.diary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gbq.library.base.BasePresenter;
import com.gbq.library.rxbus.Bus;
import com.gbq.library.rxbus.BusProvider;
import com.gbq.library.widget.dialog.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 类说明：fragment基类
 * Author: Kuzan
 * Date: 2017/5/25 17:50.
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected View mView;
    protected Context mContext;
    protected boolean isInit;
    protected Bus mEventBus = BusProvider.getInstance();

    public T mPresenter;

    protected boolean isInFragment;

    private LoadingDialog loadingDialog = null;

    private IBackInterface mBackInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (mPresenter == null) {
            mPresenter = initPresenter();
        }

        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }

        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.addFragment(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isInFragment = true;

        //保护机制，判断承载此fragment是否实现了IBackInterface接口
        if(!(getActivity() instanceof IBackInterface)){
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        }else{
            mBackInterface = (IBackInterface)getActivity();
        }
        mBackInterface.setSelectedFragment(this);//将fragment传递到

        this.mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
        }
        ButterKnife.bind(this, mView);

        // 注册eventBus
        if (mEventBus == null) {
            mEventBus = BusProvider.getInstance();
        }
        try {
            mEventBus.register(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isInit) {
            initViewAndData();
            isInit = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isInFragment = false;
    }

    public void detach() {
        if (mPresenter != null) {
            mPresenter.detach();
        }

        // 如果订阅了相关事件，在onDestroy时取消订阅，防止RxJava可能会引起的内存泄漏问题
        if (mEventBus != null) {
            try {
                mEventBus.unregister(this);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            mEventBus = null;
        }
    }

    /**
     * 模版方法，通过该方法获取该fragment的view的layoutid
     */
    protected abstract int getLayoutId();

    /**
     * 实例化presenter
     *
     * @return presenter
     */
    protected abstract T initPresenter();

    /**
     * 模版方法，在activity初始化之后调用
     */
    protected abstract void initViewAndData();


    public abstract boolean onBackPressed();


    public void startLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mContext);
        }
        loadingDialog.show();
    }


    public void stopLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        loadingDialog = null;
    }
}
