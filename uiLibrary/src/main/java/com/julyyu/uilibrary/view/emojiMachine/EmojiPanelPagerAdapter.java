package com.julyyu.uilibrary.view.emojiMachine;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
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

public class EmojiPanelPagerAdapter extends PagerAdapter{

    Context mContext;
    EmojiAdapter.EmojiSelectListener listener;

    public EmojiPanelPagerAdapter(Context context, EmojiAdapter.EmojiSelectListener emojiSelectListener){
        mContext = context;
        listener = emojiSelectListener;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        View         view         = LayoutInflater.from(context).inflate(R.layout.view_recyclerview,null,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        List<Emoji> emojis       = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,7));
        recyclerView.setAdapter(new EmojiAdapter(context,emojis,listener));
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }


}
