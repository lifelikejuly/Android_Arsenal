package com.julyyu.utilslibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;

import com.julyyu.utilslibrary.fileProcessing.FilePathUtils;

import java.io.File;


/**
 * Created by julyyu on 2017/7/7.
 * 调用系统Intent
 */

public class IntentUtils {

    /**
     * 打电话
     * 需要android.permission.CALL_PHONE权限
     * @param context
     * @param phone
     */
    public static void callTel(Context context,String phone){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * app默认设置页
     * @param activity
     * @param packagename
     * @param result
     */
    public static void appDefualtSetting(Activity activity,String packagename,int result){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packagename));
        activity.startActivityForResult(intent, result);
    }

    /**
     * 跳转到发送短信页面
     * @param context
     * @param who
     */
    public static void sendSMS(Context context,String who,String content){
        Uri smsUri = Uri.parse("smsto:" + who);
        Intent intent = new Intent(Intent.ACTION_SENDTO,smsUri);
        intent.putExtra("sms_body",content);
        context.startActivity(intent);
    }

    /**
     * 发送邮件
     * @param context
     * @param who
     * @param content
     */
    public static void sendMail(Context context,String who,String content){
        Uri smsUri = Uri.parse("mailto:" + who);
        Intent intent = new Intent(Intent.ACTION_SENDTO,smsUri);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(intent);
    }

    /**
     * 打开图片
     * @param context
     * @param path
     */
    public static void openImageFile(Context context,String path){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
        context.startActivity(intent);
    }

    /**
     * 打开文本
     * @param context
     * @param path
     */
    public static void openTextFile(Context context,String path){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "text/*");
        context.startActivity(intent);
    }

    public static void installApk(Context context,String path){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }else{
            try {
                throw new Exception("Api N not support this function");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 打开相册
     * @param context
     * @param result
     */
    public static void openAblum(Activity context,int result){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        context.startActivityForResult(intent, result);
    }

    /**
     * 打开相机
     * @param activity
     * @param imageName
     * @param result
     * @return
     */
    public static File openCamera(Activity activity,String imageName, int result){
        File file = null;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(FilePathUtils.hasSdcard()){
            file = new File(FilePathUtils.getEnvironmentPath(),imageName);
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        activity.startActivityForResult(intent,result);
        return file;
    }

    /**
     * 剪切图片
     * @param context
     * @param uri
     * @param image
     * @param result
     */
    public static void cropImage(Activity context,Uri uri,File image,int result){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(image));
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        context.startActivityForResult(intent, result);
    }

    public static void cropImage(Activity context,Uri uri,File image,int width,int height,int result){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(image));
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        context.startActivityForResult(intent, result);
    }

    /**
     * 剪切图片
     * @param context
     * @param uri
     * @param result
     */
    public static void cropImage(Activity context,Uri uri,int result){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        context.startActivityForResult(intent, result);
    }

    /**
     * 分享文本内容
     * @param context
     * @param title
     * @param text
     */
    public static void shareTextInfo(Context context,String title,String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent,title));
    }
}
