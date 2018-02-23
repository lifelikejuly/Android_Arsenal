package com.julyyu.utilslibrary.fileProcessing;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by JulyYu on 2016/12/28.
 * 文件处理工具
 */

public class FilePathUtils {

    private static final String FILE_PATH = "/FileFolder";


    /*
       * 判断sdcard是否被挂载
       */
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取应用包文件夹
     *
     * @param application
     * @return
     */
    public static File getAppPackageFileFolder(@NonNull Application application) {
        File cacheFolder = new File(application.getApplicationContext().getFilesDir().getAbsolutePath() + FILE_PATH);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdir() == true ? cacheFolder : null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return cacheFolder;
        }
    }

    public static String getAppPackageFilePath(@NonNull Application application) {
        File cacheFolder = new File(application.getApplicationContext().getFilesDir().getAbsolutePath() + FILE_PATH);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdirs() == true ? cacheFolder.getAbsolutePath() : null;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return cacheFolder.getAbsolutePath();
        }
    }

    public static String getEnvironmentPath() {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + FILE_PATH);
        if (cacheFolder.exists()) {
            return cacheFolder.getAbsolutePath();
        } else {
            try {
                boolean create = cacheFolder.mkdir();
                return  create ? cacheFolder.getAbsolutePath() : null;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * 获取Environment文件夹
     *
     * @return
     */
    public static File getEnvironmentFileFolder() {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + FILE_PATH);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdir() == true ? cacheFolder : null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return cacheFolder;
        }
    }


    /**
     * 获取应用包文件夹
     *
     * @param application
     * @return
     */
    public static File getAppPackageFileFolder(@NonNull Application application, String filePath) {
        File cacheFolder = new File(application.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filePath);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdir() == true ? cacheFolder : null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return cacheFolder;
        }
    }

    public static String getAppPackageFilePath(@NonNull Application application, String filePath) {
        File cacheFolder = new File(application.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + filePath);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdirs() == true ? cacheFolder.getAbsolutePath() : null;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return cacheFolder.getAbsolutePath();
        }
    }

    public static String getEnvironmentPath(String filePath) {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + "/" + filePath);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdir() == true ? cacheFolder.getAbsolutePath() : null;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return cacheFolder.getAbsolutePath();
        }
    }

    /**
     * 获取Environment文件夹
     *
     * @return
     */
    public static File getEnvironmentFileFolder(String filePath) {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + "/" + filePath);
        if (!cacheFolder.exists()) {
            try {
                return cacheFolder.mkdir() == true ? cacheFolder : null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return cacheFolder;
        }
    }

    public static boolean saveNetFile(Context context, String fileName, String fileUrl, String folderPath) {

        InputStream  in   = null;
        OutputStream out  = null;
        File         file = null;
        try {
            URL           url = new URL(fileUrl);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5 * 1000);
            int contentLength = con.getContentLength();
            in = con.getInputStream();
            byte[] bytes = new byte[1024];
            file = new File(folderPath, fileName);
            out = new FileOutputStream(file);
            int len;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.fromFile(file);
            // 通知更新
            Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            context.sendBroadcast(scannerIntent);
            return true;
        }
    }

}
