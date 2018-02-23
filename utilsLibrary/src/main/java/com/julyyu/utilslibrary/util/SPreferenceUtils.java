package com.julyyu.utilslibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by JulyYu on 2016/8/27.
 *
 */
public class SPreferenceUtils {


    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    static SPreferenceUtils sSPreferenceUtils;

    Context mContext;

    static String mName;

    public SPreferenceUtils(@NonNull Context context, @NonNull String name){
        mContext = context;
        mName = name;
        sharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SPreferenceUtils getInstance(@NonNull Context context, @NonNull String name){
        if(sSPreferenceUtils == null){
            sSPreferenceUtils = new SPreferenceUtils(context,name);
        }else if(!TextUtils.equals(name,mName)){
            sSPreferenceUtils = new SPreferenceUtils(context,name);
        }
        return sSPreferenceUtils;
    }

    public void saveIntValue(@NonNull String key, int value){
        editor.putInt(key,value);
        editor.commit();
    }
    public void saveStringValue(@NonNull String key,@NonNull String value){
        editor.putString(key,value);
        editor.commit();
    }
    public void saveBoolValue(@NonNull String key,Boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }
    public String getStringValue(String key){
        return sharedPreferences.getString(key,"");
    }
    public int getIntValue(String key){
        return sharedPreferences.getInt(key,0);
    }
    public boolean getBoolValue(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    public void clearInfo(){
        editor.clear();
        editor.commit();
    }

}
