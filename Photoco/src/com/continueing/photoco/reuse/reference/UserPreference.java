package com.continueing.photoco.reuse.reference;

import android.content.Context;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class UserPreference extends PreferenceHelper {
    public static final String FILE_NAME ="PHOTOCO_USER";
    public static final String KEY_USERNAME ="USERNAME";
    public static final String KEY_PASSWORD ="PASSWORD";
    public static final String KEY_IS_LOGIN ="IS_LOGIN";

    public UserPreference(Context aContext) {
        super(aContext);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    public void setName(String anEmail) {
        setString(KEY_USERNAME, anEmail);
    }

    public void setPassword(String aPassword) {
        setString(KEY_PASSWORD, aPassword);
    }
    
    public void login(String anEmail, String aPassword) {
    	setName(anEmail);
        setPassword(aPassword);
        setBoolean(KEY_IS_LOGIN, true);
    }
    
    public boolean isLoggedIn() {
        return getBoolean(KEY_IS_LOGIN, false);
    }
}