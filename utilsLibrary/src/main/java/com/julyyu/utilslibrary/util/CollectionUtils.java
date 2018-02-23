package com.julyyu.utilslibrary.util;

import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * Created by JulyYu on 2016/8/27.
 * 集合工具
 */
public class CollectionUtils {

    public static boolean isEmpty(@Nullable Collection collection){
        if(collection == null || collection.size() == 0){
            return true;
        }
        return collection.isEmpty();
    }

    public static int getSize(@Nullable Collection collection){
        if(collection == null){
            return 0;
        }
        return collection.size();
    }
}
