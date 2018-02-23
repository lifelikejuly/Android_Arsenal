package com.julyyu.arsenal.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.utilslibrary.util.SoftKeyBoardUtils;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.uilibrary.view.chatInput.CInputBar;
import com.julyyu.uilibrary.view.chatInput.ChatInputClickListener;
import com.julyyu.uilibrary.view.emojiMachine.EmojiAdapter;
import com.julyyu.uilibrary.view.emojiMachine.EmojiPanel;

import butterknife.BindView;

/**
 * Created by julyyu on 2017/8/4.
 */

public class ChatInputFragment extends BaseFragment implements EmojiAdapter.EmojiSelectListener {


    @BindView(R.id.tv_text)
    TextView   textView;
    @BindView(R.id.chatinput)
    CInputBar  chatinput;
    @BindView(R.id.emoji_panel)
    EmojiPanel emojiPanel;

    @Override
    protected int getLayout() {
        return R.layout.view_chatinput;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/AppleColorEmoji.ttf");
        if(typeface != null){
            textView.setTypeface(typeface);
        }
        chatinput.setListener(new ChatInputClickListener() {
            @Override
            public void clickMsg() {
//                chatinput.setText(0x1f604+"");
//                String emojiString = getEmojiStringByUnicode(0x1f604);
//                chatinput.setText(emojiString);
                textView.setText(chatinput.getText());
                chatinput.setText("");
            }

            @Override
            public void clickImage() {

            }

            @Override
            public void clickMore(boolean bool) {
                if(bool){
                    SoftKeyBoardUtils.closeKeybord(chatinput,getContext());
                }else{
                    SoftKeyBoardUtils.openKeybord(chatinput,getContext());
                }
                emojiPanel.setVisibility(bool ? View.VISIBLE : View.GONE);
            }
        });
        emojiPanel.setEmojiSelectListener(this);
    }

    private String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    @Override
    public void onClickListener(CharSequence text, int position) {
        chatinput.setText(text.toString());
    }

    @Override
    public void delectListener() {
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
        chatinput.dispatchKeyEvent(event);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
