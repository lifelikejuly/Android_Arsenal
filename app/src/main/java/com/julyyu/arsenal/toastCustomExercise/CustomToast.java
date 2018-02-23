package com.julyyu.arsenal.toastCustomExercise;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by julyyu on 2018/1/23.
 */

public class CustomToast {

    String text;
    int gravity;
    int duratione;
    View layout;

    private CustomToast(Builder builder) {
        text = builder.text;
        gravity = builder.gravity;
        duratione = builder.duratione;
        layout = builder.layout;
    }

    public void show(Context context){
        Toast toast = new Toast(context);
        toast.setGravity(gravity,0,0);
        toast.setDuration(duratione);
        toast.setView(layout);
        toast.show();
    }

    public static final class Builder {
        private String  text;
        private int gravity;
        private int     duratione;
        private View    layout;

        public Builder() {
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public Builder gravity(int val) {
            gravity = val;
            return this;
        }

        public Builder duratione(int val) {
            duratione = val;
            return this;
        }

        public Builder layout(View val) {
            layout = val;
            return this;
        }

        public CustomToast build() {
            return new CustomToast(this);
        }
    }
}
