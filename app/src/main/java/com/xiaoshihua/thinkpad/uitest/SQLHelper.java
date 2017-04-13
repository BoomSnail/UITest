package com.xiaoshihua.thinkpad.uitest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2016/9/5.
 */
public class SQLHelper {
    //数据库名称
        private static  String DB_NAME = "mysinaweibo.db";
    private static int DB_VERSION = 2;
    private SQLiteDatabase db;
    private MySqlLite mySqlLite;

    public SQLHelper(Context context){
        mySqlLite = new MySqlLite(context,DB_NAME,null,DB_VERSION);
        db = mySqlLite.getWritableDatabase();
    }

    public void close(){
        db.close();
        mySqlLite.close();
    }

    public List<UserInfo> getUserInfos(boolean isSimple){
        List<UserInfo> list = new ArrayList<>();
        Cursor cursor = db.query(mySqlLite.TB_NAME,null,null,null,null,null,UserInfo.ID + "DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast() && cursor.getString(1) != null){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(cursor.getString(0));
            userInfo.setUserId(cursor.getString(1));
            userInfo.setToken(cursor.getString(2));
            userInfo.setTokenScrect(cursor.getString(3));
            if (!isSimple) {
                userInfo.setUserName(cursor.getString(4));
                ByteArrayInputStream stream = new ByteArrayInputStream(cursor.getBlob(5));
                Drawable icon = Drawable.createFromStream(stream,"image");
                userInfo.setUserIcon(icon);
            }
            list.add(userInfo);
        }
        cursor.close();
        return list;
    }

    //判断表中是否拥有userId
    public Boolean haveUserInfo(String userId){
        Boolean b = false;
        Cursor cursor = db.query(MySqlLite.TB_NAME,null,UserInfo.USERID + "=" + userId,null,null,null,null);
        b = cursor.moveToFirst();
        cursor.close();
        return b;
    }
    //更新
    public int  updateUserInfo(String userName, Bitmap userIcon,String userId){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfo.USERNAME,userName);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        userIcon.compress(Bitmap.CompressFormat.PNG,100,os);
        contentValues.put(UserInfo.USERICON,os.toByteArray());
        int id = db.update(MySqlLite.TB_NAME,contentValues,UserInfo.USERID + "=" + userId,null);
        db.update(MySqlLite.TB_NAME,contentValues,UserInfo.USERNAME + "=" + userName,null);
        return id;
    }

    //保存个人信息
    public Long saveUserInfo(UserInfo userInfo){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfo.USERID,userInfo.getUserId());
        contentValues.put(UserInfo.TOKEN,userInfo.getToken());
        contentValues.put(UserInfo.TOKENSECRET,userInfo.getTokenScrect());
        Long id = db.insert(MySqlLite.TB_NAME,UserInfo.ID,contentValues);
        return id;
    }
    //删除
    public int  delUserInfo(String userId){
        int id = db.delete(MySqlLite.TB_NAME,UserInfo.USERID + "=" + userId,null);
        return id;
    }
}
