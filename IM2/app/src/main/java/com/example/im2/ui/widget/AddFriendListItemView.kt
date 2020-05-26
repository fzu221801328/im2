package com.example.im2.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im2.R


class AddFriendListItemView(context: Context?, attrs:AttributeSet?=null): RelativeLayout(context,attrs) {

    init{
        View.inflate(context, R.layout.view_add_friend_item,this)
    }
}