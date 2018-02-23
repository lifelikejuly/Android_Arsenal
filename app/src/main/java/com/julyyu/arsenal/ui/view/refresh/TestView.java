package com.julyyu.arsenal.ui.view.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.utilslibrary.util.LogUtils;


/**
 * Created by julyyu on 2017/8/3.
 */

public class TestView extends RelativeLayout {


    private Animation.AnimationListener mListener;

    private static final int[] LAYOUT_ATTRS = new int[]{
            android.R.attr.enabled
    };

    TextView textView;

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, AttributeSet attrs){
        super(context,attrs);
        textView = new TextView(context);
        textView.setText("TestViewTestViewTestViewTestViewTestViewTestView");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        addView(textView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
        addView(imageView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
    }

    //测量view
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width  = getMeasuredWidth();
        final int height = getMeasuredHeight();
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
//            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            child.layout(0,0,width,100);
            LogUtils.printI(child.toString(),"getMeasuredWidth: " + child.getMeasuredWidth() + "  getMeasuredHeight: " +child.getMeasuredHeight() + "\n"+
                    "width:" + width + "\n" +
                    "height: " + height);
        }
    }
    //布局View
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        LogUtils.printI("widthMeasureSpec: " + widthMeasureSpec + "  heightMeasureSpec: " +heightMeasureSpec );
    }

    public TextView getTextView() {
        return textView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(30);
//        canvas.drawText("FUCK",50,50,paint);
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        mListener = listener;
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
        if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
    }

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
    }


}
