package com.julyyu.arsenal;

import android.content.Context;
import android.content.Intent;

import com.julyyu.arsenal.launchModeExercise.SingleInstanceActivity;
import com.julyyu.arsenal.launchModeExercise.SingleTaskActivity;
import com.julyyu.arsenal.launchModeExercise.SingleTopActivity;
import com.julyyu.arsenal.launchModeExercise.StandardActivity;
import com.julyyu.arsenal.ui.activity.DefaultActivity;


/**
 * Created by julyyu on 2017/7/6.
 */

public class Route {

    public static void goPage(Context context,int item){
        Intent intent = new Intent(context, DefaultActivity.class);
        intent.putExtra("position",item);
        context.startActivity(intent);
    }

    public static void goStandard(Context context){
        Intent intent = new Intent(context, StandardActivity.class);
        context.startActivity(intent);
    }
    public static void goSingleTop(Context context){
        Intent intent = new Intent(context, SingleTopActivity.class);
        context.startActivity(intent);
    }
    public static void goSingleTask(Context context){
        Intent intent = new Intent(context, SingleTaskActivity.class);
        context.startActivity(intent);
    }
    public static void goSingleInstance(Context context){
        Intent intent = new Intent(context, SingleInstanceActivity.class);
        context.startActivity(intent);
    }
}