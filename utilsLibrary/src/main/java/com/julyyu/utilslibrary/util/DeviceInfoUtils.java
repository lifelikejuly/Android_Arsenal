package com.julyyu.utilslibrary.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by JulyYu on 2016/12/29.
 * 获取手机信息需要的权限<uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
 * 设备信息工具
 */

public class DeviceInfoUtils {

    /**
     * 手机基本信息详情
     * @param context
     * @return
     */
    @Deprecated
    public static String getPhoneInfoStr(Context context) {
        try {
            TelephonyManager mTm = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
            String imei = mTm.getDeviceId();
            String imsi = mTm.getSubscriberId();
            String mtype = android.os.Build.MODEL; // 手机型号
            String mtyb= android.os.Build.BRAND;//手机品牌
            String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
            return "手机IMEI号：" + imei + '\n' +
                    "手机IESI号：" + imsi + '\n' +
                    "手机型号：" + mtype + '\n' +
                    "手机品牌："+ mtyb + '\n' +
                    "手机号码"+numer;
        }catch (SecurityException e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取手机基本信息
     * 需要获取READ_PHONE_STATE权限
     * @param context
     * @return imei,imsi,mtype,mtyb,number
     */
    public static String[] getPhoneInfos(Context context){
        try {
            TelephonyManager mTm = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
            String imei = mTm.getDeviceId();
            String imsi = mTm.getSubscriberId();
            String mtype = android.os.Build.MODEL; // 手机型号
            String mtyb= android.os.Build.BRAND;//手机品牌
            String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
            return new String[]{imei,imsi,mtype,mtyb,numer};
        }catch (SecurityException e){
            e.printStackTrace();
            return new String[0];
        }
    }

    /**
     * 手机基本信息详情
     * 需要获取READ_PHONE_STATE权限
     * @param context
     * @return
     */
    @Deprecated
    public static String[] getPhoneInfoStrList(Context context){
        TelephonyManager mTm = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        String imsi = mTm.getSubscriberId();
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return new String[]{imei, imsi, mtype, mtyb, numer};
    }

    /**
     * 获取手机CPU信息
     * @return
     */
    private String[] getCPU_Info() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return cpuInfo;
    }

    public static String getMac() {
        String str       = "";
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir    = new InputStreamReader(pp.getInputStream());
            LineNumberReader  input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (macSerial == null || "".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return macSerial;
    }

    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String     text   = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder    = new StringBuilder();
        char[]        buffer     = new char[4096];
        int           readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }
}
