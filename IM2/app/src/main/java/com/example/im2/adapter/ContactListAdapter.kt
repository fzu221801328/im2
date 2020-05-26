package com.example.im2.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im2.data.ContactListItem
import com.example.im2.ui.widget.ContactListItemView

/*要使用上下文去初始化条目的布局*/
class ContactListAdapter(
    val context: Context,
    val contactListItems: MutableList<ContactListItem>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int {
        return contactListItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
    }

    class ContactListItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
}