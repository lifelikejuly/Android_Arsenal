package com.julyyu.arsenal.exercise.AccountManagerExercise;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;

/**
 * Created by haocanyu on 11/04/2018.
 */

public class AccountManagerFragmengt extends BaseAppFragment{
    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AccountManager accountManager = AccountManager.get(getContext());
        Account riderAccount = new Account(getString(R.string.app_name),"account");
//        accountManager.addAccountExplicitly(riderAccount,getString(R.string.app_name),null); 
//        ContentResolver.setIsSyncable(riderAccount,"july",1); 
//        ContentResolver.addPeriodicSync(riderAccount,"july",new Bundle(),60);
//        //开启同步
//        ContentResolver.setSyncAutomatically(riderAccount, "july", true);

        accountManager.addAccountExplicitly(riderAccount,getString(R.string.app_name),null);
        ContentResolver.setIsSyncable(riderAccount,"july",1);
        ContentResolver.addPeriodicSync(riderAccount,"july",new Bundle(),60);
        ContentResolver.setSyncAutomatically(riderAccount,"july",true);
    }
}
