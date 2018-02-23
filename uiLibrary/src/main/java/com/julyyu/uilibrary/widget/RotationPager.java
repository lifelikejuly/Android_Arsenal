package com.julyyu.uilibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.julyyu.uilibrary.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by julyyu on 2017/6/5.
 * 轮播ViewPager
 */

public class RotationPager extends RelativeLayout implements ViewPager.OnPageChangeListener{

    private Context      mContext;
    private ViewPager    mViewPager;
    private LinearLayout mPointLayout;
    private Drawable     selectDrawable;
    private Drawable     unSelectDrawable;
    private List<ImageView> mImgs = new ArrayList<>();
    private long            delay = 3000;
    private long  mRecentTouchTime;
    private Timer timer;
    private TimeTaskHandler timeTaskHandler = new TimeTaskHandler(this);
    public RotationPager(Context context) {
        this(context,null);
        initView(context);
    }
    public RotationPager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        initView(context);
    }
    public RotationPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.rotationPager);
        delay = a.getInt(R.styleable.rotationPager_Pager_delay,3000);
        selectDrawable = a.getDrawable(R.styleable.rotationPager_selectdrawable);
        unSelectDrawable = a.getDrawable(R.styleable.rotationPager_unselectdrawable);
        initView(context);
    }
    private void initView(Context context) {
        mContext = context;
        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //设置ViewPager
        mViewPager = new ViewPager(getContext());
        LinearLayout.LayoutParams pageParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(pageParam);
        mViewPager.addOnPageChangeListener(this);
        this.addView(mViewPager);
        //设置导航点
        mPointLayout = new LinearLayout(getContext());
        mPointLayout.setGravity(CENTER_IN_PARENT);
        LayoutParams pointParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(context,15));
        pointParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pointParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mPointLayout.setLayoutParams(pointParam);
        mPointLayout.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(mPointLayout);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        int pageNum = 0;
        for(ImageView imageView : mImgs){
            if(pageNum == position){
                imageView.setImageDrawable(selectDrawable);
            }else{
                imageView.setImageDrawable(unSelectDrawable);
            }
            pageNum ++;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    private static int dip2px(Context context,int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * 定时轮播
     */
    private static class WeakTimerTask extends TimerTask {
        private WeakReference<RotationPager> mRollPagerViewWeakReference;
        public WeakTimerTask(RotationPager mRollPagerView) {
            this.mRollPagerViewWeakReference = new WeakReference<>(mRollPagerView);
        }
        @Override
        public void run() {
            RotationPager rollPagerView = mRollPagerViewWeakReference.get();
            if (rollPagerView!=null){
                if(rollPagerView.isShown() && System.currentTimeMillis() - rollPagerView.mRecentTouchTime > rollPagerView.delay){
                    rollPagerView.timeTaskHandler.sendEmptyMessage(0);
                }
            }else{
                cancel();
            }
        }
    }

    /**
     * 轮播消息Handler
     */
    private final static class TimeTaskHandler extends Handler {
        private WeakReference<RotationPager> mRollPagerViewWeakReference;
        public TimeTaskHandler(RotationPager rollPagerView) {
            this.mRollPagerViewWeakReference = new WeakReference<>(rollPagerView);
        }
        @Override
        public void handleMessage(Message msg) {
            RotationPager rollPagerView = mRollPagerViewWeakReference.get();
            int cur = rollPagerView.mViewPager.getCurrentItem() + 1;
            if(cur >= rollPagerView.mViewPager.getAdapter().getCount()){
                cur = 0;
            }
            rollPagerView.mViewPager.setCurrentItem(cur);
//            rollPagerView.mHintViewDelegate.setCurrentPosition(cur, (HintView) rollPagerView.mHintView);
//            if (rollPagerView.mAdapter.getCount()<=1)rollPagerView.stopPlay();
        }
    }

    /**
     * 设置ViewPager内容
     * @param adapter
     */
    public void setAdapter(PagerAdapter adapter){
        if(mViewPager != null){
            mViewPager.setAdapter(adapter);
        }
        mImgs.clear();
        mPointLayout.removeAllViews();
        if(mViewPager.getAdapter().getCount() <= 1) return;
        int pading =  dip2px(mContext,5);
        for(int i = 0 ;i < adapter.getCount();i++){
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imgParams.gravity = Gravity.CENTER;
            imgParams.leftMargin = pading/2;
            imgParams.rightMargin = pading/2;
            imageView.setLayoutParams(imgParams);
            if(i == 0){
                imageView.setImageDrawable(selectDrawable);
            }else{
                imageView.setImageDrawable(unSelectDrawable);
            }
            mImgs.add(imageView);
            mPointLayout.addView(imageView);
        }
    }
    /**
     * 重置轮播导航点
     */
    public void resetPointLayout(){
        if(mViewPager.getAdapter() == null) return;
        int pading =  dip2px(mContext,5);
        mImgs.clear();
        mPointLayout.removeAllViews();
        if(mViewPager.getAdapter().getCount() <= 1) return;
        for(int i = 0 ;i < mViewPager.getAdapter().getCount();i++){
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imgParams.gravity = Gravity.CENTER;
            imgParams.leftMargin = pading / 2;
            imgParams.rightMargin = pading / 2;
            imageView.setLayoutParams(imgParams);
            if(i == 0){
                imageView.setImageDrawable(selectDrawable);
            }else{
                imageView.setImageDrawable(unSelectDrawable);
            }
            mImgs.add(imageView);
            mPointLayout.addView(imageView);
        }
    }
    /**
     * 设置轮播时间
     * @param delay
     */
    public void setDelay(int delay){
        this.delay = delay;
    }
    public void startPlay(){
        if(delay <= 0 || mViewPager.getAdapter() == null || mViewPager.getAdapter().getCount() <= 1){
            return;
        }
        if (timer != null){
            timer.cancel();
        }
        timer = new Timer();
        //用一个timer定时设置当前项为下一项
        timer.schedule(new WeakTimerTask(this), delay, delay);
    }
    public void stopPlay(){
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mRecentTouchTime = System.currentTimeMillis();
        return super.dispatchTouchEvent(ev);
    }
}
