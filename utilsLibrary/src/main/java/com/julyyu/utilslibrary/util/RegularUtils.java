package com.julyyu.utilslibrary.util;

import android.support.annotation.NonNull;
import android.text.util.Linkify;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JulyYu on 2016/4/12.
 * 正则表达工具
 */
public class RegularUtils {

    /** 数字 */
    public static boolean isNumeric(@NonNull String s){
        Pattern pattern =  Pattern.compile(".*?[0-9]+.*?");
        Matcher isNum = pattern.matcher(s);
        if (isNum.matches()) {
            return true;
        }else{
            return false;
        }
    }
    /** 大写*/
    public static boolean hasBigChar(@NonNull String s){
        Pattern pattern = Pattern.compile(".*?[A-Z]+.*?");
        Matcher isNum = pattern.matcher(s);
        if (isNum.matches()) {
            return true;
        }else{
            return false;
        }

    }
    /** 小写*/
    public static boolean hasSmallChar(@NonNull String s){
        Pattern pattern = Pattern.compile(".*?[a-z]+.*?");
        Matcher isNum = pattern.matcher(s);
        if (isNum.matches()) {
            return true;
        }else{
            return false;
        }

    }
    /** 特殊字符*/
    public static boolean hasSpecialChar(@NonNull String s){

        Pattern pattern = Pattern.compile(".*?[^A-Za-z0-9]+.*?");
        Matcher isNum = pattern.matcher(s);
        if (isNum.matches()) {
            return true;
        }else{
            return false;
        }
    }

    /** 判断手机格式是否正确*/
    public static boolean isMobile(@NonNull String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher isPhone = p.matcher(mobiles);
        if (isPhone.matches()) {
            return true;
        }else{
            return false;
        }
    }

    /** 判断email格式是否正确*/
    public static boolean isEmail(@NonNull String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher isMail = p.matcher(email);
        if (isMail.matches()) {
            return true;
        }else{
            return false;
        }
    }
    /** 将半角字符转换为全角字符*/
    public static String ToSBC(@NonNull String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }
    /** 文本添加链接{电话、邮箱、网址}*/
    public static void setHyperlinkLine(@NonNull TextView textView){
        String p1="\\d{4,11}";
        Pattern patternPhone = Pattern.compile(p1);
        String scheme1="tel:";
        Linkify.addLinks(textView,patternPhone, scheme1);

        String p2="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern patternEmail = Pattern.compile(p2);
        String scheme2="mailto:";
        Linkify.addLinks(textView,patternEmail, scheme2);

        String p3="(http://|www.)[^\\s]*";
        Pattern patternWeb = Pattern.compile(p3);
        String scheme3="http:";
        Linkify.addLinks(textView,patternWeb, scheme3);
    }
    /** 文本添加链接{电话、邮箱、网址}*/
    public static void setHyperlinkLine(@NonNull TextView... textView){
        for(TextView text : textView){
            setHyperlinkLine(text);
        }
    }
}
