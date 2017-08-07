package com.zhy.guolinstudy.hll_ec.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.guolin10_webview.discover.BottomItemDelegate;
import com.zhy.guolinstudy.hll_core.ui.recycle.BaseDecoration;
import com.zhy.guolinstudy.hll_core.ui.recycle.MultipleRecycleAdapter;
import com.zhy.guolinstudy.hll_core.ui.refresh.RefreshHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wanyummy on 2017/8/7.
 */

public class IndexDelegate extends BottomItemDelegate {


    @BindView(R.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.icon_index_scan)
    ImageView iconIndexScan;
    @BindView(R.id.et_search_view)
    AppCompatEditText etSearchView;
    @BindView(R.id.icon_index_message)
    ImageView iconIndexMessage;
    @BindView(R.id.tb_index)
    Toolbar tbIndex;
    Unbinder unbinder;
    private MultipleRecycleAdapter mAdapter = null;


    private RefreshHandler mRefreshHandler = null;

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.gray), 5));

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index_data");
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}