package com.julyyu.arsenal.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.activity.BaseToolBarTitleActivity;
import com.julyyu.utilslibrary.util.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by julyyu on 2018/2/22.
 */

public class AIDLActivity extends BaseToolBarTitleActivity {


    @BindView(R.id.btn_send)
    Button btnSend;

    private static final String TAG = "AIDLActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    LogUtils.printI(TAG, "receive new book :" + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
    /**
     * 调用服务端获取书籍列表接口：客户端主动向服务端拿去书籍列表
     */
    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook)
                    .sendToTarget();
        }
    };
    /**
     * IBinder死亡回调接口：用于处理远程访问服务死亡或断开时事项
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            LogUtils.printI(TAG, "binder died. tname:" + Thread.currentThread().getName());
            if (mRemoteBookManager == null)
                return;
            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mRemoteBookManager = null;
            // TODO:这里重新绑定远程Service
        }
    };
    /**
     * 连接服务类：服务连接成功后获取AIDL服务接口对象、绑定死亡回调接口、注册访问服务端接口等连接成功后的事项
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            mRemoteBookManager = bookManager;
            try {
                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient, 0);
                List<Book> list = bookManager.getBookList();
                LogUtils.printI(TAG, "query book list, list type:"
                        + list.getClass().getCanonicalName());
                LogUtils.printI(TAG, "query book list:" + list.toString());
                Book newBook = new Book(3, "Android进阶");
                bookManager.addBook(newBook);
                LogUtils.printI(TAG, "add book:" + newBook);
                List<Book> newList = bookManager.getBookList();
                LogUtils.printI(TAG, "query book list:" + newList.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        public void onServiceDisconnected(ComponentName className) {
            mRemoteBookManager = null;
            LogUtils.printI(TAG, "onServiceDisconnected. tname:" + Thread.currentThread().getName());
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_ipc_aidl;
    }

    @Override
    protected void initView() {

    }

    /**
     * 主动向服务端获取数据时需要注意的是，若服务端该方法是一个耗时操作那么会阻塞现所在进程。
     * 所以特别注意耗时操作放入子线程中处理避免阻塞线程。
     */
    @OnClick(R.id.btn_send)
    public void onButton1Click() {
        showToast("click button1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mRemoteBookManager != null) {
                    try {
                        List<Book> newList = mRemoteBookManager.getBookList();
                        LogUtils.printI(TAG, JSON.toJSONString(newList));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    protected void initDate() {
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null
                && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                LogUtils.printI(TAG, "unregister listener:" + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
