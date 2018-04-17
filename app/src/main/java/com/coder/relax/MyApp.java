package com.coder.relax;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by TUS on 2018/4/16.
 */

public class MyApp extends Application {
    private static MyApp mInstance;
    public static MyApp getInstance() {
        if (mInstance == null) {
            synchronized (MyApp.class) {
                if (mInstance == null) {
                    mInstance = new MyApp();
                }
            }
        }
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        /**Bugly*/
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setAppChannel("google play");
        CrashReport.initCrashReport(getApplicationContext(), "2174eb55bf", false,strategy);
        CrashReport.setIsDevelopmentDevice(this, false);
        MobileAds.initialize(this,"ca-app-pub-4727610544131155~6924161613");


    }
}
