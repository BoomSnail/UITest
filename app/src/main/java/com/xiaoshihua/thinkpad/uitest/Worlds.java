package com.xiaoshihua.thinkpad.uitest;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ThinkPad on 2016/9/12.
 */
public class Worlds {
    public static final String AUTHORITY = "com.xiaoshihua.thinkpad.uitest.DemoProvider";

    public final static class World implements BaseColumns {
        public final static String _ID = "_id";
        public final static String WORD = "word";
        public final static String DETAIL = "detail";

        public final static Uri CONTENT_URI1 = Uri.parse("content://" + AUTHORITY + "/words");
        public final static Uri CONTENT_URI2 = Uri.parse("content://" + AUTHORITY + "/word");
    }
}
