package com.continueing.photoco.reuse.network;

import java.util.List;

import org.apache.http.cookie.Cookie;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class HttpRequester {
    private static final String BASE_URL = "http://photocoapi-env-x2ezvferc7.elasticbeanstalk.com";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler, Context aContext) {
    	request(url, params, responseHandler, aContext, true);
    }

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler, Context aContext) {
    	request(url, params, responseHandler, aContext, false);
    }
    
    public static void request(String url, RequestParams params, JsonHttpResponseHandler responseHandler, Context aContext, boolean anIsPost)
    {
    	Log.i("request", "Url: "+url);
        Log.i("request", "Parms: " + params.toString());
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(aContext);
        client.setCookieStore(persistentCookieStore);
        
        if(anIsPost)
        	client.post(getAbsoluteUrl(url), params, responseHandler);
        else
        	client.get(getAbsoluteUrl(url), params, responseHandler);
        
        List<Cookie> cookieList =  persistentCookieStore.getCookies();
        Log.i("cookie count", cookieList.size()+"");
        for( Cookie aCookie : cookieList)
        {
            Log.i("name", aCookie.getName());
            Log.i("value", aCookie.getValue());
        }
    }
    
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
    
    // 처리를 위해 공통적인 규약을 준것이다.
    public static interface NetworkResponseListener
    {
        public void onSuccess(JSONObject jsonObject);
        public void onFail(JSONObject jsonObject, int errorCode);
    }
}