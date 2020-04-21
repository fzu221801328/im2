package com.example.im2.presenter

import android.util.Log
import com.example.im2.adapter.EMCallBackAdapter
import com.example.im2.contract.LoginContract
import com.example.im2.extentions.isValidPassword
import com.example.im2.extentions.isValidUserName
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

class LoginPresenter(val view:LoginContract.View):LoginContract.Presenter {

    /*用户输入校验
    1、用户名长度在3-20位，首字母是英文字母，其他字符除了是
    一ing文字母，还可以是下划线和数字
    2、密码必须是3-20位数字
    使用正则表达式
    * */

    override fun login(userName: String, pwd: String) {
        if (userName.isValidUserName()){
            //用户名合法，继续校验密码
            if (pwd.isValidPassword()){
                //密码合法，开始登陆
                view.onStartLogin()
                //登录到环信
                Log.d("tag","userName"+userName+"pwd"+pwd)
                loginEaseMob(userName,pwd)
                Log.d("tag","222")
            }
            else//密码不合法
            view.onPasswordError()
        }
        else//通知view层说不合法
        view.onUserNameError()
        Log.d("tag","999")
    }

//在这里出错了
    private fun loginEaseMob(userName: String, pwd: String) {
        EMClient.getInstance().login(userName,pwd,object:EMCallBack{
            //在子线程回调，要在主线程通知view层

            override fun onSuccess() {
                Log.d("tag","333")
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                Log.d("tag","444")
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onProgress(p0: Int, p1: String?) {
                //super.onProgress(p0, p1)
                Log.d("tag","101010")
            }

            override fun onError(p0: Int, p1: String?) {
                Log.d("tag","555")
                uiThread { view.onLoggedInFailed() }
            }

        })
    }
}