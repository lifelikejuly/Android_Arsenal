package com.julyyu.uilibrary.view.defaultPage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.julyyu.uilibrary.R;


/**
 * Created by JulyYu on 2017/4/18.
 * 缺省页
 */

public class DefaultPageView extends FrameLayout implements View.OnClickListener{

    ImageView ivDefault;
    TextView tvBigTitle;
    TextView tvSmallTitle;
    Button btnAction;
    DefalutActionListener mDefalutActionListener;
    BadActionListener mBadActionListener;
    ErrorActionListener mErrorActionListener;

    DefaultParameter mDefault;
    DefaultParameter mDefault2;
    DefaultParameter mErrorParameter;
    DefaultParameter mBadParameter;
    PageStatus pageStatus = PageStatus.defaultPage;

    public DefaultPageView(Context context) {
        super(context);
        initView();
    }

    public DefaultPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttributeSet(attrs);
    }

    public DefaultPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttributeSet(attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_default_page,null);
        ivDefault = (ImageView) view.findViewById(R.id.iv_default);
        tvBigTitle = (TextView) view.findViewById(R.id.tv_big_title);
        tvSmallTitle = (TextView) view.findViewById(R.id.tv_small_title);
        btnAction = (Button) view.findViewById(R.id.btn_default_action);
        btnAction.setOnClickListener(this);
        addView(view);
        setBadParameter(R.drawable.a,"当前网络不给力","","立即刷新");//立即刷新
        setErrorParameter(R.drawable.m,"哎呀，出错了啦！","","返回首页");//返回首页
    }

    private void initAttributeSet(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.defaultpage);
        int icon = a.getResourceId(R.styleable.defaultpage_page_icon,0);
        String bigtitle = a.getString(R.styleable.defaultpage_page_bigtitle);
        String smalltitle = a.getString(R.styleable.defaultpage_page_smalltitle);
        String actiontitle = a.getString(R.styleable.defaultpage_page_action);
        int actionColor = a.getColor(R.styleable.defaultpage_page_action_textcolor, Color.BLACK);
        int actionShape = a.getResourceId(R.styleable.defaultpage_page_action_shape,R.drawable.shape_black_stroke_radius_2);
        if(icon != 0){
            ivDefault.setImageResource(icon);
        }
        if(!TextUtils.isEmpty(bigtitle)){
            tvBigTitle.setText(bigtitle);
        }
        if(!TextUtils.isEmpty(smalltitle)){
            tvSmallTitle.setText(smalltitle);
        }
        if(!TextUtils.isEmpty(actiontitle)){
            btnAction.setText(actiontitle);
        }else{
            btnAction.setVisibility(INVISIBLE);
        }
        btnAction.setTextColor(actionColor);
        btnAction.setBackgroundResource(actionShape);
        DefaultParameter.Builder builder = new DefaultParameter.Builder();
        mDefault = builder.DefaultIcon(icon)
                .BigTitle(bigtitle)
                .SmallTitle(smalltitle)
                .ActionTitle(actiontitle)
                .build();
    }

    @Override
    public void onClick(View v) {
        if (v == btnAction && btnAction.getVisibility() == VISIBLE){
            switch (pageStatus){
                case errorPage:
                    if(mErrorActionListener != null){
                        mErrorActionListener.ActionOnClick();
                    }
                    break;
                case badPage:
                    if(mBadActionListener != null){
                        mBadActionListener.ActionOnClick();
                    }
                    break;
                case defaultPage2:
                case defaultPage:
                    if(mDefalutActionListener != null){
                        mDefalutActionListener.ActionOnClick();
                    }
                    break;
            }
        }
    }
    private void setParameter(DefaultParameter defaultParameter){
        if(defaultParameter.mDefaultIcon != 0){
            ivDefault.setImageResource(defaultParameter.mDefaultIcon);
        }
        if(!TextUtils.isEmpty(defaultParameter.mBigTitle)){
            tvBigTitle.setText(defaultParameter.mBigTitle);
        }
        if(!TextUtils.isEmpty(defaultParameter.mSmallTitle)){
            tvSmallTitle.setText(defaultParameter.mSmallTitle);
        }
        if(!TextUtils.isEmpty(defaultParameter.mActionTitle)){
            btnAction.setText(defaultParameter.mActionTitle);
            btnAction.setVisibility(VISIBLE);
        }else{
            btnAction.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置默认行动监听
     * @param mDefalutActionListener
     */
    public void setActionListener(DefalutActionListener mDefalutActionListener) {
        this.mDefalutActionListener = mDefalutActionListener;
    }

    /**
     * 设置失败行动监听
     * @param mBadActionListener
     */
    public void setBadActionListener(BadActionListener mBadActionListener) {
        this.mBadActionListener = mBadActionListener;
    }

    /**
     * 设置错误行动监听
     * @param mErrorActionListener
     */
    public void setErrorActionListener(ErrorActionListener mErrorActionListener) {
        this.mErrorActionListener = mErrorActionListener;
    }

    /**
     * 配置错误参数
     * @param mErrorParameter
     */
    public void setErrorParameter(DefaultParameter mErrorParameter) {
        this.mErrorParameter = mErrorParameter;
    }

    /**
     * 配置失败参数
     * @param mBadParameter
     */
    public void setBadParameter(DefaultParameter mBadParameter) {
        this.mBadParameter = mBadParameter;
    }

    /**
     * 设置默认参数
     * @param icon
     * @param bigtitle
     * @param smalltitle
     * @param actiontitle
     */
    public void setDefaultParameter(@Nullable @DrawableRes int icon, String bigtitle, String smalltitle, String actiontitle){
        if(icon != 0){
            ivDefault.setImageResource(icon);
        }
        if(!TextUtils.isEmpty(bigtitle)){
            tvBigTitle.setText(bigtitle);
        }
        if(!TextUtils.isEmpty(smalltitle)){
            tvSmallTitle.setText(smalltitle);
        }
        if(!TextUtils.isEmpty(actiontitle)){
            btnAction.setText(actiontitle);
            btnAction.setVisibility(VISIBLE);
        }else{
            btnAction.setVisibility(INVISIBLE);
        }
        DefaultParameter.Builder builder = new DefaultParameter.Builder();
        mDefault = builder.DefaultIcon(icon)
                .BigTitle(bigtitle)
                .SmallTitle(smalltitle)
                .ActionTitle(actiontitle)
                .build();
    }

    /**
     * 配置默认参数2
     * @param icon
     * @param bigtitle
     * @param smalltitle
     * @param actiontitle
     */
    public void setDefault2Parameter(@Nullable @DrawableRes int icon, String bigtitle, String smalltitle, String actiontitle){
        DefaultParameter.Builder builder = new DefaultParameter.Builder();
        mDefault2 = builder.DefaultIcon(icon)
                .BigTitle(bigtitle)
                .SmallTitle(smalltitle)
                .ActionTitle(actiontitle)
                .build();
    }

    /**
     * 设置默认参数
     * @param icon
     * @param bigtitle
     * @param smalltitle
     * @param actiontitle
     * @param style
     */
    public void setDefaultParameter(@Nullable @DrawableRes int icon, String bigtitle, String smalltitle, String actiontitle, int style){
        if(icon != 0){
            ivDefault.setImageResource(icon);
        }
        if(!TextUtils.isEmpty(bigtitle)){
            tvBigTitle.setText(bigtitle);
        }
        if(!TextUtils.isEmpty(smalltitle)){
            tvSmallTitle.setText(smalltitle);
        }
        if(!TextUtils.isEmpty(actiontitle)){
            btnAction.setText(actiontitle);
            btnAction.setVisibility(VISIBLE);
        }else{
            btnAction.setVisibility(INVISIBLE);
        }
        DefaultParameter.Builder builder = new DefaultParameter.Builder();
        mDefault = builder.DefaultIcon(icon)
                          .BigTitle(bigtitle)
                          .SmallTitle(smalltitle)
                          .ActionTitle(actiontitle)
                          .build();
    }

    /**
     * 配置失败参数
     * @param icon
     * @param bigtitle
     * @param smalltitle
     * @param actiontitle
     */
    public void setBadParameter(@Nullable @DrawableRes int icon, String bigtitle, String smalltitle, String actiontitle){
        DefaultParameter.Builder builder = new DefaultParameter.Builder();
        mBadParameter = builder.DefaultIcon(icon)
                .BigTitle(bigtitle)
                .SmallTitle(smalltitle)
                .ActionTitle(actiontitle)
                .build();
    }

    /**
     * 配置失败参数
     * @param builder
     */
    public void setBadParameter(@Nullable DefaultParameter.Builder builder){
        mBadParameter = builder.build();
    }

    /**
     * 配置错误参数
     * @param icon
     * @param bigtitle
     * @param smalltitle
     * @param actiontitle
     */
    public void setErrorParameter(@Nullable @DrawableRes int icon, String bigtitle, String smalltitle, String actiontitle){
        DefaultParameter.Builder builder = new DefaultParameter.Builder();
        mErrorParameter = builder.DefaultIcon(icon)
                .BigTitle(bigtitle)
                .SmallTitle(smalltitle)
                .ActionTitle(actiontitle)
                .build();
    }

    /**
     * 配置错误参数
     * @param builder
     */
    public void setErrorParameter(@Nullable DefaultParameter.Builder builder){
        mErrorParameter = builder.build();
    }
    /**
     * 隐藏
     */
    public void gone(){
        this.setVisibility(GONE);
    }

    /**
     * 显示
     */
    public void visible(){
        this.setVisibility(VISIBLE);
    }
    /**
     * 隐藏
     */
    public void invisible(){
        this.setVisibility(INVISIBLE);
    }

    /**
     * 显示默认缺省页
     */
    public void showDefaultPage(){
        if(pageStatus == PageStatus.defaultPage){
            visible();
        }else{
            pageStatus = PageStatus.defaultPage;
            if(mDefault != null){
                setParameter(mDefault);
                visible();
            }
        }
    }
    /**
     * 显示默认缺省页2
     */
    public void showDefault2Page(){
        if(pageStatus == PageStatus.defaultPage2){
            visible();
        }else{
            pageStatus = PageStatus.defaultPage2;
            if(mDefault2 != null){
                setParameter(mDefault2);
                visible();
            }
        }
    }
    /**
     * 显示错误页
     */
    public void showErrorPage(){
        if(pageStatus == PageStatus.errorPage){
            visible();
        }else{
            pageStatus = PageStatus.errorPage;
            if(mErrorParameter != null){
                setParameter(mErrorParameter);
                visible();
            }
        }
    }
    /**
     * 显示失败页
     */
    public void showBadPage(){
        if(pageStatus == PageStatus.badPage){
            visible();
        }else{
            pageStatus = PageStatus.badPage;
            if(mBadParameter != null){
                setParameter(mBadParameter);
                visible();
            }
        }
    }

}
