package com.example.im2.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*

class ChatActivity:BaseActivity() {

    val TAG = "ChatActivity"

    override fun getLayoutResId(): Int
        = R.layout.activity_chat

    override fun init() {

        Log.d(TAG,"到达ChatActivity")
        super.init()
        initHeader()
        Log.d(TAG,"到达initHeader")
        initEditText()
        Log.d(TAG,"initEditText")
    }

    private fun initEditText() {
        edit.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //输入框里有字,才能发
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun initHeader()
    {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户名
        val username = intent.getStringExtra("username")
        Log.d(TAG,"获取聊天的用户名$username")
        val titleString = String.format(getString(R.string.chat_title),username)
        headerTitle.text = titleString
    }
}