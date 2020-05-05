package com.weather.lolapplication.ui

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.weather.lolapplication.R
import com.weather.lolapplication.base.BaseActivity
import com.weather.lolapplication.databinding.ActivityRegisterBinding
import com.weather.lolapplication.model.RegisterModel
import com.weather.lolapplication.navigator.RegisterNavigator
import com.weather.lolapplication.utils.Message
import com.weather.lolapplication.utils.makeTextWatcher
import com.weather.lolapplication.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity :BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), RegisterNavigator{


    override val layoutId: Int
        get() = R.layout.activity_register

    override val viewModel: RegisterViewModel by viewModel()

    override fun initAdapter() {
        return
    }

    override fun initBind() {
        viewModel.registerNavigator = this
        viewModel.isOkLiveData.observe(this, Observer {
            if(it){
                button3.setBackgroundResource(R.drawable.button)
            }else{
                button3.setBackgroundResource(R.drawable.button_lock)
            }
        })
        viewModel.snackbarLiveData.observe(this, Observer {
            if(it == Message.SAME_ID){
                println("아이디 중복")
                Snackbar.make(findViewById(android.R.id.content), "아이디가 중복되었습니다", Snackbar.LENGTH_SHORT).show()
            }else if(it == Message.SAME_NAME){
                println("닉네임 중복")
                Snackbar.make(findViewById(android.R.id.content), "닉네임이 중복되었습니다", Snackbar.LENGTH_SHORT).show()
            }else if(it == Message.REGISTER_SUCCESS){
                println("회원가입 완료")
                Snackbar.make(findViewById(android.R.id.content), "회원가입 완료!", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    override fun initListener() {
        editText3.addTextChangedListener(makeTextWatcher {
            viewModel.idTextChanged(editText3.text.toString()) })
        editText4.addTextChangedListener(makeTextWatcher {
            viewModel.passwordTextChanged(editText4.text.toString())
        })
        editText5.addTextChangedListener(makeTextWatcher {
            viewModel.nameTextChanged(editText5.text.toString())
        })
        button3.setOnClickListener {
            viewModel.tryRegister()
        }
    }

    override fun callLoginActivity() {
        finish()
    }


}