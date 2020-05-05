package com.weather.lolapplication.ui

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.weather.lolapplication.R
import com.weather.lolapplication.base.BaseActivity
import com.weather.lolapplication.databinding.ActivityLoginBinding
import com.weather.lolapplication.navigator.LoginNavigator
import com.weather.lolapplication.utils.Message
import com.weather.lolapplication.utils.makeTextWatcher
import com.weather.lolapplication.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    override val layoutId: Int
        get() = R.layout.activity_login

    override val viewModel: LoginViewModel by viewModel()

    override fun initAdapter() {
        return
    }

    override fun initBind() {
        viewModel.loginNavigator = this
        viewModel.isOkLiveData.observe(this, Observer {
            if(it){
                button.setBackgroundResource(R.drawable.button)
            }else{
                button.setBackgroundResource(R.drawable.button_lock)
            }
        })
        viewModel.snackbarLiveData.observe(this, Observer {
            if(it == Message.LOGIN_FAIL){
                Snackbar.make(findViewById(android.R.id.content), "로그인에 실패했습니다", Snackbar.LENGTH_SHORT).show()
            }
        })

    }

    override fun initListener() {
        editText.addTextChangedListener(makeTextWatcher {
            viewModel.idTextChanged(editText.text.toString())
        })
        editText2.addTextChangedListener(makeTextWatcher {
            viewModel.passwordTextChanged(editText2.text.toString())
        })

        button.setOnClickListener {
            viewModel.login(editText.text.toString(), editText2.text.toString())

        }
        button2.setOnClickListener {
            viewModel.register()
        }


    }

    override fun callMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    override fun callRegisterActivity() {
        startActivity(Intent(applicationContext, RegisterActivity::class.java))
    }

}