package com.example.im2.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im2.R
import com.example.im2.data.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

class ContactListItemView(context: Context?, attrs: AttributeSet?=null) :
    RelativeLayout(context, attrs) {

    //条目刷新
    fun bindView(contactListItem: ContactListItem) {
        firstLetter.text = contactListItem.firstLetter.toString()
        userName.text = contactListItem.userName

    }

    //要给他布局
    init{
        View.inflate(context, R.layout.view_contact_item,this)
    }
}
