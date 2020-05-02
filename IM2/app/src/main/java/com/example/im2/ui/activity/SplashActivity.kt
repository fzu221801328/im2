package com.example.im2.ui.activity

import android.os.Handler
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import com.example.im2.contract.SplashContract
import com.example.im2.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity:BaseActivity(),SplashContract.View{

    val presenter = SplashPresenter(this)

    //用来定义常量
    companion object{
        val DELAY = 2000L
    }

    //用来延时
    val handler by lazy {//用到时初始化
        Handler()
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun getLayoutResId(): Int
       = R.layout.splash


    /*没登陆跳转到登陆界面,延时2s*/
    override fun onNotLoggedIn() {
        handler.postDelayed(object :Runnable{
            override fun run() {
                startActivity<LoginActivity>()
                finish()
            }
        }, DELAY)

    }

    /*登陆了跳转到首页*/
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }
}