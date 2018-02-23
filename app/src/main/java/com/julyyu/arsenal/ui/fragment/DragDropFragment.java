package com.julyyu.arsenal.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;


import com.julyyu.arsenal.R;
import com.julyyu.utilslibrary.util.LogUtils;
import com.julyyu.uilibrary.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by julyyu on 2017/7/25.
 */

public class DragDropFragment extends BaseFragment {

    @BindView(R.id.iv_a)
    ImageView      ivA;
    @BindView(R.id.layout_view)
    RelativeLayout rlView;
    Scroller scroller;

    int dx = 0, dy = 0;
    private static final int   MAX_Y_OVERSCROLL_DISTANCE = 200;
    private static final float SCROLL_RATIO              = 0.5f;// 阻尼系数
    private int mMaxYOverscrollDistance;

    @Override
    protected int getLayout() {
        return R.layout.view_dragdrop;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Interpolator interpolator = new Interpolator() {
//            @Override
//            public float getInterpolation(float input) {
//                return input;
//            }
//        };
        final DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        final float          density = metrics.density;

        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);


        Interpolator interpolator1 = new BounceInterpolator();
        scroller = new Scroller(getContext(), interpolator1);
        /**
         * 设置rlView和主视图一样大小 通过监听OnTouch事件获取触摸屏幕时的点击,移动,抬起事件
         * scrollto方法移动rlView整体视图。rlView容器内的子视图发生位移，而rlView依然是撑满整个主视图
         */
        rlView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        rlView.clearAnimation();
                        dx = (int) event.getX();
                        dy = (int) event.getY();
                        LogUtils.printI("drag down", dx + "--" + dy);
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.printI("drag up", "getScrollY " + rlView.getScrollY() + "\n" +
                                "getY " + rlView.getY() + "\n" +
                                "getX " + rlView.getX() + "\n" +
                                "getPivotY " + rlView.getPivotY() + "\n" +
                                "getRotationY " + rlView.getRotationY() + "\n" +
                                "getScaleY " + rlView.getScaleY() + "\n" +
                                "getTranslationY " + rlView.getTranslationY() + "\n"
                        );
//                        TranslateAnimation anim = new TranslateAnimation(0, 0,
//                                0, rlView.getScrollY());
//                        anim.setFillAfter(true);
//                        anim.setDuration(1000);
//                        rlView.startAnimation(anim);

                        rlView.scrollBy(0, -rlView.getScrollY());
//                        scroller.startScroll(0, rlView.getScrollY(), 0, 0, 1000);
//                        rlView.invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:


                        int mx = (int) event.getX();
                        int my = (int) event.getY();


                        if (dy - my < 0) {
//                            int newDeltaY = dy - my;
//                            int delta = (int) (my * SCROLL_RATIO);
//                            if (delta != 0) newDeltaY = delta;
//                            rlView.scrollTo(0, -newDeltaY);
                            rlView.scrollTo(0, dy - my);
                            LogUtils.printI("drag move", mx + "--" + my + "---" + dy);
                        }
                        break;
//                    default:
//                        return super.onTouc
                }

                return true;
            }
        });

    }


}










