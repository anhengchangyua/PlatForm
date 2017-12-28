package com.zhy.uutils.hll_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.zhy.uutils.R;
import com.zhy.uutils.hll_core.delegates.HLDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by wanyummy on 2017/7/10.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract HLDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 垃圾回收
        System.gc();
        System.runFinalization();
    }
}
