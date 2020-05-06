package com.example.im2.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkplatform.BaseFragment
import com.example.im2.R
import com.example.im2.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

class ContactFragment:BaseFragment() {
    override fun getLayoutResId(): Int
        = R.layout.fragment_contacts

    override fun init() {
        super.init()
        /*设置标题*/
        headerTitle.text = getString(R.string.contact)
        /*设置+号可见*/
        add.visibility = View.VISIBLE
        /*设置下拉刷新颜色不然是黑色*/
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }

    }
}