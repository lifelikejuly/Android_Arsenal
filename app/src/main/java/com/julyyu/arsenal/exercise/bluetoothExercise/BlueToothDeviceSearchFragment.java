package com.julyyu.arsenal.exercise.bluetoothExercise;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.julyyu.arsenal.R;
import com.julyyu.arsenal.ui.base.BaseAppFragment;
import com.julyyu.utilslibrary.util.LogUtils;

import java.util.List;

/**
 * Created by julyyu on 2018/3/10.
 */

public class BlueToothDeviceSearchFragment extends BaseAppFragment {
    BluetoothAdapter   mBluetoothAdapter;
    BluetoothLeScanner mBluetoothLeScanner;
    BlueToothCallBack blueToothCallBack;
    BluetoothGatt bluetoothGatt;
    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        blueToothCallBack = new BlueToothCallBack();
        BluetoothManager bluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mBluetoothAdapter = bluetoothManager.getAdapter();
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 0);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
                    mBluetoothLeScanner.startScan(blueToothCallBack);
                }
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBluetoothLeScanner != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mBluetoothLeScanner.stopScan(blueToothCallBack);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class BlueToothCallBack extends ScanCallback{
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            LogUtils.printI(getClass().getName() + "-onScanResult", JSON.toJSONString(result));
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            LogUtils.printI(getClass().getName() + "-onBatchScanResults", JSON.toJSONString(results));
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            LogUtils.printI(getClass().getName() + "-onScanFailed", errorCode + "");
        }
    }

    /**
     * 连接蓝牙设备
     * @param bluetoothDevice
     */
    private void connectBlueToothDevice( BluetoothDevice bluetoothDevice){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothGatt = bluetoothDevice.connectGatt(getContext(), true, new BluetoothGattCallback() {
                @Override
                public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                    super.onPhyUpdate(gatt, txPhy, rxPhy, status);
                }

                @Override
                public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                    super.onPhyRead(gatt, txPhy, rxPhy, status);
                }

                /**
                 * 连接状态改变
                 * @param gatt
                 * @param status
                 * @param newState
                 */
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                    super.onConnectionStateChange(gatt, status, newState);
                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    super.onServicesDiscovered(gatt, status);
                }

                /**
                 * 读取数据
                 * @param gatt
                 * @param characteristic
                 * @param status
                 */
                @Override
                public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    super.onCharacteristicRead(gatt, characteristic, status);
                }

                /**
                 * 写数据 检查写数据结果是否和自己发送数据相同
                 * @param gatt
                 * @param characteristic
                 * @param status
                 */
                @Override
                public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    super.onCharacteristicWrite(gatt, characteristic, status);
                }

                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                    super.onCharacteristicChanged(gatt, characteristic);
                }

                @Override
                public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    super.onDescriptorRead(gatt, descriptor, status);
                }

                @Override
                public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    super.onDescriptorWrite(gatt, descriptor, status);
                }

                @Override
                public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                    super.onReliableWriteCompleted(gatt, status);
                }

                @Override
                public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                    super.onReadRemoteRssi(gatt, rssi, status);
                }

                @Override
                public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                    super.onMtuChanged(gatt, mtu, status);
                }
            });
        }
    }

    /**
     * 向蓝牙Service设备发送数据
     * @param gatt
     */
    private void  sendBlueToothGatt(BluetoothGatt gatt){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic();
//            gatt.setCharacteristicNotification(bluetoothGattCharacteristic,true);
            BluetoothGattService service = gatt.getService(null);
            BluetoothGattCharacteristic bluetoothGattCharacteristic = service.getCharacteristic(null);
            bluetoothGattCharacteristic.setValue("");
            gatt.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }


}
