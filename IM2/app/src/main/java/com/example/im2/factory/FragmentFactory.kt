package com.example.im2.factory

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.im2.R
import com.example.im2.ui.activity.MainActivity
import com.example.im2.ui.fragment.ContactFragment
import com.example.im2.ui.fragment.ConversationFragment
import com.example.im2.ui.fragment.DynamicFragment


//单例模式私有构造方法
class FragmentFactory private constructor()
{
    val conversation by lazy{
        ConversationFragment()
    }

    val contact by lazy{
        ContactFragment()
    }

    val dynamic by lazy {
        DynamicFragment()
    }

    companion object{
        val instance = FragmentFactory()
    }

    /*传入tabId，返回相应的Fragment对象*/
    fun getFragment(tabId:Int): Fragment?
    {
        when(tabId){
            R.id.tab_conversation -> return conversation
            R.id.tab_contacts -> return contact
            R.id.tab_dynamic -> return dynamic
        }
        return null
    }
}