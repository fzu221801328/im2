package com.example.im2.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import cn.bmob.v3.Bmob
import com.example.im2.BuildConfig
import com.example.im2.R
import com.example.im2.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions

class IMApplication:Application() {

    companion object{
        lateinit var instance:IMApplication
    }
    
    //放音乐
    val soundPool = SoundPool(2,AudioManager.STREAM_MUSIC,0)

    //返回值是音乐的id
    val duan by lazy{
        soundPool.load(instance, R.raw.duan,0)
    }

    val yulu by lazy{
        soundPool.load(instance,R.raw.yulu,0)
    }

    val messageListener = object :EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            Log.d("tag","isForeground()="+isForeground())
            //如果是在前台，则播放短的声音
            if(isForeground())
            {
                soundPool.play(duan,1f,1f,0,0,1f)
            }
            else//如果在后台，则播放长的声音
            {
                soundPool.play(yulu,1f,1f,0,0,1f)
            }
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

    private fun isForeground():Boolean{
        Log.d("tag","isForeground")
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for(runningAppProgress in activityManager.runningAppProcesses){
            if (runningAppProgress.processName==packageName){
                Log.d("tag","look for 进程")
                //找到了app的进程
                return runningAppProgress.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

}