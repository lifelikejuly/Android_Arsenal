package com.julyyu.arsenal.ui.view.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by julyyu on 2017/8/2.
 */

public class ViewGropTest extends ViewGroup{

    private static final int[] LAYOUT_ATTRS = new int[]{
            android.R.attr.enabled
    };

    public ViewGropTest(Context context) {
        this(context,null);

    }
    public ViewGropTest(Context context, AttributeSet attrs) {
        super(context,attrs);
        final TypedArray a = context.obtainStyledAttributes(attrs, LAYOUT_ATTRS);
        setEnabled(a.getBoolean(0, true));
        a.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width  = getMeasuredWidth();
        final int height = getMeasuredHeight();
        final int  childLeft   = getPaddingLeft();
        final int  childTop    = getPaddingTop();
        final int  childWidth  = width - getPaddingLeft() - getPaddingRight();
        final int  childHeight = height - getPaddingTop() - getPaddingBottom();
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
//            child.measure(View.MeasureSpec.makeMeasureSpec(
//                    getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
//                    View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
//                    getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), View.MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
//            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int cw = child.getMeasuredWidth();
            // int ch = child.getMeasuredHeight();
            child.measure(MeasureSpec.makeMeasureSpec(
            getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
            MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
            getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));
        }
    }
}
