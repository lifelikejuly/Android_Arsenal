package com.julyyu.arsenal.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;
import com.julyyu.uilibrary.adapter.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by julyyu on 2018/3/22.
 */

public class MenuAdapter extends BaseRecyclerAdapter<String,MenuAdapter.MenuViewHolder> {

    public MenuAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.item_content;
    }

    @Override
    protected MenuViewHolder createViewHolder(View view, int viewType) {
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, String data, int position) {
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

    class MenuViewHolder extends BaseViewHolder{
        @BindView(R.id.btn_content)
        TextView btn;
        public MenuViewHolder(View itemView) {
            super(itemView);
        }
    }
}
