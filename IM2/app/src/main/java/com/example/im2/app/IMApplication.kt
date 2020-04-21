package com.example.im2.app

import android.app.Application
import android.util.Log
import com.example.im2.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

class IMApplication:Application() {
    override fun onCreate() {
        Log.d("tag","开始")
        super.onCreate()

//环信sdk初始化
        EMClient.getInstance().init(applicationContext,EMOptions());
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源BuildConfig.DEBUG
        EMClient.getInstance().setDebugMode(false);
    }
}