package com.gbq.diary.widget.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gbq.diary.R;
import com.gbq.diary.base.BaseLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

/**
 * 类说明：我的日记头部信息
 * Author: Kuzan
 * Date: 2017/5/26 11:48.
 */
public class HeadView extends BaseLinearLayout {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_remarks)
    TextView mTvRemarks;

    private SimpleDateFormat dateFormat;

    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected int getLayoutId() {
        return R.layout.layout_head;
    }

    @Override protected void initViewAndData() {
        mTvTitle.setText(mContext.getString(R.string.app_name));
        notifyDate();
    }

    public void notifyDate() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        mTvRemarks.setText(dateFormat.format(new Date()));
    }
}
