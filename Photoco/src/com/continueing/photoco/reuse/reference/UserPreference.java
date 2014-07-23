package com.continueing.photoco.reuse.reference;

import android.content.Context;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class UserPreference extends PreferenceHelper {
    private static final String FILE_NAME ="PHOTOCO_USER";
    private static final String KEY_EMAIL ="EMAIL";
    private static final String KEY_PASSWORD ="PASSWORD";
    private static final String KEY_IS_LOGIN ="IS_LOGIN";

    public UserPreference(Context aContext) {
        super(aContext);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    public void setEmail(String anEmail)
    {
        setString(KEY_EMAIL, anEmail);
    }

    public void setPassword(String aPassword)
    {
        setString(KEY_PASSWORD, aPassword);
    }
    
    public void login(String anEmail, String aPassword)
    {
        setEmail(anEmail);
        setPassword(aPassword);
        setBoolean(KEY_IS_LOGIN, true);
    }

    public String getEmail()
    {
        return getString(KEY_EMAIL, null);
    }

    public String getPassword()
    {
        return getString(KEY_PASSWORD, null);
    }
    
    public boolean isLoggedIn()
    {
        return getBoolean(KEY_IS_LOGIN, false);
    }
}