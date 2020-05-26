package com.example.im2.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkplatform.BaseActivity
import com.example.im2.R
import com.example.im2.adapter.AddFriendListAdapter
import com.example.im2.contract.AddFriendContract
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity: BaseActivity(),AddFriendContract.View {
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

    override fun onSearchSuccess() {
        dissmissProgress()
        toast(R.string.search_success)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dissmissProgress()
        toast(R.string.search_failed)
    }
}