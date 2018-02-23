package com.julyyu.uilibrary.view.chatInput;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.julyyu.uilibrary.R;


/**
 * Created by julyyu on 2017/8/4.
 */

public class WeChatInputBar extends RelativeLayout implements View.OnClickListener {

    RelativeLayout    mRlContent;
    AppCompatEditText mEditChatInput;
    AppCompatButton   mBtnSend;

    ChatInputClickListener listener;

    public WeChatInputBar(Context context) {
        this(context, null);
    }

    public WeChatInputBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        this.setBackgroundColor(0xffeaeaea);

        mRlContent = new RelativeLayout(getContext());
        LayoutParams contentParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int          dp5           = dip2px(getContext(), 5);
        int          dp10          = dip2px(getContext(), 10);
        contentParams.setMargins(dp10, dp5, dp10, dp5);
        mRlContent.setLayoutParams(contentParams);
        mRlContent.setBackgroundResource(R.drawable.shape_w_radius_5);
        addView(mRlContent);

        mBtnSend = new AppCompatButton(getContext());
        mBtnSend.setText("发送");
        mBtnSend.setId(R.id.chat_send);
        LayoutParams sendParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sendParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRlContent.addView(mBtnSend, sendParams);

        mEditChatInput = new AppCompatEditText(getContext());
        mEditChatInput.setBackgroundDrawable(null);
        mEditChatInput.setMaxLines(4);
        LayoutParams inputParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inputParams.addRule(RelativeLayout.LEFT_OF, mBtnSend.getId());
        mRlContent.addView(mEditChatInput, inputParams);

        mBtnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnSend) {
            if (listener != null) {
                listener.clickMsg();
            }
        }
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
