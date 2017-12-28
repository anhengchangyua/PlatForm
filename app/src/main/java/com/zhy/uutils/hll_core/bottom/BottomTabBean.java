package com.zhy.uutils.hll_core.bottom;

/**
 * @author zwy.
 * @time 2017/7/29 14:55
 * @description
 */
public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}

