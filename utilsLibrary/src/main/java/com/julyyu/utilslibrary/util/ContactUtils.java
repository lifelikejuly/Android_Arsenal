package com.julyyu.utilslibrary.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Telephony;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julyyu on 2017/6/22.
 * 通讯录工具
 */

public class ContactUtils {

    /**
     * 添加新联系人
     * @param context
     * @param name
     * @param phone
     * @param tel
     * @param mail
     * @param company
     */
    public static void addNewContactUser(Context context,String name, String phone, String tel, String mail, String company){

        ContentValues[] contentValuesList = new ContentValues[6];
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = context.getContentResolver();
        Uri contactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI,contentValues);
        Long contactId = ContentUris.parseId(contactUri);
        contentValuesList[0] = contentValues;

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(ContactsContract.Data.RAW_CONTACT_ID,contactId);
        contentValues1.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contentValues1.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
//        contentResolver.insert(ContactsContract.Data.CONTENT_URI,contentValues);
        contentValuesList[1] = contentValues1;

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(ContactsContract.Data.RAW_CONTACT_ID,contactId);
        contentValues2.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        contentValues2.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phone);
        contentValues2.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
//        contentResolver.insert(ContactsContract.Data.CONTENT_URI,contentValues);
        contentValuesList[2] = (contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
        contentValues3.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        contentValues3.put(ContactsContract.CommonDataKinds.Email.DATA, mail);
        contentValues3.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        contentValuesList[3] = (contentValues3);

        ContentValues contentValues4 = new ContentValues();
        contentValues4.put(ContactsContract.Data.RAW_CONTACT_ID,contactId);
        contentValues4.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        contentValues4.put(ContactsContract.CommonDataKinds.Phone.NUMBER,tel);
        contentValues4.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);
//        contentResolver.insert(ContactsContract.Data.CONTENT_URI,contentValues);
        contentValuesList[4] = (contentValues4);

        ContentValues contentValues5 = new ContentValues();
        contentValues5.put(ContactsContract.Data.RAW_CONTACT_ID,contactId);
        contentValues5.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE);
        contentValues5.put(ContactsContract.CommonDataKinds.Organization.LABEL, name);
        contentValues5.put(ContactsContract.CommonDataKinds.Organization.COMPANY, company);
        contentValues5.put(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK);
//        contentResolver.insert(ContactsContract.Data.CONTENT_URI,contentValues);
        contentValuesList[5] = (contentValues5);

        contentResolver.bulkInsert(ContactsContract.Data.CONTENT_URI,contentValuesList);

    }

    /**
     * 模拟插入短信 必需添加权限<uses-permission android:name="android.permission.WRITE_SMS"/>
     * @param context
     * @param address
     * @param type
     * @param date
     * @param body
     */
    public static void insertFakeMessage(Context context,String address,int type,long date,String body) {
//        ContentResolver resolver = context.getContentResolver();
//        Uri             uri      = Uri.parse("content://sms/");
//        ContentValues   values   = new ContentValues();
//        values.put("address", address);
//        values.put("type", 1);
//        values.put("date", date);
//        values.put("body", body);
//        resolver.insert(uri, values);

        //Put content values
        ContentValues values = new ContentValues();
        values.put(Telephony.Sms.ADDRESS, address);
        values.put(Telephony.Sms.DATE, System.currentTimeMillis());
        values.put(Telephony.Sms.BODY, body);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            context.getContentResolver().insert(Telephony.Sms.Sent.CONTENT_URI, values);
        }
        else {
            context.getContentResolver().insert(Uri.parse("content://sms/sent"), values);
        }
    }

}
