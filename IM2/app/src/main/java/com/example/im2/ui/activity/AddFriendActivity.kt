package com.example.im2.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import com.example.im2.adapter.AddFriendListAdapter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*

class AddFriendActivity: BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }
}