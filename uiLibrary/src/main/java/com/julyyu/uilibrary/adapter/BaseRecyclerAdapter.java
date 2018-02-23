package com.julyyu.uilibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JulyYu on 2016/11/2.
 */
public abstract class BaseRecyclerAdapter<T,VH extends BaseViewHolder> extends RecyclerView.Adapter<VH>{

    protected List<T>               mDatas;
    protected ItemSingleListener<T> itemSingleListener;
    protected ItemLongListener<T>   itemLongListener;



    public BaseRecyclerAdapter(List<T> datas){
        if(datas == null){
            mDatas = new ArrayList<>();
        }else{
            mDatas =  datas;
        }
    }
    public BaseRecyclerAdapter(List<T> datas, ItemSingleListener<T> listener){
        if(datas == null){
            mDatas = new ArrayList<>();
        }else{
            mDatas =  datas;
        }
        this.itemSingleListener = listener;
    }
    public BaseRecyclerAdapter(List<T> datas, ItemLongListener<T> listener){
        if(datas == null){
            mDatas = new ArrayList<>();
        }else{
            mDatas =  datas;
        }
        this.itemLongListener = listener;
    }
    public BaseRecyclerAdapter(List<T> datas, ItemSingleListener<T> listener, ItemLongListener<T> longListener){
        if(datas == null){
            mDatas = new ArrayList<>();
        }else{
            mDatas =  datas;
        }
        this.itemSingleListener = listener;
        this.itemLongListener = longListener;
    }
    public void setDatas(List<T> datas) {
        if(datas != null && datas.size() > 0){
            this.mDatas = datas;
            notifyDataSetChanged();
        }
    }
    public void resetDatas(List<T> datas){
        if(datas != null && datas.size() > 0){
            this.mDatas = datas;
            notifyDataSetChanged();
        }else{
            this.mDatas = new ArrayList<>();
            notifyDataSetChanged();
        }
    }
    public void addDatas(List<T> datas) {
        if(datas != null && datas.size() > 0){
            this.mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addData(T data) {
        if(data != null){
            this.mDatas.add(data);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position){
        if(this.mDatas != null && this.mDatas.size() > 0 && position <= this.mDatas.size() - 1){
            this.mDatas.remove(position);
        }
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getViewLayout(),parent,false);
        return createViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(listener != null){
//                    listener.onClick(mDatas.get(position));
//                }
//            }
//        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if(longListener != null){
//                    longListener.onLongClick(mDatas.get(position));
//                }
//                return true;
//            }
//        });
        final T data = mDatas.get(position);
        onBindViewHolder(holder,data,position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
    protected abstract int getViewLayout();
    protected abstract VH createViewHolder(View view, int viewType);



    public interface ItemSingleListener<T> {
        void onSingleClick(T clickedTask, int position);
    }
    public interface ItemLongListener<T> {
        void onLongClick(T clickedTask, int position);
    }

    public void onBindViewHolder(VH holder, T data,int position){

    }

    public void setItemSingleListener(ItemSingleListener<T> itemSingleListener) {
        this.itemSingleListener = itemSingleListener;
    }

    public void setItemLongListener(ItemLongListener<T> itemLongListener) {
        this.itemLongListener = itemLongListener;
    }
}
