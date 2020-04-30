package com.example.im2.presenter

import com.example.im2.contract.RegisterContract
import com.example.im2.extentions.isValidPassword
import com.example.im2.extentions.isValidUserName

class RegisterPresenter(val view:RegisterContract.View):RegisterContract.Presenter {
    override fun register(userName: String, password: String, confirmPassword: String) {
        if(userName.isValidUserName()){
            if(password.isValidPassword()){
                if (password == confirmPassword){
                    view.onStartRegister()
                    //开始注册
                }else view.onConfirmPasswordError()
            }else view.onPasswordError()
        }else view.onUserNameError()
    }
}