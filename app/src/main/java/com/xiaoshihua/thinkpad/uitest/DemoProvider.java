package com.xiaoshihua.thinkpad.uitest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

/**
 *
 * Created by ThinkPad on 2016/9/4.
 */
public class DemoProvider extends ContentProvider {

    private static final int WORDS = 1;
    private static final int WORD = 2;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //注册两个Uri
        uriMatcher.addURI(Worlds.AUTHORITY,"words",WORDS);
        uriMatcher.addURI(Worlds.AUTHORITY,"word",WORD);
    }

    @Override
    public boolean onCreate() {
        String TABLE_NAME = "create table dict(_id INTEGER PRIMARY KEY AUTOINCREMENT,word varchar,detail varchar))";
        SQLiteDatabase sqLiteDatabase = this.getContext().openOrCreateDatabase("dabase.db", Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL(TABLE_NAME);

        ContentValues contentValues = new ContentValues();
        contentValues.put("word","test");
        contentValues.put("detail","测试");
        sqLiteDatabase.insert("dict","_id",contentValues);
        sqLiteDatabase.close();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = this.getContext().openOrCreateDatabase("database.db",Context.MODE_PRIVATE,null);
        switch (uriMatcher.match(uri)){
            case WORDS:
                //执行查询
                return database.query("dict",projection,selection,selectionArgs,null,null,sortOrder);
            //如果代表制定操作项
            case WORD:
                long id = ContentUris.parseId(uri);
                String whereClause = Worlds.World._ID + "=" + id;
                if (selection != null && !selection.equals("")) {
                    whereClause = whereClause + "and" + selection;
                }
                return database.query("dict",projection,whereClause,selectionArgs,null,null,sortOrder);
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case WORDS:
                return "vnd.android.cursor.dict/org.crazyit.dict";
            case WORD:
                return "vnd.android.cursor.item/org.crazyit.dict";
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = this.getContext().openOrCreateDatabase("database.db",Context.MODE_PRIVATE,null);
        switch (uriMatcher.match(uri)){
            case WORDS:
                long rowId = database.insert("dict",Worlds.World._ID,values);
                if (rowId > 0) {
                    Uri wordUri = ContentUris.withAppendedId(uri,rowId);
                    getContext().getContentResolver().notifyChange(wordUri,null);
                    return wordUri;
                }
                break;
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = this.getContext().openOrCreateDatabase("database.db",Context.MODE_PRIVATE,null);
        //记录删除的记录数
        int num = 0;
        switch (uriMatcher.match(uri)){
            case WORDS:
                num = database.delete("dict",selection,selectionArgs);
                break;
            case WORD:
                //解析删除的id
                long id = ContentUris.parseId(uri);
                String whereClause = Worlds.World._ID + "=" + id;
                if (selection != null && !selection.equals("")) {
                    whereClause = whereClause + "and" + selection;
                }

                num = database.delete("dict",whereClause,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("未知uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);

        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = this.getContext().openOrCreateDatabase("database.db",Context.MODE_PRIVATE,null);
        //记录修改的记录数
        int num = 0;
        switch (uriMatcher.match(uri)){
            case WORDS:
                num = database.update("dict",values,selection,selectionArgs);
                break;
            case WORD:
                //解析出想修改记录的id
                long id = ContentUris.parseId(uri);
                String whereChause = Worlds.World._ID + "=" + id;
                //如果原来的where字句存在则拼接
                if (selection != null && !selection.equals("")) {
                    whereChause = whereChause + "and" + selection;
                }

                database.update("dict",values,whereChause,selectionArgs);
                break;
            default:
                throw  new IllegalArgumentException("未知uri" + uri);
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri,null);
        return num;
    }
}
