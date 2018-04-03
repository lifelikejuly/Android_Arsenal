package com.julyyu.arsenal.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.model.PageItem;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;
import com.julyyu.uilibrary.adapter.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by haocanyu on 03/04/2018.
 */

public class PageItemAdapter extends BaseRecyclerAdapter<PageItem,PageItemAdapter.ItemViewHolder>{

    public PageItemAdapter(List<PageItem> datas) {
        super(datas);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.item_content;
    }

    @Override
    protected PageItemAdapter.ItemViewHolder createViewHolder(View view, int viewType) {
        return new PageItemAdapter.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PageItemAdapter.ItemViewHolder holder, final PageItem data, final int position) {
        super.onBindViewHolder(holder, data, position);
        holder.btn.setText(data.name);
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
        TextView btn;
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
