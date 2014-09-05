package com.continueing.photoco.reuse.etc;

import java.lang.reflect.Field;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

/* Application단에서 건드리면 폰트 타입등을 전체로 적용할 수 있다.  
     또한, */

public class PhotocoApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		PhotocoApplication.setDefaultFont(this, "DEFAULT", "BreeSerif_Reg.otf");
	}
	
	public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

	// 글자를 애플리케이션 전체에 적용하기 위해 사용한다.
    protected static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}