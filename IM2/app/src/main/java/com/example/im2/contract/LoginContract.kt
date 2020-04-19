package com.example.im2.contract

interface LoginContract{

    /*要实现登录功能*/
    interface Presenter:BasePresenter{

        fun login(userName:String,pwd:String)
    }

    /*输入校验，登录*/
    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}