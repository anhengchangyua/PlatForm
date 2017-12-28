package com.zhy.uutils.hll_core.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.zhy.uutils.R;

import java.util.ArrayList;

/**
 * Created by fei on 2017/8/3.
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banner,
                                  OnItemClickListener clickListener) {

        convenientBanner.setPages(new HolderCreator(), banner)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000);


    }

}
