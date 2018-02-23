// IBookManager.com.julyyu.arsenal.aidl
package com.julyyu.arsenal.aidl;

// Declare any non-default types here with import statements

import com.julyyu.arsenal.aidl.Book;
import com.julyyu.arsenal.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}
