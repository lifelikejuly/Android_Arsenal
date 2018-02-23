// IOnNewBookArrivedListener.com.julyyu.arsenal.aidl
package com.julyyu.arsenal.aidl;

import com.julyyu.arsenal.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
