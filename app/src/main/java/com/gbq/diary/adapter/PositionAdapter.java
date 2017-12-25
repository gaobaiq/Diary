package com.gbq.diary.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gbq.diary.R;
import com.gbq.diary.beans.PositionBean;
import com.gbq.library.adapter.recyclerview.BaseViewHolder;
import com.gbq.library.adapter.recyclerview.CommonRecyclerAdapter;

/**
 * 类说明：大神杰作列表
 * Author: Kuzan
 * Date: 2017/5/26 15:50.
 */
public class PositionAdapter extends CommonRecyclerAdapter<PositionBean> {
    public PositionAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_of_list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final PositionBean bean = mList.get(position);

        TextView tvName = holder.get(R.id.tv_name);
        TextView tvRemarks = holder.get(R.id.tv_remarks);

        tvName.setText(bean.getTitle());
        tvRemarks.setText(bean.getRemarks());
    }
}
