package com.xiaoshihua.thinkpad.uitest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ThinkPad on 2016/9/2.
 */
public class DemoSqlite extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "create table if not exists list_file(_id integer primary key" +
            " autoincrement not null,data TEXT,city TEXT) ";

    public DemoSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
