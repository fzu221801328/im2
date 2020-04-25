package com.example.im2.contract

interface RegisterContract {

    interface Presenter:BasePresenter{
        fun register(userName:String,password:String,conformPassword:String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onConformPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
    }
}