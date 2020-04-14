package com.example.homeworkplatform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    /*一些公共初始化操作，open子类可以复写，完成自己的初始化*/
    open fun init() {

    }

    /*抽象类，子类必须实现这个方法返回布局资源的id*/
    abstract fun getLayoutResId(): Int
}