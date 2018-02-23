package com.julyyu.uilibrary.view.emojiMachine;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.julyyu.uilibrary.widget.RotationPager;

/**
 * Created by julyyu on 2017/8/9.
 */

public class EmojiPanel extends RelativeLayout {

    RotationPager rotationPager;
    EmojiPanelPagerAdapter emojiPanelPagerAdapter;

    public EmojiPanel(Context context) {
        this(context,null);
    }

    public EmojiPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        rotationPager = new RotationPager(getContext());

        addView(rotationPager);
    }

    public void setEmojiSelectListener(EmojiAdapter.EmojiSelectListener listener){
        rotationPager.setAdapter(emojiPanelPagerAdapter = new EmojiPanelPagerAdapter(getContext(),listener));
    }
}
