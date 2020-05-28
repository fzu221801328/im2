package com.example.im2.presenter

import com.example.im2.contract.ChatContract

class ChatPresenter(val view:ChatContract.View):ChatContract.Presenter {

    override fun sendMessage(contact: String, message: String) {

    }
}