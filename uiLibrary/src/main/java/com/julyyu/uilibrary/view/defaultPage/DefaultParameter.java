package com.julyyu.uilibrary.view.defaultPage;

import android.support.annotation.DrawableRes;

/**
 * Created by JulyYu on 2017/4/18.
 */

public class DefaultParameter {

    @DrawableRes
    public int mDefaultIcon;
    public String mBigTitle;
    public String mSmallTitle;
    public String mActionTitle;

    private DefaultParameter(Builder builder) {
        mDefaultIcon = builder.mDefaultIcon;
        mBigTitle = builder.mBigTitle;
        mSmallTitle = builder.mSmallTitle;
        mActionTitle = builder.mActionTitle;
    }


    public static final class Builder {
        private int mDefaultIcon;
        private String mBigTitle;
        private String mSmallTitle;
        private String mActionTitle;

        public Builder() {
        }

        public Builder DefaultIcon(int val) {
            mDefaultIcon = val;
            return this;
        }

        public Builder BigTitle(String val) {
            mBigTitle = val;
            return this;
        }

        public Builder SmallTitle(String val) {
            mSmallTitle = val;
            return this;
        }

        public Builder ActionTitle(String val) {
            mActionTitle = val;
            return this;
        }

        public DefaultParameter build() {
            return new DefaultParameter(this);
        }
    }
}
