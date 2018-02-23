package com.julyyu.utilslibrary.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by JulyYu on 2016/12/28.
 * 剪切板工具
 * TODO
 */

public class ClipboardUtils {

    public static void copyText(Context context, CharSequence text){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("Label",text));
    }

    public static CharSequence getCopyText(Context context){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData != null && clipData.getItemCount() > 0){
            return clipData.getItemAt(0).coerceToText(context);
        }else{
            return null;
        }
    }
}
