package com.example.im2

import com.example.homeworkplatform.BaseActivity
import com.example.im2.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity:BaseActivity(),LoginContract.View {
    override fun getLayoutResId(): Int
       = R.layout.activity_login

    override fun onUserNameError() {
        account.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.user_name_error)
    }

    /*开始登录，出现进度条*/
    override fun onStartLogin() {
        showProgress(getString(R.string.logging))
    }

    /*登陆成功，隐藏进度条，进入主界面，退出登录activity*/
    override fun onLoggedInSuccess() {
        dissmissProgress()
        startActivity<MainActivity>()
        finish()
    }

    /*登陆失败，隐藏进度条，弹出失败提示*/
    override fun onLoggedInFailed() {
        dissmissProgress()
        toast(R.string.login_failed)//anko库里的方法
    }
}