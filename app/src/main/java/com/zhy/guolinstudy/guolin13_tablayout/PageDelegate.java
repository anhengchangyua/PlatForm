package com.zhy.guolinstudy.guolin13_tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wanyummy on 2017/8/21.
 */

public class PageDelegate extends HLDelegate {
    public static final String ARG_PAGE = "ARG_PAGE";
    @BindView(R.id.text_pagedelegate)
    TextView textPagedelegate;
    Unbinder unbinder;
    private int mPage;

    public static PageDelegate INSTANCE(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageDelegate pageDelegate = new PageDelegate();
        pageDelegate.setArguments(args);
        return pageDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = (int) getArguments().get(ARG_PAGE);
    }

    @Override
    public Object setLayout() {
        return R.layout.page_delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        textPagedelegate.setText("page" + mPage);
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
