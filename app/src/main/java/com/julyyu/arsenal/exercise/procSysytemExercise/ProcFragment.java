package com.julyyu.arsenal.exercise.procSysytemExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.utilslibrary.util.LogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by haocanyu on 2018/6/26.
 */

public class ProcFragment extends BaseFragment{

    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getCPU_total();
                getCPU_app(android.os.Process.myPid());
                getCPU_app2(android.os.Process.myPid());
                gettask(android.os.Process.myPid());
//                long cpuApp = getCPU_app(android.os.Process.myPid());
//                LogUtils.printD(cpuApp + "");
//                getCPU_app2(android.os.Process.myPid());
//                long cpuApp2 = getCPU_app2(android.os.Process.myPid());
//                LogUtils.printD(cpuApp2 + "");
            }
        }).start();

    }
    public static void getCPU_total() {
        try {
            String str2 = "";
            FileReader fr = new FileReader("/proc/stat");
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                LogUtils.printD(str2);
            }
        } catch (IOException e) {
        }
    }

    public static long getCPU_app(int pid) {
        Scanner scanner = null;
        long cpuApp = 0;
        try {

            try {
                String str2="";
                FileReader fr = new FileReader("/proc/" + pid + "/stat");
                BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
                while ((str2 = localBufferedReader.readLine()) != null) {
                    LogUtils.printD(str2);
                }
            } catch (IOException e) {
            }
            scanner = new Scanner(new File("/proc/" + pid + "/stat"));

            int i = 0;
            while (scanner.hasNext() && i < 13) {
                scanner.next();
                i++;
            }

            cpuApp = scanner.nextLong() +
                    scanner.nextLong() +
                    scanner.nextLong() +
                    scanner.nextLong();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        LogUtils.printD("cpuApp: " + cpuApp);
        return cpuApp;
    }

    public static void getCPU_app2(int pid) {
        try {
            String str2 = "";
            FileReader fr = new FileReader("/proc/"+ pid+"/status");
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                LogUtils.printD(str2);
            }
        } catch (IOException e) {
        }
    }

    public static void gettask(int pid) {
        try {
            String str2 = "";
            FileReader fr = new FileReader("/proc/"+ pid+"/task");
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                LogUtils.printD(str2);
            }
        } catch (IOException e) {
        }
    }
}
