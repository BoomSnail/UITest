package com.xiaoshihua.thinkpad.uitest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by ThinkPad on 2016/9/5.
 */
public class MySqlLite extends SQLiteOpenHelper {


    public static final String TB_NAME = "users";

    public MySqlLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS" +TB_NAME + "(" + UserInfo.ID +
                "integer primary key" +
                UserInfo.USERID + "varchar" +
                UserInfo.TOKEN + "varchar" +
                UserInfo.TOKENSECRET + "varchar" +
                UserInfo.USERNAME + "varchar" +
                UserInfo.USERICON + "blob" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TB_NAME);
        onCreate(db);
    }

    //更新列
    public void updateColumn(SQLiteDatabase db,String oldColumn,String newColumn,String typeColumn){
        db.execSQL("ALTER TABLE" + TB_NAME +
                    "CHANGE" + oldColumn + " " + newColumn + " " + typeColumn);
    }
}
