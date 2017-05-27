package com.gbq.diary.ui.okami.widget;

import android.content.Context;
import android.content.Intent;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gbq.diary.R;
import com.gbq.diary.base.BaseActivity;
import com.gbq.diary.ui.okami.presenter.impl.IOkGoUploadPresenterImpl;
import com.gbq.diary.ui.okami.view.IOkGoUploadView;
import com.gbq.diary.utils.GlideImageLoader;
import com.gbq.diary.widget.customview.NumberProgressBar;
import com.gbq.diary.widget.toolbar.BaseBar;
import com.gbq.library.utils.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.gbq.diary.R.id.pbProgress;
import static com.gbq.diary.R.id.tvProgress;

/**
 * 类说明：文件上传
 * Author: Kuzan
 * Date: 2017/5/27 15:02.
 */
public class OkGoUploadActivity extends BaseActivity<IOkGoUploadView, IOkGoUploadPresenterImpl> implements IOkGoUploadView {

    @Bind(R.id.toolbar)
    BaseBar mToolbar;
    @Bind(R.id.selectImage)
    Button mSelectImage;
    @Bind(R.id.formUpload)
    Button mFormUpload;
    @Bind(R.id.downloadSize)
    TextView mDownloadSize;
    @Bind(R.id.netSpeed)
    TextView mNetSpeed;
    @Bind(tvProgress)
    TextView mTvProgress;
    @Bind(pbProgress)
    NumberProgressBar mPbProgress;
    @Bind(R.id.images)
    TextView mImages;

    private List<ImageItem> imageItems;

    @Override
    protected int initLayout() {
        return R.layout.activity_okgo_form_upload;
    }

    @Override
    protected IOkGoUploadPresenterImpl initPresenter() {
        return new IOkGoUploadPresenterImpl();
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setTitle(R.string.title_okgo_upload);
    }

    public static void openActivity(Context context) {
        context.startActivity(new Intent(context, OkGoUploadActivity.class));
    }

    @OnClick({R.id.selectImage, R.id.formUpload})
    void onBtnClick(View view) {
        if (view.getId() == R.id.selectImage) {
            ImagePicker imagePicker = ImagePicker.getInstance();
            imagePicker.setImageLoader(new GlideImageLoader());
            imagePicker.setMultiMode(true);   //多选
            imagePicker.setShowCamera(true);  //显示拍照按钮
            imagePicker.setSelectLimit(9);    //最多选择9张
            imagePicker.setCrop(false);       //不进行裁剪
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, 100);
        } else {
            List<File> files = new ArrayList<>();
            if (imageItems != null && imageItems.size() > 0) {
                for (int i = 0; i < imageItems.size(); i++) {
                    files.add(new File(imageItems.get(i).path));
                }
                mPresenter.uploadFile(this, "paramValue1", files);
            } else {
                ToastUtils.ToastMessage(this, "没有选择图片");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageItems != null && imageItems.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < imageItems.size(); i++) {
                        if (i == imageItems.size() - 1) sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).path);
                        else sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).path).append("\n");
                    }
                    mImages.setText(sb.toString());
                } else {
                    mImages.setText("--");
                }
            } else {
                ToastUtils.ToastMessage(this, "没有选择图片");
                mImages.setText("--");
            }
        }
    }

    @Override
    public void onUploadBefore() {
        mFormUpload.setText("正在上传中...");
    }

    @Override
    public void onUploadSuccess() {
        mFormUpload.setText("上传完成");
    }

    @Override
    public void onUploadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        Log.e("TAG", "upProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);

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
        mFormUpload.setText("上传出错");
    }
}
