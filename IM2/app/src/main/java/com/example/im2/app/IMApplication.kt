package com.example.im2.app

import android.app.Application
import android.util.Log
import cn.bmob.v3.Bmob
import com.example.im2.BuildConfig
import com.example.im2.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions

class IMApplication:Application() {

    companion object{
        lateinit var instance:IMApplication
    }

    val messageListener = object :EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果是在前台，则播放短的声音
            //如果在后台，则播放长的声音
        }
    }

    override fun onCreate() {
        Log.d("tag","开始")
        super.onCreate()

//环信sdk初始化
        EMClient.getInstance().init(applicationContext,EMOptions());
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源BuildConfig.DEBUG
        EMClient.getInstance().setDebugMode(false);
        Bmob.initialize(applicationContext,"c35ee4f69cd9e311605133f1fcf2264f")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

}