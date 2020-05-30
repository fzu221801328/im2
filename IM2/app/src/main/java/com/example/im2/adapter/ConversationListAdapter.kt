package com.example.im2.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im2.ui.widget.ConversationListItemView
import com.hyphenate.chat.EMConversation

//list
//6 想办法获得数据以后，创建了数据集合，设置到adapter里,,,再把它val成一个属性

class ConversationListAdapter(
    val context: Context,
    val conversations: MutableList<EMConversation>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //2定义好viewholder类后，在这里返回一个viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //3 给它参数，这个View就是我们刚刚自定义的条目
        //4 创建它要上下文，把上下文传入adapter里
       return ConversationListItemViewHolder(ConversationListItemView(context))
    }
    //5 暂时完成了adapter，可以去赋值了

    //7 设置真实的大小
    override fun getItemCount(): Int = conversations.size

    //8 条目的真实数据显示->条目的绑定
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder.itemView as ConversationListItemView
        //传入对应位置的数据
        conversationListItemView.bindView(conversations[position])

    }

    //1创建viewholder继承自RecyclerView.ViewHolder
    class ConversationListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}