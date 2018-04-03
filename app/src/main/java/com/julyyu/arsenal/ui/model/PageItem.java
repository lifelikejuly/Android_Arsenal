package com.julyyu.arsenal.ui.model;

import java.io.Serializable;

/**
 * Created by haocanyu on 03/04/2018.
 */

public class PageItem implements Serializable{

    public String name;
    public String pageName;

    public PageItem(String name, String pageName) {
        this.name = name;
        this.pageName = pageName;
    }
}
