package com.zhy.guolinstudy.hll_core.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by wampum's on 2017/8/7.
 */

public class MultipleItemEntity implements MultiItemEntity {

    // 1 数据
    private final ReferenceQueue<LinkedHashMap<Object, Object>> queue = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> link = new LinkedHashMap<>();
    private SoftReference<LinkedHashMap<Object, Object>> soft = new SoftReference<LinkedHashMap<Object, Object>>(link, queue);

    // 2 构造函数给builder传值
    MultipleItemEntity(LinkedHashMap<Object, Object> data) {
        soft.get().putAll(data);
    }

    // 3 创建builder
    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }

    // 4 get
    @SuppressWarnings("unchecked")
    final <T> T getField(Object key) {
        return (T) soft.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return soft.get();
    }

    //5 set
    public final MultipleItemEntity setField(Object key, Object value) {
        soft.get().put(key, value);
        return this;
    }

    @Override
    public int getItemType() {
        return (int) soft.get().get(MultipleFields.ITEM_TYPE);
    }
}
