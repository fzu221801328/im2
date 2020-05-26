package com.example.im2.presenter

import com.example.im2.contract.ContactContract
import com.example.im2.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

class ContactPresenter(val view:ContactContract.View):ContactContract.Presenter {

    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            //如果失败会抛出异常
            try{
                //从环信服务器获得联系人列表
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer

                usernames.sortBy{it[0]}
                usernames.forEach {
                    val contactListItem = ContactListItem(it,it[0].toUpperCase())
                    contactListItems.add(contactListItem)
                }
                uiThread { view.onLoadContractsSuccess() }
            }catch (e:HyphenateException){
                uiThread { view.onLoadContractsFailed() }
            }
        }
    }
}