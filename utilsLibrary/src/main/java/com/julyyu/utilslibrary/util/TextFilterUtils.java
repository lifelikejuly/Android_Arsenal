package com.julyyu.utilslibrary.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by JulyYu on 2016/8/8.
 * 文本过滤
 */
public class TextFilterUtils {


    /**
     * 过滤emoji输入
     */
    public static void emojiFilter(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setFilters(new InputFilter[]{EMOJI_FILTER});
        }
    }

    /**
     * 过滤中文和空格
     * @param editTexts
     */
    public static void chineseFilter(EditText... editTexts){
        for (EditText editText : editTexts) {
            editText.setFilters(new InputFilter[]{CHINESE_FILTER});
        }
    }

    /**
     * 过滤文本的emoji内容
     */
    public static String removeEmojiText(String text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        return text.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]|\\u2B50|\\u2B55", "");
    }

    private static boolean isEmojiCharacter(char codePoint) {
        //emoji unicode范围
        return (codePoint >= 0x1F601 && codePoint <= 0x1F64F)
                || codePoint == 0x2702 || codePoint == 0x2705
                || (codePoint >= 0x2708 && codePoint <= 0x270F)
                || (codePoint >= 0x2712 && codePoint <= 0x27B0)
                || codePoint == 0x1F680
                || (codePoint >= 0x1F683 && codePoint <= 0x1F6C0)
                || (codePoint >= 0x1F170 && codePoint <= 0x1F251)
                || (codePoint >= 0x1F600 && codePoint <= 0x1F636)
                || (codePoint >= 0x1F681 && codePoint <= 0x1F6C5)
                || (codePoint >= 0x1F30D && codePoint <= 0x1F567);
        //(codePoint >= 0x1F300 && codePoint <= 0x1F5FF)
    }

    public static InputFilter EMOJI_FILTER = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                char charCode = source.charAt(index);
                int  type     = Character.getType(charCode);
                if (type == Character.SURROGATE || isEmojiCharacter(charCode)) {
                    return "";
                }
            }
            return null;
        }
    };


    public static InputFilter CHINESE_FILTER = new InputFilter() {
        public CharSequence filter(CharSequence src, int start, int end, Spanned dst, int dstart, int dend) {
            if (src.length() < 1) {
                return null;
            } else {
                char temp[]   = (src.toString()).toCharArray();
                char result[] = new char[temp.length];
                for (int i = 0, j = 0; i < temp.length; i++) {
                    if (temp[i] == ' ' || isChinese(temp[i])) {
                        continue;
                    } else {
                        result[j++] = temp[i];
                    }
                }
                return String.valueOf(result).trim();
            }
        }
    };

    /**
     * 判定输入汉字
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
