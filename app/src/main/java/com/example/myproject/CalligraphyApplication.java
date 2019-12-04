package com.example.myproject;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class CalligraphyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("FangZhengFangSongJianTi-1.ttf")//如果没有这种字体，则使用默认字体，不会崩溃
                // .setDefaultFontPath("fonts/STLITI.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
