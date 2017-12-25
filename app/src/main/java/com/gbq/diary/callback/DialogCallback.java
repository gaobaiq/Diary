package com.gbq.diary.callback;

import android.app.Activity;

import com.gbq.library.widget.dialog.LoadingDialog;

/**
 * 类说明：对于网络请求是否需要弹出进度对话框
 * Author: Kuzan
 * Date: 2017/5/26 17:58.
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {
    private LoadingDialog dialog;

    private void initDialog(Activity activity) {
        dialog = new LoadingDialog(activity);
    }

    public DialogCallback(Activity activity) {
        super();
        initDialog(activity);
    }
}
