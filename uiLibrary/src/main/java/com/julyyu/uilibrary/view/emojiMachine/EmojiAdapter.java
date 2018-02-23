package com.julyyu.uilibrary.view.emojiMachine;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.julyyu.uilibrary.R;
import com.julyyu.uilibrary.adapter.BaseViewHolder;

import java.util.List;


/**
 * Created by julyyu on 2017/8/9.
 */

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    private List<Emoji> emojis;
    private Typeface    typeface;
    private EmojiSelectListener listener;

    public EmojiAdapter(Context context, List<Emoji> datas) {
        emojis = datas;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AppleColorEmoji.ttf");
    }

    public EmojiAdapter(Context context, List<Emoji> datas,EmojiSelectListener emojiSelectListener) {
        emojis = datas;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AppleColorEmoji.ttf");
        listener = emojiSelectListener;
    }

    @Override
    public EmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emojipanel, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EmojiViewHolder holder, final int position) {
        if(position == 20){
            ImageSpan       imgSpan    = new ImageSpan(holder.itemView.getContext(), R.drawable.ic_chat_emoji_delect);
            SpannableString spanString = new SpannableString("[delect]");
            spanString.setSpan(imgSpan, 0, "[delect]".length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            holder.tvEmoji.setText(spanString);
        }else{
            holder.tvEmoji.setTypeface(typeface);
            holder.tvEmoji.setText(getEmojiStringByUnicode(0x1f604));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    if(position == 20){
                        listener.delectListener();
                    }else{
                        listener.onClickListener(holder.tvEmoji.getText(),position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 21;
    }

    class EmojiViewHolder extends BaseViewHolder {
        TextView tvEmoji;

        public EmojiViewHolder(View itemView) {
            super(itemView);
            tvEmoji = (TextView) itemView.findViewById(R.id.tv_emoji);
        }
    }

    private String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }


    public void setListener(EmojiSelectListener listener) {
        this.listener = listener;
    }

    public interface EmojiSelectListener{
        void onClickListener(CharSequence text,int position);
        void delectListener();
    }

}
