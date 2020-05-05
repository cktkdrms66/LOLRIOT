package com.weather.lolapplication.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weather.lolapplication.base.BaseViewModel
import com.weather.lolapplication.model.RegisterModel
import com.weather.lolapplication.navigator.LoginNavigator
import com.weather.lolapplication.utils.Message
import com.weather.lolapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Appendable


class LoginViewModel(val model: RegisterModel, application: Application): BaseViewModel(application){

    lateinit var loginNavigator: LoginNavigator

    private val _isOkLiveData = MutableLiveData<Boolean>()
    val isOkLiveData: LiveData<Boolean>
        get() = _isOkLiveData

    private val _idLiveData = MutableLiveData<String>()
    val idLiveData : LiveData<String>
        get() = _idLiveData

    private val _passwordLiveData = MutableLiveData<String>()
    val passwordLiveData : LiveData<String>
        get() = _passwordLiveData

    private val _snackbarLiveData = SingleLiveEvent<Message>()
    val snackbarLiveData : LiveData<Message>
        get() = _snackbarLiveData


    init{
        _isOkLiveData.value = false
        _idLiveData.value = ""
        _passwordLiveData.value = ""
    }

    fun idTextChanged(string: String){
        _idLiveData.value = string
        if(!_idLiveData.value!!.isEmpty() && !_passwordLiveData.value!!.isEmpty() ){
            unlockLogin(true)
        }else{
            unlockLogin(false)
        }
    }

    fun passwordTextChanged(string: String){
        _passwordLiveData.value = string
        if(!_idLiveData.value!!.isEmpty() && !_passwordLiveData.value!!.isEmpty() ){
            unlockLogin(true)
        }else{
            unlockLogin(false)
        }
    }

    fun unlockLogin(boolean: Boolean){
        _isOkLiveData.postValue(boolean)
    }


    fun login(id:String, password:String){
        if(_isOkLiveData.value == true){
            println("login")
            addDisposable(model.tryLogin(id, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.success){
                        loginNavigator.callMainActivity()
                    }else{
                        _snackbarLiveData.value = Message.LOGIN_FAIL
                    }
                },{it.printStackTrace()}))
        }
    }

    fun register(){
        println("register")
        loginNavigator.callRegisterActivity()

    }




}