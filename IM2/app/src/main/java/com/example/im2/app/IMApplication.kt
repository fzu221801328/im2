package com.example.im2.app

import android.app.Application
import com.example.im2.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

class IMApplication:Application() {
    override fun onCreate() {
        super.onCreate()

//环信sdk初始化
        EMClient.getInstance().init(applicationContext,EMOptions());
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
    }
}