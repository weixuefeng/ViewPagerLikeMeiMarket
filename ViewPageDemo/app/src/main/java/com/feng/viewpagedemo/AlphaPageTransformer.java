package com.feng.viewpagedemo;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class AlphaPageTransformer implements ViewPager.PageTransformer {
    private final float DEFAULT_MIN_ALPHA = 1.0f;
    private final float DEFAULT_MIN_SCALE = 0.9f;
    private final float DEFAULT_MIN_Z = 0.5f;
    private float mMinZ = DEFAULT_MIN_Z;
    private float mMinAlpah = DEFAULT_MIN_ALPHA;
    private float mMinScale = DEFAULT_MIN_SCALE;
    private static final String TAG = "AlphaPageTransformer";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setZ(mMinZ);
            page.setAlpha(mMinAlpah);
            page.setScaleY(mMinScale);
            page.setScaleX(mMinScale);
        } else if (position <= 1) {
            if (position <= 0) {
                float factor = mMinAlpah + (1 - mMinAlpah) * (1 + position);
                float factor1 = mMinScale + (1 - mMinScale) * (1 + position);
                float factor2 = mMinZ + (1 - mMinZ) * (1 + position);
                page.setAlpha(factor);
                page.setScaleX(factor1);
                page.setScaleY(factor1);
                page.setZ(factor2);
            } else {
                float factor = mMinAlpah + (1 - mMinAlpah) * (1 - position);
                float factor1 = mMinScale + (1 - mMinScale) * (1 - position);
                float factor2 = mMinZ + (1 - mMinZ) * (1 - position);
                page.setAlpha(factor);
                page.setScaleX(factor1);
                page.setScaleY(factor1);
                page.setZ(factor2);
            }
        } else {
            page.setAlpha(mMinAlpah);
            page.setScaleY(mMinScale);
            page.setScaleX(mMinScale);
            page.setZ(mMinZ);
        }
    }

}
