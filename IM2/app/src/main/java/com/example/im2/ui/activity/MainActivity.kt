package com.example.im2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import com.example.im2.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
//我不知道具体是怎样，看起来是，数据库+服务器端，应该不用别的知识
class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int
        = R.layout.activity_main

    override fun init() {
        super.init()
        //要点击切换fragment，在framlayout里完成
        bottom_bar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()
        }
    }
}
