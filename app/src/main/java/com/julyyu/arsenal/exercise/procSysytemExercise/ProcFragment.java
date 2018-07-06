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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by haocanyu on 2018/6/26.
 */

public class ProcFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.view_null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                getCPU_total();
//                getCPU_app(android.os.Process.myPid());
//                getCPU_app2(android.os.Process.myPid());
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
                String str2 = "";
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
            FileReader fr = new FileReader("/proc/" + pid + "/status");
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                LogUtils.printD(str2);
            }
        } catch (IOException e) {
        }
    }

    public static void gettask(int pid) {
        File threadDir = new File("/proc/" + pid + "/task");
        File[] threadFiles = threadDir.listFiles();
        String str2;
        String cpuThreads = "";
        for (File threadFile : threadFiles) {
            BufferedReader pidReader = null;
            try {
                File statFile = new File(threadFile, "stat");
                pidReader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(statFile)), 1000);

                String line = pidReader.readLine();
                if (line != null) {
                    // 找到第一个'('和最后一个')'
                    int firstQianKuoHao = 0;
                    int lastHouKuoHao = 0;
                    for (int k = 0; k < line.length(); k++) {
                        if (line.charAt(k)=='(' && firstQianKuoHao==0) {
                            firstQianKuoHao = k;
                        }

                        if (line.charAt(k)==')' && k > lastHouKuoHao) {
                            lastHouKuoHao = k;
                        }
                    }

                    String threadId = line.substring(0, firstQianKuoHao - 1);
                    String threadName = line.substring(firstQianKuoHao + 1,lastHouKuoHao);
                    String lastLine = line.substring(lastHouKuoHao + 2,line.length());
                    String[] pidCpuInfoList = lastLine.split(" ");

                    if (pidCpuInfoList.length >= 17) {
                        long threadCpp = Long.parseLong(pidCpuInfoList[11]) +
                                Long.parseLong(pidCpuInfoList[12]);
                        cpuThreads = cpuThreads + threadId + ":" + threadCpp + ":" +
                                threadName.replace(",","@@@")
                                        .replace(":","%%%")
                                        .replace("\n","")+",";

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (pidReader != null) {
                    try {
                        pidReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        LogUtils.printD(cpuThreads);
//        try {
//            String str2 = "";
//            FileReader fr = new FileReader("/proc/"+ pid+"/task");
//            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
//            while ((str2 = localBufferedReader.readLine()) != null) {
//                LogUtils.printD(str2);
//            }
//        } catch (IOException e) {
//        }
    }
}
