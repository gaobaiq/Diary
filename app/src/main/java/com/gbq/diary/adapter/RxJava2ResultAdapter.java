package com.gbq.diary.adapter;

import android.content.Context;

import com.gbq.diary.R;
import com.gbq.library.adapter.recyclerview.BaseViewHolder;
import com.gbq.library.adapter.recyclerview.CommonRecyclerAdapter;

/**
 * 类说明：
 * Author: Kuzan
 * Date: 2017/12/25 11:36.
 */
public class RxJava2ResultAdapter extends CommonRecyclerAdapter<String> {
    public RxJava2ResultAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_of_rxjava_result;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_name, mList.get(position));
    }
}
