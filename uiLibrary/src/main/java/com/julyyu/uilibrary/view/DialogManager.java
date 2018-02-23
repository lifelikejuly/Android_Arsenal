package com.julyyu.uilibrary.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

/**
 * Created by julyyu on 2017/7/11.
 */

public class DialogManager {

    public static void showItemsDialog(Context context, @StyleRes int theme, @ArrayRes int array, DialogInterface.OnClickListener onClickListenerl){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, theme));
        builder.setItems(array, onClickListenerl);
        builder.show();
    }
}
