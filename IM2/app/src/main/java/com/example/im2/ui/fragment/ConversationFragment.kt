package com.example.im2.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkplatform.BaseFragment
import com.example.im2.R
import com.example.im2.adapter.ConversationListAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConversationFragment:BaseFragment() {

    val conversations = mutableListOf<EMConversation>()//环信的conversation类啊

    override fun getLayoutResId(): Int
        = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)

        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            //6 想办法获得数据以后，创建了数据集合，设置到adapter里
            adapter = ConversationListAdapter(context,conversations)
        }

        loadConversations()
    }

    private fun loadConversations() {
        doAsync {
            //怎么转到我们能用的conversation，应该可以通过这个获得参数->加到会话数据列表里
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)//只要数据部分
            //数据加载出来了，可以通知rv刷新了
            //所以adapter里必须有传进来这个会话数据列表
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }

        }
}
}