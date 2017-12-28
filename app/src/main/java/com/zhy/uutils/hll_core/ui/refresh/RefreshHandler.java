package com.zhy.uutils.hll_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.uutils.hll_core.app.Hll;
import com.zhy.uutils.hll_core.net.RestClient;
import com.zhy.uutils.hll_core.net.callback.ISuccess;
import com.zhy.uutils.hll_core.ui.recycle.DataConverter;
import com.zhy.uutils.hll_core.ui.recycle.MultipleRecycleAdapter;

/**
 * Created by fei on 2017/8/2.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecycleAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagingBean());
    }


    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Hll.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //这里进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置adapter
                        mAdapter = MultipleRecycleAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
