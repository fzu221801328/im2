package com.example.im2

import android.view.KeyEvent
import android.widget.TextView
import com.example.homeworkplatform.BaseActivity
import com.example.im2.contract.RegisterContract
import com.example.im2.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.account
import kotlinx.android.synthetic.main.activity_register.password
import org.jetbrains.anko.toast

class RegisterActivity:BaseActivity(),RegisterContract.View {
    override fun getLayoutResId(): Int
        = R.layout.activity_register

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register_button.setOnClickListener { register() }
        //软键盘也来一个监听器
        confirm_password.setOnEditorActionListener(object:TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                register()
                return true
            }
        })
    }

    fun register(){
        //隐藏软键盘
        hideSoftKeyboard()
        val userNameString = account.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val confirmPassword = confirm_password.text.trim().toString()
        presenter.register(userNameString,passwordString,confirmPassword)
    }

    override fun onUserNameError() {
        account.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirm_password.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dissmissProgress()
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFailed() {
        dissmissProgress()
        toast(R.string.register_failed)
    }
}