package com.example.im2

import android.util.Log
import android.view.KeyEvent
import android.widget.TextView
import com.example.homeworkplatform.BaseActivity
import com.example.im2.contract.LoginContract
import com.example.im2.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.w3c.dom.Text

class LoginActivity:BaseActivity(),LoginContract.View {

    val presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        login_button.setOnClickListener {
            Log.d("tag","666")
            login()
            Log.d("tag","666")}
        password.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                Log.d("tag","666")
                login()
                Log.d("tag","666")
                return true
            }

        })
    }

    fun login(){
        val userNameString = account.text.toString()
        val pwdString = password.text.toString()
        Log.d("tag","888")
        presenter.login(userNameString,pwdString)
        Log.d("tag","presenter.login")
    }

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
        toast("登陆成功，即将进入main activity")
        startActivity<MainActivity>()
        toast("成功啦")
        finish()
    }

    /*登陆失败，隐藏进度条，弹出失败提示*/
    override fun onLoggedInFailed() {
        dissmissProgress()
        toast(R.string.login_failed)//anko库里的方法
    }
}