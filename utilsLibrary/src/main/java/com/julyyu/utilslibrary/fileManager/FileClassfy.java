package com.julyyu.utilslibrary.fileManager;

import android.support.annotation.DrawableRes;

/**
 * Created by julyyu on 2017/7/12.
 * 文件分类
 */

public class FileClassfy {

    @DrawableRes
    public int     icon;
    public String  classfy;
    public Classfy flieClassfy;

    public FileClassfy(int icon, String classfy, Classfy flieClassfy) {
        this.icon = icon;
        this.classfy = classfy;
        this.flieClassfy = flieClassfy;
    }

    public enum Classfy {
        video,
        audio,
        picture,
        doc,
        apk,
        zip
    }
}
