package com.julyyu.arsenal.exercise.dataBindingExercise;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.julyyu.arsenal.BR;


/**
 * Created by julyyu on 2018/2/28.
 */

public class User extends BaseObservable {

    private String userName = "null";
    private int userAccount = 0;

    public User(String userName, int userAccount) {
        this.userName = userName;
        this.userAccount = userAccount;
    }
//    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
//        notifyPropertyChanged(BR.userName);
    }

    public int getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(int userAccount) {
        this.userAccount = userAccount;
    }
}
