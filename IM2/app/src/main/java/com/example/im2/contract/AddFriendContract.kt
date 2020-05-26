package com.example.im2.contract

interface AddFriendContract {

    interface  Presenter:BasePresenter{
        fun search(key:String)
    }

    interface View{
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}