package com.example.im2.presenter

import com.example.im2.adapter.EMCallBackAdapter
import com.example.im2.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

class ChatPresenter(val view:ChatContract.View):ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contact: String, message: String) {
        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message,contact)
        emMessage.setMessageStatusCallback(object:EMCallBackAdapter(){
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMessageFailed() }
            }
        })
        messages.add(emMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)


    }
}