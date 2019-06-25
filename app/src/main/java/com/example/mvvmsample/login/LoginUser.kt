package com.example.mvvmsample.login

import android.text.TextUtils
import android.R.attr.password
import android.util.Patterns



class LoginUser(var username: String, var pass: String) {

    fun isValidEmail(): Boolean {
        return !TextUtils.isEmpty(username)

    }

    fun isValidPassword(): Boolean {
        return !TextUtils.isEmpty(pass)
    }

    fun requestCheckLogin(loginUser: LoginUser): Boolean {
        return TextUtils.equals(loginUser.username, "mvvm") && TextUtils.equals(loginUser.pass, "mvvm")
    }

}