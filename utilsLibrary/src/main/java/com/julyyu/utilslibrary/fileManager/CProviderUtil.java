package com.julyyu.utilslibrary.fileManager;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;


import com.julyyu.utilslibrary.fileManager.model.FileCursor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julyyu on 2017/7/12.
 */

public class CProviderUtil {


    /**
     * 获取视频
     *
     * @return
     */
    public static List<FileCursor> getVideoFile(Application app) {
        List<FileCursor> fileCursors = new ArrayList<>();
        Cursor cursor = app.getApplicationContext().getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATE_MODIFIED,
                        MediaStore.Video.Media.MIME_TYPE
                },
                null,
                null,
                "LOWER(" + MediaStore.Video.Media.TITLE + ") ASC"
        );
        if (cursor.moveToFirst()) {
            do {
                FileCursor fileCursor = new FileCursor();
                fileCursor.name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                fileCursor.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                fileCursor.size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                fileCursor.mime = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                fileCursor.time = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
                fileCursors.add(fileCursor);
            } while (cursor.moveToNext());
        }
        return fileCursors;
    }

    /**
     * 获取图片
     *
     * @return
     */
    public static List<FileCursor> getPicFile(Application app) {
        List<FileCursor> fileCursors = new ArrayList<>();
        Cursor cursor = app.getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.SIZE,
                        MediaStore.Video.Media.DATE_MODIFIED,
                        MediaStore.Images.Media.MIME_TYPE
                },
                null,
                null,
                "LOWER(" + MediaStore.Images.Media.TITLE + ") ASC"
        );
        if (cursor.moveToFirst()) {
            do {
                FileCursor fileCursor = new FileCursor();
                fileCursor.name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                fileCursor.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                fileCursor.size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                fileCursor.mime = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
                fileCursor.time = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED));
                fileCursors.add(fileCursor);
            } while (cursor.moveToNext());
        }
        return fileCursors;
    }

    /**
     * 获取Zip
     *
     * @return
     */
    public static List<FileCursor> getZipFile(Application app) {
        List<FileCursor> fileCursors = new ArrayList<>();
        String select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.zip'" + " or " +
                MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.rar'" + " or " +
                MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.7z'" + ")";

        Cursor cursor = app.getApplicationContext().getContentResolver().query(
                MediaStore.Files.getContentUri("external"),
                new String[]{
                        MediaStore.Files.FileColumns._ID,
                        MediaStore.Files.FileColumns.DISPLAY_NAME,
                        MediaStore.Files.FileColumns.DATA,
                        MediaStore.Files.FileColumns.SIZE,
                        MediaStore.Files.FileColumns.DATE_MODIFIED,
                        MediaStore.Files.FileColumns.MIME_TYPE
                },
                select,
                null,
                "LOWER(" + MediaStore.Files.FileColumns.TITLE + ") ASC"
        );
        if (cursor.moveToFirst()) {
            do {
                FileCursor fileCursor = new FileCursor();
                fileCursor._id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID));
                fileCursor.name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME));
                fileCursor.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                fileCursor.size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE));
                fileCursor.mime = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE));
                fileCursor.time = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED));
                fileCursors.add(fileCursor);
            } while (cursor.moveToNext());
        }
        return fileCursors;
    }

    public static List<FileCursor> getClassfyFile(Application app, FileClassfy.Classfy classfy) {
        List<FileCursor> fileCursors = new ArrayList<>();
        String           select      = "";
        switch (classfy) {
            case video:
                select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.avi'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.mp4'" + ")";
                break;
            case audio:
                select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.wav'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.ogg'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.wma'" + ")";
                break;
            case picture:
                select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.jpg'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.png'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.jpeg'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.gif'" + ")";
                break;
            case doc:
                select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.doc'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.docx'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.txt'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.log'" + ")";
                break;
            case apk:
                select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.apk'" + ")";
                break;
            case zip:
                select = "(" + MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.zip'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.rar'" + " or " +
                        MediaStore.Files.FileColumns.DATA.toLowerCase() + " LIKE '%.7z'" + ")";
                break;
        }
        Cursor cursor = app.getApplicationContext().getContentResolver().query(
                MediaStore.Files.getContentUri("external"),
                new String[]{
                        MediaStore.Files.FileColumns._ID,
                        MediaStore.MediaColumns.DISPLAY_NAME,
                        MediaStore.Files.FileColumns.DATA,
                        MediaStore.Files.FileColumns.SIZE,
                        MediaStore.Files.FileColumns.DATE_MODIFIED,
                        MediaStore.Files.FileColumns.MIME_TYPE
                },
                select,
                null,
                "LOWER(" + MediaStore.Files.FileColumns.TITLE + ") ASC"
        );
        if (cursor.moveToFirst()) {
            do {
                FileCursor fileCursor = new FileCursor();
                fileCursor._id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID));
                fileCursor.name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME));
                fileCursor.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                fileCursor.size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE));
                fileCursor.mime = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE));
                fileCursor.time = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED));
                if (classfy == FileClassfy.Classfy.apk) {
                    PackageManager pm = app.getPackageManager();
                    PackageInfo    pi = pm.getPackageArchiveInfo(fileCursor.path, PackageManager.GET_ACTIVITIES);
                    if (pi != null) {
                        pi.applicationInfo.sourceDir = fileCursor.path;
                        pi.applicationInfo.publicSourceDir = fileCursor.path;
                        fileCursor.icon = pm.getApplicationIcon(pi.applicationInfo);
                    }
                }
                fileCursors.add(fileCursor);
            } while (cursor.moveToNext());
        }
        return fileCursors;
    }

    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    private static void getChildFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                getChildFiles(f);
            }
        } else {
            String fileName = root.getName().toLowerCase();
            if (fileName.endsWith(".jpg")
                    || fileName.endsWith(".png")) {//图像
                Log.i("fileName", fileName);
            } else if (fileName.endsWith(".apk")) {//安装包
                Log.i("fileName", fileName);
            } else if (fileName.endsWith(".mp4")
                    || fileName.endsWith(".avi")) {//视频
                Log.i("fileName", fileName);
            } else if (fileName.endsWith(".mp3")) {//音频
                Log.i("fileName", fileName);
            } else if (fileName.endsWith(".txt")
                    || fileName.endsWith(".doc")
                    || fileName.endsWith(".docx")
                    || fileName.endsWith(".pdf")) {//文档
                Log.i("fileName", fileName);
            } else if (fileName.endsWith(".rar")
                    || fileName.endsWith(".zip")) {//压缩包
                Log.i("fileName", fileName);
            }
        }
    }

}
