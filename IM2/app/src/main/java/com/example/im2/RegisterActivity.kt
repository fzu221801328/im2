package com.example.im2

import com.example.homeworkplatform.BaseActivity
import com.example.im2.contract.RegisterContract
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity:BaseActivity(),RegisterContract.View {
    override fun getLayoutResId(): Int
        = R.layout.activity_register

    override fun onUserNameError() {
        account.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConformPasswordError() {
        confirm_password.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dissmissProgress()
        finish()
    }

    override fun onRegisterFailed() {
        dissmissProgress()
        toast(R.string.register_failed)
    }
}