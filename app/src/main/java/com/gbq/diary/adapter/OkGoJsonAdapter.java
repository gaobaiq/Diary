package com.gbq.diary.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gbq.diary.R;
import com.gbq.diary.beans.AuthorBean;
import com.gbq.diary.beans.OkGoRequestDataBean;
import com.gbq.library.adapter.recyclerview.BaseViewHolder;
import com.gbq.library.adapter.recyclerview.CommonRecyclerAdapter;

/**
 * 类说明：okgo请求json数据
 * Author: Kuzan
 * Date: 2017/5/27 11:41.
 */
public class OkGoJsonAdapter extends CommonRecyclerAdapter<OkGoRequestDataBean> {
    public OkGoJsonAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_of_okgo_json;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final OkGoRequestDataBean bean = mList.get(position);

        TextView tvTitle = holder.get(R.id.tv_title);
        TextView tvMethod = holder.get(R.id.tv_method);
        TextView tvUrl = holder.get(R.id.tv_url);

        TextView tvName = holder.get(R.id.tv_user_name);
        TextView tvFullName = holder.get(R.id.tv_user_full_name);
        TextView tvQQ = holder.get(R.id.tv_user_qq);
        TextView tvEmail = holder.get(R.id.tv_user_email);
        TextView tvGithub = holder.get(R.id.tv_user_github);
        TextView tvAddress = holder.get(R.id.tv_user_address);
        TextView tvDesc = holder.get(R.id.tv_user_desc);

        tvTitle.setText(bean.getDes());
        tvMethod.setText("请求方式：" + bean.getMethod());
        tvUrl.setText("请求链接：" + bean.getUrl());

        final AuthorBean authorBean = bean.getAuthor();
        if (authorBean != null) {
            tvName.setText("英文名称：" + authorBean.getName());
            tvFullName.setText("中文名称：" + authorBean.getFullname());
            tvQQ.setText("QQ：" + authorBean.getQq());
            tvEmail.setText("邮箱：" + authorBean.getEmail());
            tvGithub.setText("github：" + authorBean.getGithub());
            tvAddress.setText("地址：" + authorBean.getAddress());
            tvDesc.setText("简介：" + bean.getDes());
        }
    }
}
