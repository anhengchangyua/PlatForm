package com.zhy.uutils.tablayout_page;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.uutils.R;

/**
 * Created by wanyummy on 2017/8/21.
 */

public class PageDelegateAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};
    private Context context;

    public PageDelegateAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageDelegate.INSTANCE(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    //添加自定义的view 第一步...
    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        return view;
    }

    public int getPosition(int position) {
        return position;
    }
}

