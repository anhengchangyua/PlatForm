package com.zhy.uutils.tablayout_page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.uutils.R;
import com.zhy.uutils.hll_core.delegates.HLDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wanyummy on 2017/8/21.
 */

public class PageMainDelegate extends HLDelegate {
    @BindView(R.id.sliding_tabs)
    TabLayout slidingTabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

    private PageDelegateAdapter pagerAdapter;

    @Override
    public Object setLayout() {
        return R.layout.page_main_delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        pagerAdapter = new PageDelegateAdapter(getFragmentManager(), getContext());
        viewpager.setAdapter(pagerAdapter);
        slidingTabs.setupWithViewPager(viewpager);
        slidingTabs.setTabMode(TabLayout.MODE_FIXED);
        // 添加自定义的view 第二步...
        for (int i = 0; i < slidingTabs.getTabCount(); i++) {
            TabLayout.Tab tab = slidingTabs.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
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

    //当屏幕旋转或者配置改变的时候，需要保存当前的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", slidingTabs.getSelectedTabPosition());
    }

    //Activity中 可在onRestoreInstanceState()方法恢复数据,也可以在onCreate()中恢复
    //Fragment中 数据恢复是调用的方法
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            viewpager.setCurrentItem(savedInstanceState.getInt("position"));
        }
    }
}
