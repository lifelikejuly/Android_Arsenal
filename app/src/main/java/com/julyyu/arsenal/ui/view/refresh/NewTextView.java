package com.julyyu.arsenal.ui.view.refresh;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by julyyu on 2017/8/1.
 */

public  class NewTextView extends ViewGroup{

    private Animation.AnimationListener mListener;

    public NewTextView(Context context) {
        this(context,null);

//        setBackgroundResource(R.mipmap.ic_launcher);
    }
    public NewTextView(Context context,AttributeSet attr){
        super(context,attr);
        TextView textView = new TextView(context);
        textView.setText("HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView);
        addView(linearLayout,0);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width  = getMeasuredWidth();
        final int height = getMeasuredHeight();
        final int  childLeft   = getPaddingLeft();
        final int  childTop    = getPaddingTop();
        final int  childWidth  = width - getPaddingLeft() - getPaddingRight();
        final int  childHeight = height - getPaddingTop() - getPaddingBottom();
        int count = this.getChildCount();
        for(int i = 0;i < count;i++){
            View child = this.getChildAt(i);
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("FUCK",50,50,paint);
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
