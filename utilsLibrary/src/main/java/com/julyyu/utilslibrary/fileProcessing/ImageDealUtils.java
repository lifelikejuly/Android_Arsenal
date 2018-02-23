package com.julyyu.utilslibrary.fileProcessing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by julyyu on 2017/8/2.
 * 图片处理
 */

public class ImageDealUtils {

    /**
     * 计算图片的缩放值
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height       = options.outHeight;
        final int width        = options.outWidth;
        int       inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio  = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 根据路径获得图片并固定480x800尺寸压缩，返回bitmap
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 根据路径获得图片压缩，返回bitmap
     * @param filePath
     * @param rqsW
     * @param rqsH
     * @return
     */
    public static Bitmap getCompressBitmap(String filePath,int rqsW,int rqsH){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, rqsW,rqsH);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 输出压缩bitmap文件
     * @param bitmap
     * @param savePath
     * @param saveName
     * @param quality
     * @return
     * @throws FileNotFoundException
     */
    public static File saveCompressBitmap(Bitmap bitmap,String savePath,String saveName,int quality) throws FileNotFoundException {
        File imageDir = new File(FilePathUtils.getEnvironmentPath(savePath));
        File outFile = new File(imageDir,saveName);
        FileOutputStream out = new FileOutputStream(outFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,out);
        return outFile;
    }
    /**
     * 把bitmap转换成Base64
     * @param filePath
     * @return
     */
    public static String bitmapToString(String filePath) {
        Bitmap                bm   = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     *
     * @param filePath
     */
    public static void getBitmapSize(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //dont read bitmap into cache
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int height       = options.outHeight;
        int width        = options.outWidth;
        int inSampleSize = 1;
        int reqHeight    = 800;
        int reqWidth     = 480;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio  = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

    }

    /**
     * 读取图片角度
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param bitmap
     * @param degress
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap,int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }


}
