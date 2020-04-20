package com.example.im2.extentions

fun String.isValidUserName():Boolean
{
    //this是当前的字符串
    //regex正则表达式,^开头
    return this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))
}

fun String.isValidPassword():Boolean
{
    return this.matches(Regex("^[0-9]{3,20}$"))
}