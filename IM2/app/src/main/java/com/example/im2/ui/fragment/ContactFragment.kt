package com.example.im2.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkplatform.BaseFragment
import com.example.im2.R
import com.example.im2.adapter.ContactListAdapter
import com.example.im2.contract.ContactContract
import com.example.im2.presenter.ContactPresenter
import com.example.im2.ui.activity.AddFriendActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ContactFragment:BaseFragment(),ContactContract.View {
    override fun getLayoutResId(): Int
        = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)

    override fun init() {
        super.init()
        /*设置标题*/
        headerTitle.text = getString(R.string.contact)
        /*设置+号可见*/
        add.visibility = View.VISIBLE
        add.setOnClickListener { context!!.startActivity<AddFriendActivity>() }
        /*设置下拉刷新颜色不然是黑色*/
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context,presenter.contactListItems)//数据集合传进去
        }

        presenter.loadContacts()
    }

    override fun onLoadContractsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        //recyclerview列表要刷新
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLoadContractsFailed() {
        swipeRefreshLayout.isRefreshing = false
        context!!.toast(R.string.load_contacts_failed)
    }
}