package com.example.im2.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkplatform.BaseFragment
import com.example.im2.R
import com.example.im2.adapter.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*

class ConversationFragment:BaseFragment() {
    override fun getLayoutResId(): Int
        = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)

        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context)
        }
    }
}