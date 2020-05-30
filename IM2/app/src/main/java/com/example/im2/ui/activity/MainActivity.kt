package com.example.im2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import com.example.im2.adapter.EMMessageListenerAdapter
import com.example.im2.factory.FragmentFactory
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dynamic.*
import org.jetbrains.anko.toast

//我不知道具体是怎样，看起来是，数据库+服务器端，应该不用别的知识
class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int
        = R.layout.activity_main

    val messageListener = object:EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread { updateBottomBarUnReadCount() }
        }
    }

    override fun init() {
        super.init()
        //要点击切换fragment，在framlayout里完成
        bottom_bar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    override fun onResume() {
        super.onResume()
        updateBottomBarUnReadCount()
    }

    private fun updateBottomBarUnReadCount()
    {
        //初始化bottombar未读消息的个数
        val tab = bottom_bar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}
