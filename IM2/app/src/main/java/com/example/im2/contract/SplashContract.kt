package com.example.im2.contract

interface SplashContract {
    //mvp协议
    interface Presenter:BasePresenter
    {
        /*检查登录状态*/
        fun checkLoginStatus()
    }

    interface View{
        /*没有登陆的ui处理*/
        fun onNotLoggedIn()
        /*已经登陆的ui处理*/
        fun onLoggedIn()

    }
}