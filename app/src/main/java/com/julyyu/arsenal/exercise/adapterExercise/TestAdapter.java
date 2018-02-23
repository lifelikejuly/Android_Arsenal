package com.julyyu.arsenal.exercise.adapterExercise;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by julyyu on 2018/2/9.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TextViewHolder>{

    List<?> mDatas;

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        layoutInflater.inflate()
        return null;
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) { //绑定数据显示视图内容

    }

    @Override
    public int getItemViewType(int position) { //获取Data对应的ViewType类型
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TextViewHolder extends RecyclerView.ViewHolder{

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }
}
