package com.example.im2.contract

interface ChatContract {

    //presenter，给谁发，发什么
    interface Presenter:BasePresenter{
        fun sendMessage(contact:String,message:String)
    }


    interface View{
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}

