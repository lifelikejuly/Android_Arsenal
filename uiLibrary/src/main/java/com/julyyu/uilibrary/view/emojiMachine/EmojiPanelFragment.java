package com.julyyu.uilibrary.view.emojiMachine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.julyyu.uilibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julyyu on 2017/8/9.
 */

public class EmojiPanelFragment extends Fragment {


    View mRootView;
    RecyclerView recyclerView;
    EmojiAdapter emojiAdapter;


    public static EmojiPanelFragment newInstance(){
        EmojiPanelFragment emojiPanelFragment = new EmojiPanelFragment();
        return emojiPanelFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.view_recyclerview,container,false);
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Emoji> emojis = new ArrayList<>();
        emojis.add(new Emoji());
        emojis.add(new Emoji());
        emojis.add(new Emoji());
        emojis.add(new Emoji());
        emojis.add(new Emoji());
        emojis.add(new Emoji());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),5, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(emojiAdapter = new EmojiAdapter(getContext(),emojis));
    }
}
