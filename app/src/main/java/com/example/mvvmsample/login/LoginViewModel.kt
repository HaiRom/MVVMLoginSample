package com.example.mvvmsample.login

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData



class LoginViewModel : ViewModel() {

    val liveDataUser = MutableLiveData<String>()
    val liveDataPass = MutableLiveData<String>()
    val errorMessageEdtUserName = MutableLiveData<String>()
    val errorMessageEdtPass = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val loginFailure = MutableLiveData<String>()

    private var handler: Handler = Handler(Looper.getMainLooper())

    fun onClickBtnLogin(view: View) {
        Log.d("TAG:LoginViewModel","onClickBtnLogin user = ${liveDataUser.value} pass = ${liveDataPass.value} ")


        val loginUser = LoginUser(liveDataUser.value.toString(), liveDataPass.value.toString())

        if (loginUser.username.length < 3){
            showErrorEditTextUserName("not empty")
        } else if (loginUser.pass.length < 3){
            showErrorEditTextPass("not empty")
        } else {
            isLoading.value = true
            val loginStatus = loginUser.requestCheckLogin(loginUser)

            handler.postDelayed(Runnable {
                isLoading.value = false
                 if (loginStatus){
                     liveDataLoginUser?.value = loginUser
                 } else {
                     showMesLoginFalure("login failure authenization")
                 }
            },2000)

        }



        if (!loginUser.isValidPassword()){
            showErrorEditTextPass("not empty")
            return
        }


    }
    private var liveDataLoginUser: MutableLiveData<LoginUser>? = null

    fun getUser(): LiveData<LoginUser> {
        if (liveDataLoginUser == null) {
            liveDataLoginUser = MutableLiveData()
        }
        return liveDataLoginUser as MutableLiveData<LoginUser>
    }

    private fun showErrorEditTextUserName(mes : String){
        errorMessageEdtUserName.value = mes
    }

    private fun showErrorEditTextPass(mes : String){
        errorMessageEdtPass.value = mes
    }

    private fun showMesLoginFalure(mes : String){
        loginFailure.value = mes
    }

}