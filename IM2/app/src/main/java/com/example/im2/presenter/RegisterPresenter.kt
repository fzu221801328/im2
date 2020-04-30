package com.example.im2.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
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
                    registerBmob(userName,password)
                }else view.onConfirmPasswordError()
            }else view.onPasswordError()
        }else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        /**
         * bmob注册方法
         */
        var user = BmobUser()
        user.username = userName
        user.setPassword(password)
        user.signUp(object : SaveListener<BmobUser>() {
            override fun done(currentUser: BmobUser?, ex: BmobException?) {
                if (ex == null) {
                   //注册成功
                    //注册到环信
                } else {
                    //注册失败
                    view.onRegisterFailed()
                }
            }
        })

        }
    }