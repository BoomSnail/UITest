package com.xiaoshihua.thinkpad.uitest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Created by ThinkPad on 2016/9/3.
 */
public class JsonTools {

    //往json放入数据
    public static String getJsonString(String key,Object value){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    //得到一个json对象
    public static JSONObject getJsonObject(String key,Object value) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key,value);
        return jsonObject;
    }
}
