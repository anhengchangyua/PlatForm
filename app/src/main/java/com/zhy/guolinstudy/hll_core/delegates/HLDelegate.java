package com.zhy.guolinstudy.hll_core.delegates;

/**
 * Created by wanyummy on 2017/7/10.
 */

public abstract class HLDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends HLDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
