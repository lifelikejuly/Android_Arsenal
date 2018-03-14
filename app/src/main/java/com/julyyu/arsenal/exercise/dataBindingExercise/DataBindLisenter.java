package com.julyyu.arsenal.exercise.dataBindingExercise;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.julyyu.utilslibrary.util.LogUtils;

/**
 * Created by julyyu on 2018/2/28.
 */

public class DataBindLisenter {

   Context mContext;

   public DataBindLisenter(Context context) {
      mContext = context;
   }

   public void userNameChange(View view){
        String text = ((EditText)view).getText().toString();
      LogUtils.printI("DataBinding",text);
   }
   public void getUserName(View view,User user){
      Toast.makeText(view.getContext(),user.getUserName(),Toast.LENGTH_SHORT).show();
   }
}
