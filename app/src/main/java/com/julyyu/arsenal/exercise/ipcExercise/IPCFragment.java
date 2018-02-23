package com.julyyu.arsenal.exercise.ipcExercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.exercise.adapter.ItemAdapter;
import com.julyyu.arsenal.aidl.AIDLActivity;
import com.julyyu.arsenal.ui.base.BaseAppFragment;
import com.julyyu.uilibrary.adapter.BaseRecyclerAdapter;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by julyyu on 2018/2/23.
 */

public class IPCFragment extends BaseAppFragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    ItemAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.view_recyclerview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ItemAdapter(Arrays.asList(new String[]{
                "Messenger",
                "AIDL",
                "Socket"
        }));
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapter);
        adapter.setItemSingleListener(new BaseRecyclerAdapter.ItemSingleListener<String>() {
            @Override
            public void onSingleClick(String clickedTask, int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getContext(),MessengerClientActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getContext(),AIDLActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getContext(),SocketActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

    }
}
