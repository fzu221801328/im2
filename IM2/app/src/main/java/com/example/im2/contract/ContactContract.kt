package com.example.im2.contract

interface ContactContract {

    interface Presenter:BasePresenter{
        fun loadContacts()
    }

    interface View{
        fun onLoadContractsSuccess()
        fun onLoadContractsFailed()
    }
}