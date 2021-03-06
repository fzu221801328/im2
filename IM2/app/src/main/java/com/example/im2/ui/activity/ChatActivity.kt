package com.example.im2.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import com.example.im2.adapter.EMMessageListenerAdapter
import com.example.im2.adapter.MessageListAdapter
import com.example.im2.contract.ChatContract
import com.example.im2.presenter.ChatPresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity:BaseActivity(),ChatContract.View {

    val TAG = "ChatActivity"

    val presenter = ChatPresenter(this)

    lateinit var username:String

    override fun getLayoutResId(): Int
        = R.layout.activity_chat

    //收消息的监听器
    val messageListener = object: EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username,p0)
            runOnUiThread {
                recyclerView.adapter?.notifyDataSetChanged()
                scrollToBottom()
            }
        }

    }

    override fun init() {

        Log.d(TAG,"到达ChatActivity")
        super.init()
        initHeader()
        Log.d(TAG,"到达initHeader")
        initEditText()
        Log.d(TAG,"initEditText")

        initRecyclerView()
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        //发送消息
        send.setOnClickListener { send() }

        presenter.loadMessages(username)
    }

    private fun initRecyclerView() {
        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            Log.d(TAG,"开始设置adapter")
            adapter = MessageListAdapter(context,presenter.messages)

            addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    //当rv是一个空闲的状态
                    //检查是否滑动到顶部，要加载更多数据
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){//空闲
                        //如果第一个可见条目的位置是0，为滑倒顶部
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0){
                            //加载更多数据
                            presenter.loadMoreMessages(username)
                        }

                    }
                }
            })
        }
    }

    private fun send()
    {
        hideSoftKeyboard()
        val message = edit.text.toString()
        presenter.sendMessage(username,message)
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

        edit.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                send()
                return true
            }

        })
    }

    private fun initHeader()
    {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户名
        username = intent.getStringExtra("username")
        Log.d(TAG,"获取聊天的用户名$username")
        val titleString = String.format(getString(R.string.chat_title),username)
        headerTitle.text = titleString
    }

    override fun onStartSendMessage() {
        //通知recyclerview刷新列表，显示一条正在发送的消息
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_success)
        //清空编辑框
        edit.text.clear()
        //细节：滚动到底部
        scrollToBottom()
    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageFailed() {
        //加载变成出错
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_failed)
    }

    override fun onMessageLoaded() {
        recyclerView.adapter?.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size: Int) {
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}