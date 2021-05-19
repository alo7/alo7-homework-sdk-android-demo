package com.alo7.android.homeworksdk;

import android.app.Application;

import com.alo7.android.homework.Alo7HomeworkSDK;

/**
 * @author zhangpan
 * @date 2021/4/23
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Alo7HomeworkSDK.initialize(this, BuildConfig.DEBUG);
    }
}
