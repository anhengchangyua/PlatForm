package com.zhy.guolinstudy.hll_core.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zwy.
 * @time 2017/7/29 13:47
 * @description
 */
public abstract class BaseBottomDelegate extends HLDelegate {

    private final ArrayList<BottomItemDelegate> ITEM_DELEGATE = new ArrayList<>();
    private final ArrayList<BottomTabBean> ITEM_BEAN = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    //设置 位置 以及颜色
    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

//        for (Map.Entry<BottomTabBean,BottomItemDelegate>item :ITEMS)
    }
}
