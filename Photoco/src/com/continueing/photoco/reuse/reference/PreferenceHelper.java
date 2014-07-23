package com.continueing.photoco.reuse.reference;

import android.content.Context;
import android.content.SharedPreferences;

public abstract  class PreferenceHelper {
    protected Context context;

    public abstract String getFileName();

    public PreferenceHelper(Context aContext) {
        context = aContext;
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences pref = context.getSharedPreferences(getFileName(), Context.MODE_PRIVATE);
        return pref.edit();
    }

    public String getString(String aParm, String aDefault)
    {
        return context.getSharedPreferences(getFileName(), Context.MODE_PRIVATE).getString(aParm, aDefault);
    }

    public int getInt(String aParm, int aDefault)
    {
        return context.getSharedPreferences(getFileName(), Context.MODE_PRIVATE).getInt(aParm, aDefault);
    }

    public boolean getBoolean(String aParm, boolean aDefault)
    {
        return context.getSharedPreferences(getFileName(), Context.MODE_PRIVATE).getBoolean(aParm, aDefault);
    }

    public void setInt(String aKey, int aValue)
    {
        getEditor().putInt(aKey, aValue).commit();
    }
    public void setString(String aKey, String aValue)
    {
        getEditor().putString(aKey, aValue).commit();
    }
    public void setBoolean(String aKey, boolean aValue)
    {
        getEditor().putBoolean(aKey, aValue).commit();
    }
}