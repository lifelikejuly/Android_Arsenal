package com.julyyu.arsenal.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;

import com.julyyu.utilslibrary.util.LogUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by julyyu on 2018/2/22.
 */

public class AIDLService extends Service {

    private static final String TAG = "AIDLService";
    private static final String permission = "com.julyyu.permission.ACCESS_BOOK_SERVICE";
    /**
     * 原子变量
     * 具有排他性，即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入。
     */
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    // private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =
    // new CopyOnWriteArrayList<IOnNewBookArrivedListener>();
    /**
     * 远程访问的回调接口
     */
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    /**
     * 实例化AIDL接口方法
     */
    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            SystemClock.sleep(5000);
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        /**
         * 客户端发送请求后由底层交付至这个入口到达服务端。
         * 若返回false则表示客户端请求失败。
         * 因此该方法可以作为权限验证入口。
         * @param code
         * @param data
         * @param reply
         * @param flags
         * @return
         * @throws RemoteException
         */
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            //这里做了两层检查 一层是权限判断，另一层是包名判断

            int check = checkCallingOrSelfPermission(permission);
            LogUtils.printI(TAG,   "check=" + check);
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }

            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(
                    getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            LogUtils.printI(TAG,  "onTransact: " + packageName);
            if (!packageName.startsWith("com.julyyu")) {
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener)
                throws RemoteException {
            mListenerList.register(listener);

            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            LogUtils.printI(TAG,  "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener)
                throws RemoteException {
            boolean success = mListenerList.unregister(listener);

            if (success) {
                LogUtils.printI(TAG,  "unregister success.");
            } else {
                LogUtils.printI(TAG,  "not found, can not unregister.");
            }
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            LogUtils.printI(TAG,  "unregisterListener, current size:" + N);
        };

    };

    /**
     * 服务启动初始化书籍列表，并在子线程中定时添加新书籍
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
        new Thread(new ServiceWorker()).start();
    }

    /**
     * 绑定服务前可以增加权限设置，提高应用安全性
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission(permission);
        LogUtils.printI(TAG,  "onbind check=" + check);
        if (check == PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }



    /**
     * 子线程中定时添加书籍并发送到客户端。
     */
    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            // do background processing here.....
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将新增数据通过回调接口发送到客户端
     * @param book
     * @throws RemoteException
     */
    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

}
