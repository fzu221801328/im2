package com.example.im2.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im2.R

class ContactListItemView(context: Context?, attrs: AttributeSet?=null) :
    RelativeLayout(context, attrs) {
    //要给他布局
    init{
        View.inflate(context, R.layout.view_contact_item,this)
    }
}
