package com.example.im2.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im2.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*

import java.util.*


class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null)
    : RelativeLayout(context,attrs) {
    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateMessage(emMessage)
        updateTimestamp(emMessage,showTimestamp)
    }

    private fun updateTimestamp(
        emMessage: EMMessage,
        showTimestamp: Boolean
    ) {
        if(showTimestamp){
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else timestamp.visibility = View.GONE

    }

    private fun updateMessage(emMessage: EMMessage) {
        if(emMessage.type == EMMessage.Type.TXT){
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        }else{
            receiveMessage.text = context.getString(R.string.no_text_message)
        }

    }

    //条目
    init{
        View.inflate(context,R.layout.view_receive_message_item,this)
    }
}