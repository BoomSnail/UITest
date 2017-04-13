package com.xiaoshihua.thinkpad.uitest;

import android.graphics.drawable.Drawable;

/**
 * Created by ThinkPad on 2016/9/5.
 */
public class  UserInfo{
    public static final String ID = "_id";
    public static final String USERID = "UserID";
    public static final String TOKEN = "token";
    public static final String TOKENSECRET = "tokenSecret";
    public static final String USERNAME = "username";
    public static final String USERICON = "userName";

    private String id;
    private String userId;
    private String token;
    private String tokenScrect;
    private Drawable userIcon;
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenScrect() {
        return tokenScrect;
    }

    public void setTokenScrect(String tokenScrect) {
        this.tokenScrect = tokenScrect;
    }

    public Drawable getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(Drawable userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
