//package com.julyyu.arsenal.ipcExercise;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Binder;
//import android.os.IBinder;
//import android.os.Parcel;
//import android.os.RemoteCallbackList;
//import android.os.RemoteException;
//import android.os.SystemClock;
//
//import com.julyyu.arsenal.beta.Book;
//import com.julyyu.arsenal.beta.IBookManager;
//import com.julyyu.arsenal.beta.IOnNewBookArrivedListener;
//import com.julyyu.utilslibrary.util.LogUtils;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.atomic.AtomicBoolean;
//
///**
// * Created by julyyu on 2018/2/22.
// */
//
//public class AIDLService extends Service {
//
//    /**
//     * 原子变量
//     * 具有排他性，即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入。
//     */
//    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
//
//    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
//    // private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =
//    // new CopyOnWriteArrayList<IOnNewBookArrivedListener>();
//
//    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();
//
//    private Binder mBinder = new IBookManager.Stub() {
//
//        @Override
//        public List<Book> getBookList() throws RemoteException {
//            SystemClock.sleep(5000);
//            return mBookList;
//        }
//
//        @Override
//        public void addBook(Book book) throws RemoteException {
//            mBookList.add(book);
//        }
//
//        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
//                throws RemoteException {
//            int check = checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE");
//            LogUtils.printI(getClass().getName(),   "check=" + check);
//            if (check == PackageManager.PERMISSION_DENIED) {
//                return false;
//            }
//
//            String packageName = null;
//            String[] packages = getPackageManager().getPackagesForUid(
//                    getCallingUid());
//            if (packages != null && packages.length > 0) {
//                packageName = packages[0];
//            }
//            LogUtils.printI(getClass().getName(),  "onTransact: " + packageName);
//            if (!packageName.startsWith("com.ryg")) {
//                return false;
//            }
//
//            return super.onTransact(code, data, reply, flags);
//        }
//
//        @Override
//        public void registerListener(IOnNewBookArrivedListener listener)
//                throws RemoteException {
//            mListenerList.register(listener);
//
//            final int N = mListenerList.beginBroadcast();
//            mListenerList.finishBroadcast();
//            LogUtils.printI(getClass().getName(),  "registerListener, current size:" + N);
//        }
//
//        @Override
//        public void unregisterListener(IOnNewBookArrivedListener listener)
//                throws RemoteException {
//            boolean success = mListenerList.unregister(listener);
//
//            if (success) {
//                LogUtils.printI(getClass().getName(),  "unregister success.");
//            } else {
//                LogUtils.printI(getClass().getName(),  "not found, can not unregister.");
//            }
//            final int N = mListenerList.beginBroadcast();
//            mListenerList.finishBroadcast();
//            LogUtils.printI(getClass().getName(),  "unregisterListener, current size:" + N);
//        };
//
//    };
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mBookList.add(new Book(1, "Android"));
//        mBookList.add(new Book(2, "Ios"));
//        new Thread(new ServiceWorker()).start();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        int check = checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE");
//        LogUtils.printI(getClass().getName(),  "onbind check=" + check);
//        if (check == PackageManager.PERMISSION_DENIED) {
//            return null;
//        }
//        return mBinder;
//    }
//
//    @Override
//    public void onDestroy() {
//        mIsServiceDestoryed.set(true);
//        super.onDestroy();
//    }
//
//    private void onNewBookArrived(Book book) throws RemoteException {
//        mBookList.add(book);
//        final int N = mListenerList.beginBroadcast();
//        for (int i = 0; i < N; i++) {
//            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
//            if (l != null) {
//                try {
//                    l.onNewBookArrived(book);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        mListenerList.finishBroadcast();
//    }
//
//    private class ServiceWorker implements Runnable {
//        @Override
//        public void run() {
//            // do background processing here.....
//            while (!mIsServiceDestoryed.get()) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                int bookId = mBookList.size() + 1;
//                Book newBook = new Book(bookId, "new book#" + bookId);
//                try {
//                    onNewBookArrived(newBook);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//}
