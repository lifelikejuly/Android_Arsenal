package com.julyyu.arsenal.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.julyyu.arsenal.R;
import com.julyyu.utilslibrary.fileProcessing.FileSizeUtils;
import com.julyyu.utilslibrary.fileProcessing.ImageDealUtils;
import com.julyyu.utilslibrary.util.IntentUtils;
import com.julyyu.uilibrary.fragment.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by julyyu on 2017/8/2.
 */

public class ImageCompressFragment extends BaseFragment {

    @BindView(R.id.iv_origin)
    ImageView ivOrigin;
    @BindView(R.id.tv_origin_info)
    TextView  tvOriginInfo;
    @BindView(R.id.iv_compress)
    ImageView ivCompress;
    @BindView(R.id.tv_compress_info)
    TextView  tvCompressInfo;
    @BindView(R.id.btn_select_image)
    Button    btnSelectImage;
    File compressFile;

    @Override
    protected int getLayout() {
        return R.layout.view_compress;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.btn_select_image,R.id.btn_delect})
    void selectImg(View view){
        switch (view.getId()){
            case R.id.btn_select_image:
                IntentUtils.openAblum(getActivity(),1);
                break;
            case R.id.btn_delect:
                if(compressFile != null){
                    compressFile.delete();
                }
                ivOrigin.setImageBitmap(null);
                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1 && data != null){
                Uri uri = data.getData();
                String[] proj = { MediaStore.Images.Media.DATA };
                // 好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = getActivity().getContentResolver().query(uri, proj, null, null, null);
                // 按我个人理解 这个是获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                // 最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                ivOrigin.setImageBitmap(BitmapFactory.decodeFile(path));
                tvOriginInfo.setText(path + "\n" +
                        FileSizeUtils.getFileOrFilesSize(path,FileSizeUtils.SIZETYPE_MB)
                );
                Bitmap bitmap = ImageDealUtils.getCompressBitmap(path,500,500);
                try {
                    compressFile = ImageDealUtils.saveCompressBitmap(bitmap,"FileFolder",System.currentTimeMillis() + ".jpg",60);
                    ivCompress.setImageBitmap(BitmapFactory.decodeFile(compressFile.getPath()));
                    tvCompressInfo.setText(compressFile.getPath() + "\n" +
                            FileSizeUtils.getFileOrFilesSize(compressFile.getPath(),FileSizeUtils.SIZETYPE_MB)
                    );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
