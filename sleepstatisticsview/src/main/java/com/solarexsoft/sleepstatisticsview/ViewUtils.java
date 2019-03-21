package com.solarexsoft.sleepstatisticsview;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 10:34/2019/3/21
 *    Desc:
 * </pre>
 */

public class ViewUtils {
    public static int measure(int widthMeasureSpec, int defaultSize) {
        int specSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int specMode = View.MeasureSpec.getMode(widthMeasureSpec);

        int result = defaultSize;
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = Math.min(specSize, defaultSize);
        }

        return result;
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
