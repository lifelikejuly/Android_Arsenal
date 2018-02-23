package com.julyyu.arsenal.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.uilibrary.view.emojiMachine.Emoji;
import com.julyyu.uilibrary.view.emojiMachine.EmojiAdapter;
import com.julyyu.uilibrary.view.emojiMachine.EmojiPanel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by julyyu on 2017/8/9.
 */

public class EmojiPanelFragment extends BaseFragment {
    @BindView(R.id.tv_text)
    TextView     tvText;
    @BindView(R.id.emoji_panel)
    EmojiPanel   emojiPanel;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected int getLayout() {
        return R.layout.view_emojipanel;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Emoji>  emojis       = new ArrayList<>();
        recycler.setLayoutManager(new GridLayoutManager(getContext(),7));
        recycler.setAdapter(new EmojiAdapter(getContext(),emojis));

    }

}
