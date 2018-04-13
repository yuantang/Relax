package com.coder.relax.UI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by GreendaMi on 2017/1/17.
 */

public class CHTextView extends android.support.v7.widget.AppCompatTextView {
    public CHTextView(Context context) {
        super(context);
    }

    public CHTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "iconfont/CH.ttf");
        setTypeface(typeface);
    }


}