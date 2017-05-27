package com.gbq.diary.ui.okami.widget;

import android.content.Context;
import android.content.Intent;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.gbq.diary.R;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.ui.okami.presenter.impl.IOkGoDownloadPresenterImpl;
import com.gbq.diary.ui.okami.view.IOkGoDownloadView;
import com.gbq.diary.widget.customview.NumberProgressBar;
import com.gbq.diary.widget.toolbar.BaseBar;

import butterknife.Bind;
import butterknife.OnClick;

import static com.gbq.diary.R.id.pbProgress;
import static com.gbq.diary.R.id.tvProgress;

/**
 * 类说明：okgo文件下载
 * Author: Kuzan
 * Date: 2017/5/27 13:41.
 */
public class OkGoDownloadActivity extends BaseActivity<IOkGoDownloadView, IOkGoDownloadPresenterImpl> implements IOkGoDownloadView {
    @Bind(R.id.toolbar)
    BaseBar mToolbar;
    @Bind(R.id.fileDownload)
    Button mFileDownload;
    @Bind(R.id.downloadSize)
    TextView mDownloadSize;
    @Bind(R.id.netSpeed)
    TextView mNetSpeed;
    @Bind(tvProgress)
    TextView mTvProgress;
    @Bind(pbProgress)
    NumberProgressBar mPbProgress;

    @Override
    protected int initLayout() {
        return R.layout.activity_okgo_file_download;
    }

    @Override
    protected IOkGoDownloadPresenterImpl initPresenter() {
        return new IOkGoDownloadPresenterImpl();
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setTitle(R.string.title_okgo_download);
    }

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, OkGoDownloadActivity.class));
    }

    @OnClick(R.id.fileDownload)
    void onBtnClick() {
        mPresenter.downloadFile(this, "paramValue1", "OkGo.apk");
    }

    @Override
    public void onDownloadBefore() {
        mFileDownload.setText("正在下载中");
    }

    @Override
    public void onDownloadSuccess() {
        mFileDownload.setText("下载完成");
    }

    @Override
    public void onDownloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        Log.i("TAG", "downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);

        String downloadLength = Formatter.formatFileSize(getApplicationContext(), currentSize);
        String totalLength = Formatter.formatFileSize(getApplicationContext(), totalSize);
        mDownloadSize.setText(downloadLength + "/" + totalLength);
        String netSpeed = Formatter.formatFileSize(getApplicationContext(), networkSpeed);
        mNetSpeed.setText(netSpeed + "/S");
        mTvProgress.setText((Math.round(progress * 10000) * 1.0f / 100) + "%");
        mPbProgress.setMax(100);
        mPbProgress.setProgress((int) (progress * 100));
    }

    @Override
    public void loadErr(boolean isShow, String errMsg) {
        mFileDownload.setText("下载出错");
    }
}
