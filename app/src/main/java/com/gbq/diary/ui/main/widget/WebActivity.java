package com.gbq.diary.ui.main.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;

import com.gbq.diary.R;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.base.BasePresenter;
import com.github.lzyzsd.jsbridge.BridgeWebView;

import butterknife.Bind;

/**
 * 类说明：网页有关
 * Author: Kuzan
 * Date: 2017/5/26 9:44.
 */
public class WebActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseBar mToolbar;
    @Bind(R.id.web_view)
    BridgeWebView mWebView;

    private final String TAG = "WEB";

    @Override
    protected int initLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViewAndData() {
        String title = getIntent().getStringExtra("TITLE");
        String url = getIntent().getStringExtra("URL");

        Log.e(TAG,url);

        mToolbar.setTitle(title);

        mWebView.loadUrl(url);
    }

    public static void openActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("TITLE", title);
        intent.putExtra("URL", url);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
