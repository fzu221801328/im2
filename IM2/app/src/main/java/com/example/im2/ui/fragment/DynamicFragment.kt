package com.example.im2.ui.fragment

import com.example.homeworkplatform.BaseFragment
import com.example.im2.R
import com.example.im2.adapter.EMCallBackAdapter
import com.example.im2.ui.activity.LoginActivity
import com.example.im2.ui.activity.MainActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DynamicFragment:BaseFragment() {
    override fun getLayoutResId(): Int
        = R.layout.fragment_dynamic

    override fun init() {
        super.init()

        var logoutString = String.format(getString(R.string.logout), EMClient.getInstance().currentUser)
        //不要放到MainActivity里
        logout_button.text = logoutString

        logout_button.setOnClickListener {
            logout()
        }
    }

    /*从环信服务器退出登录*/
    fun logout() {
        EMClient.getInstance().logout(true,object:EMCallBackAdapter(){

            override fun onSuccess() {
                context!!.runOnUiThread {
                    toast(R.string.logout_success)
                }
                context?.startActivity<LoginActivity>()
                activity!!.finish()
            }

            override fun onError(p0: Int, p1: String?) {
                //切换到主线程
                context!!.runOnUiThread {
                    toast(R.string.logout_failed)
                }
            }
        })
    }
}