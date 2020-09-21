package com.logistics.app;

import android.content.Context;
import android.util.DisplayMetrics;
import com.logistics.app.base.BaseApp;

public class MyApp extends BaseApp {
    public static final String TAG = "MyApp";
    public static int H,W;
    static Context context;
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;
        getScreen(this);
    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H=dm.heightPixels;
        W=dm.widthPixels;
    }


    public static Context getContext() {
        return context;
    }


}
