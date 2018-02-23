package com.julyyu.arsenal.adapter;

import android.view.View;
import android.widget.Button;


import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;
import com.julyyu.uilibrary.adapter.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by JulyYu on 2017/4/20.
 */

public class ItemAdapter extends BaseRecyclerAdapter<String,ItemAdapter.ItemViewHolder> {


    public ItemAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.item_content;
    }

    @Override
    protected ItemViewHolder createViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, final String data, final int position) {
        super.onBindViewHolder(holder, data, position);
        holder.btn.setText(data);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemSingleListener != null){
                    itemSingleListener.onSingleClick(data,position);
                }
            }
        });
    }

    class ItemViewHolder extends BaseViewHolder {
        @BindView(R.id.btn_content)
        Button btn;
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
