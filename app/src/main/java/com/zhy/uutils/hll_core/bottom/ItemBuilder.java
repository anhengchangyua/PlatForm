package com.zhy.uutils.hll_core.bottom;

import java.util.LinkedHashMap;

/**
 * @author zwy.
 * @time 2017/7/29 15:20
 * @description
 */
public final class ItemBuilder {
    //LINKHASH  是有序的map
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    //简单工厂模式
    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {

        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
