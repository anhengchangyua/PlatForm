package com.zhy.guolinstudy.guolin04_vlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.zhy.guolinstudy.R;

/**
 * Created by wanyummy on 2017/7/6.
 */

public class VlayoutActivity extends AppCompatActivity{
    public RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);

        //创建recycleview对象
        mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);
        //构建virtuallayout对象 同事内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        //将virtual绑定到recycleview中
        mRecyclerView.setLayoutManager(virtualLayoutManager);

        //设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);


        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);

    }


}
