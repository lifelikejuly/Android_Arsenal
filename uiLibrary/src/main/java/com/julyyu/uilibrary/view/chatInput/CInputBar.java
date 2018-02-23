package com.julyyu.uilibrary.view.chatInput;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.julyyu.uilibrary.R;
import com.julyyu.uilibrary.SizeUtil;


/**
 * Created by julyyu on 2017/8/4.
 */

public class CInputBar extends RelativeLayout implements View.OnClickListener, TextWatcher {

    LinearLayout       mLlContent;
    RelativeLayout     mRlFunction;
    AppCompatEditText  mEditChatInput;
    AppCompatImageView mIvEmjo;
    AppCompatImageView mIvImg;
    AppCompatImageView mIvSend;
    int                dp5;
    int                dp10;

    ChatInputClickListener listener;

    public CInputBar(Context context) {
        this(context, null);
    }

    public CInputBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

        mLlContent = new LinearLayout(getContext());
        mLlContent.setOrientation(LinearLayout.VERTICAL);
        LayoutParams contentParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dp5 = SizeUtil.dip2px(getContext(), 5);
        dp10 = SizeUtil.dip2px(getContext(), 10);
        contentParams.setMargins(dp10, dp5, dp10, dp5);
        mLlContent.setLayoutParams(contentParams);
        addView(mLlContent);


        mEditChatInput = new AppCompatEditText(getContext());
        mEditChatInput.setBackgroundDrawable(null);
        mEditChatInput.setMaxLines(4);
        mEditChatInput.addTextChangedListener(this);
        LayoutParams inputParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLlContent.addView(mEditChatInput, inputParams);

        mRlFunction = new RelativeLayout(getContext());
        mLlContent.addView(mRlFunction);

        mIvEmjo = new AppCompatImageView(getContext());
        mIvEmjo.setImageResource(R.drawable.ic_chat_emoticon);
        mIvEmjo.setId(R.id.chat_emjo);
        mIvEmjo.setSelected(true);
        LayoutParams emjoPraams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        emjoPraams.addRule(ALIGN_PARENT_LEFT);
        mRlFunction.addView(mIvEmjo, emjoPraams);

        mIvImg = new AppCompatImageView(getContext());
        mIvImg.setImageResource(R.drawable.ic_chat_image);
        LayoutParams imgPraams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgPraams.setMargins(dp5, 0, 0, 0);
        imgPraams.addRule(RIGHT_OF, mIvEmjo.getId());
        mRlFunction.addView(mIvImg, imgPraams);

        mIvSend = new AppCompatImageView(getContext());
        mIvSend.setImageResource(R.drawable.ic_chat_send);
        LayoutParams sendPraams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sendPraams.addRule(ALIGN_PARENT_RIGHT);
        mRlFunction.addView(mIvSend, sendPraams);

        mIvEmjo.setOnClickListener(this);
        mIvImg.setOnClickListener(this);
        mIvSend.setOnClickListener(this);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/AppleColorEmoji.ttf");
        if(typeface != null){
            mEditChatInput.setTypeface(typeface);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        if (v == mIvSend) {
            listener.clickMsg();
        }else if (v == mIvEmjo){
            if(mIvEmjo.isSelected()){
                mIvEmjo.setSelected(false);
                mIvEmjo.setImageResource(R.drawable.ic_chat_keyboard);
                listener.clickMore(true);
            }else{
                mIvEmjo.setSelected(true);
                mIvEmjo.setImageResource(R.drawable.ic_chat_emoticon);
                listener.clickMore(false);
            }

        }else if(v == mIvImg){
            listener.clickImage();
        }
    }

    public void setListener(ChatInputClickListener listener) {
        this.listener = listener;
    }

    public void setText(String text){
        mEditChatInput.append(text);
    }

    public void setText(SpannableString text){
        mEditChatInput.setText(text);
    }

    public void clearText(){
        mEditChatInput.setText("");
    }

    public String getText(){
        return mEditChatInput.getText().toString();
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String a = mEditChatInput.getText().toString();
        for (int i = 0; i < a.codePointCount(0, a.length()); i++) {
            int codePoint = a.codePointAt(i);
            System.out.println(Integer.toHexString(codePoint));
            Log.i("emoji",Integer.toHexString(codePoint));
        }

    }

}
