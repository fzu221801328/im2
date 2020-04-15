package com.example.im2.presenter

import com.example.im2.contract.SplashContract
import com.hyphenate.chat.EMClient

/*业务逻辑和通知view层*/
class SplashPresenter(val view:SplashContract.View): SplashContract.Presenter {

    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn()
        else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean
       = EMClient.getInstance().isConnected &&EMClient.getInstance().isLoggedInBefore





}