package com.example.mvvmsample.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.MainActivity
import com.example.mvvmsample.R
import com.example.mvvmsample.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        //setContentView(R.layout.activity_login)
        //binding.setLifecycleOwner { this }
        binding.loginViewModel = loginViewModel
        binding.executePendingBindings()
        binding.loginViewModel!!.getUser().observe(this, object : Observer<LoginUser>{
            override fun onChanged(t: LoginUser?) {
                Log.d("TAG:LoginActivity","onChanged:getUser")
                val intentMain  = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentMain)
                finish()
            }
        })

        binding.loginViewModel!!.errorMessageEdtUserName.observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                Log.d("TAG:LoginActivity","onChanged:errorMessageEdtUserName")
                editTextUserName.error = t.toString()
                editTextUserName.requestFocus()
            }
        })
        binding.loginViewModel!!.errorMessageEdtPass.observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                Log.d("TAG:LoginActivity","onChanged:errorMessageEdtPass")
                editTextPassWord.error = t.toString()
                editTextPassWord.requestFocus()
            }
        })
        binding.loginViewModel!!.isLoading.observe(this, object : Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                Log.d("TAG:LoginActivity","onChanged:isLoading")
                if (t != null && t){
                    progressCircular.visibility = View.VISIBLE
                } else {
                    progressCircular.visibility = View.GONE
                }
            }

        })
        binding.loginViewModel!!.loginFailure.observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                Snackbar.make(activityLogin,t.toString(),Snackbar.LENGTH_LONG).show()
            }
        })

    }
}
