//package com.julyyu.arsenal.ipcExercise;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.os.RemoteException;
//
//import com.julyyu.arsenal.R;
//import com.julyyu.arsenal.beta.Book;
//import com.julyyu.arsenal.beta.IBookManager;
//import com.julyyu.arsenal.beta.IOnNewBookArrivedListener;
//import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;
//import com.julyyu.utilslibrary.util.LogUtils;
//
//import java.util.List;
//
///**
// * Created by julyyu on 2018/2/22.
// */
//
//public class AIDLActivity extends BaseToolBarTitleActivity {
//
//    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
//    private IBookManager mRemoteBookManager;
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MESSAGE_NEW_BOOK_ARRIVED:
//                    LogUtils.printI(getClass().getName(), "receive new book :" + msg.obj);
//                    break;
//                default:
//                    super.handleMessage(msg);
//            }
//        }
//    };
//
//    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
//
//        @Override
//        public void onNewBookArrived(Book newBook) throws RemoteException {
//            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook)
//                    .sendToTarget();
//        }
//    };
//
//    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
//        @Override
//        public void binderDied() {
//            LogUtils.printI(getClass().getName(), "binder died. tname:" + Thread.currentThread().getName());
//            if (mRemoteBookManager == null)
//                return;
//            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
//            mRemoteBookManager = null;
//            // TODO:这里重新绑定远程Service
//        }
//    };
//
//    private ServiceConnection mConnection = new ServiceConnection() {
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            IBookManager bookManager = IBookManager.Stub.asInterface(service);
//            mRemoteBookManager = bookManager;
//            try {
//                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient, 0);
//                List<Book> list = bookManager.getBookList();
//                LogUtils.printI(getClass().getName(), "query book list, list type:"
//                        + list.getClass().getCanonicalName());
//                LogUtils.printI(getClass().getName(), "query book list:" + list.toString());
//                Book newBook = new Book(3, "Android进阶");
//                bookManager.addBook(newBook);
//                LogUtils.printI(getClass().getName(), "add book:" + newBook);
//                List<Book> newList = bookManager.getBookList();
//                LogUtils.printI(getClass().getName(), "query book list:" + newList.toString());
//                bookManager.registerListener(mOnNewBookArrivedListener);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//        public void onServiceDisconnected(ComponentName className) {
//            mRemoteBookManager = null;
//            LogUtils.printI(getClass().getName(), "onServiceDisconnected. tname:" + Thread.currentThread().getName());
//        }
//    };
//
//    @Override
//    protected int getLayout() {
//        return R.layout.view_null;
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//    public void onButton1Click() {
//        showToast("click button1");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (mRemoteBookManager != null) {
//                    try {
//                        List<Book> newList = mRemoteBookManager.getBookList();
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }
//
//
//    @Override
//    protected void initDate() {
//        Intent intent = new Intent(this, AIDLService.class);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (mRemoteBookManager != null
//                && mRemoteBookManager.asBinder().isBinderAlive()) {
//            try {
//                LogUtils.printI(getClass().getName(), "unregister listener:" + mOnNewBookArrivedListener);
//                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//        unbindService(mConnection);
//        super.onDestroy();
//    }
//}
