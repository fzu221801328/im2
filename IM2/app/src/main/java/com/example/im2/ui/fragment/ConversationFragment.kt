package com.example.im2.ui.fragment

import com.example.homeworkplatform.BaseFragment
import com.example.im2.R
import kotlinx.android.synthetic.main.header.*

class ConversationFragment:BaseFragment() {
    override fun getLayoutResId(): Int
        = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)
    }
}